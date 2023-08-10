package com.lhs.board8;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String ARTICLE_IMAGE_REPO = "C:\\board\\article_image";
	private BoardService boardService = new BoardService();
	private ArticleVO articleVO = new ArticleVO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = "";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		HttpSession session;
		String action = request.getPathInfo();
		System.out.println("action:" + action);
		
		try {
			List<ArticleVO> articles = new ArrayList<ArticleVO>();
			if (action == null) {
				
				/* request 객체에서 section, pageNum을 가져온다. */
				String _section = request.getParameter("section"); 
				String _pageNum = request.getParameter("pageNum");
				
				/* request 객체에서 가져온 section, pageNum이 null이면 1, 아니면 가져온 값으로 설정한다. */
				int section = Integer.parseInt(((_section == null) ? "1" : _section));
				int pageNum = Integer.parseInt(((_pageNum == null) ? "1" : _pageNum));

				/* Service를 통해 DAO에 접근, 쿼리를 실행하기 위한 객체를 만든다. */
				Map<String, Integer> pagingMap = new HashMap<String, Integer>();
				pagingMap.put("section", section);
				pagingMap.put("pageNum", pageNum);

				/* Service를 통해 DAO에 접근, 쿼리 실행 결과를 담는다. */
				Map<String, Object> articlesMap = boardService.listArticles(pagingMap);
				articlesMap.put("section", section);
				articlesMap.put("pageNum", pageNum);
				
				/* 쿼리 실행 결과를 Model 역할을 하는 request 객체에 담아, jsp로 넘겨준다. */
				request.setAttribute("articlesMap", articlesMap);
				nextPage = "/board8/listArticles.jsp";

			} 
			else if (action.equals("/listArticles.do")) {
				String _section = request.getParameter("section");
				String _pageNum = request.getParameter("pageNum");
				int section = Integer.parseInt(((_section == null) ? "1" : _section));
				int pageNum = Integer.parseInt(((_pageNum == null) ? "1" : _pageNum));
				
				Map<String, Integer> pagingMap = new HashMap<String, Integer>();
				pagingMap.put("section", section);
				pagingMap.put("pageNum", pageNum);
				
				Map<String, Object> articlesMap = boardService.listArticles(pagingMap);
				articlesMap.put("section", section);
				articlesMap.put("pageNum", pageNum);
				request.setAttribute("articlesMap", articlesMap);
				nextPage = "/board8/listArticles.jsp";
			} 
			else if (action.equals("/articleForm.do")) {
				/*  GET 방식을 통해 Form 양식을 작성할 JSP에  접근한다. */
				nextPage = "/board8/articleForm.jsp";
			} 
			else if (action.equals("/addArticle.do")) {
				int articleNO = 0;
				/* 파일 업로드를 하기 위해, upload 메서드에 request 객체와 response 객체를 파라미터로 넘겨준다. */
				Map<String, String> articleMap = upload(request, response);
				
				/* upload 메서드의 결과 중 사용할 데이터를 추출한다. */
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");

				/* DAO의 파라미터로 사용할 articleVO 객체를 생성한다. */
				articleVO.setParentNO(0);
				articleVO.setId("hong");
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setImageFileName(imageFileName);
				
				/* DAO에 접근하여 쿼리를 실행하기 위해 Service Method를 실행한다. */
				articleNO = boardService.addArticle(articleVO);
				
				/* 업로드를 하기 위한 이미지 파일의 이름이 null이나 공백이 아니라면 */
				if (imageFileName != null && imageFileName.length() != 0) {
					/* 현재 파일을 임시 저장하고 있는 temp 폴더의 경로 */
					File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
					/* 파일을 실제로 저장할 폴더의 경로 */
					File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO);
					/* 파일을 실제로 저장할 폴더의 경로에 articleNO 값을 이름으로 하는 새 폴더를 만든다. */
					destDir.mkdirs();
					/* 파일을 임시저장하고 있는 temp 폴더로 부터 새로 만든 폴더로 파일을 옮겨 담는다. */
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
				}
				/* 파일 업로드를 완료한 후 알람창을 띄우고, 메인 페이지인 listArticles.do로 이동한다. */
				PrintWriter pw = response.getWriter();
				pw.print("<script>" + "  alert('새글을 추가했습니다.');" + " location.href='" + request.getContextPath()
						+ "/board/listArticles.do';" + "</script>");
				return;
			} 
			else if (action.equals("/viewArticle.do")) {
				/* 선택한 게시글의 상세 정보를 불러오기 위해, 게시글 번호를 가져온다. */
				String articleNO = request.getParameter("articleNO");
				/* 게시글 번호를 통해 DAO 내 쿼리를 실행하기 위해, Service Method로 접근한다. */
				articleVO = boardService.viewArticle(Integer.parseInt(articleNO));
				/* return 받은 쿼리의 실행 결과를 request 객체에 담아, JSP로 보낸다. */
				request.setAttribute("article", articleVO);
				nextPage = "/board8/viewArticle.jsp";
			} 
			else if (action.equals("/modArticle.do")) {
				/* 수정한 게시글의 파일을 업로드하기 위해, upload 메서드에 request 객체와 response 객체를 파라미터로 넘겨준다. */
				Map<String, String> articleMap = upload(request, response);
				/* 웹에서 데이터는 String 형태로 이동되기 때문에, 전달받은 게시글 번호를 int 타입으로 변환한다. */
				int articleNO = Integer.parseInt(articleMap.get("articleNO"));

				/* DAO가 필요로하는 파라미터를 만들기 위해, articleNO에 데이터를 담는다. */
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");
				articleVO.setArticleNO(articleNO);
				articleVO.setParentNO(0);
				articleVO.setId("hong");
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setImageFileName(imageFileName);
				
				/* DAO 내 쿼리를 실행하기 위해 articleNO 객체를 Service Method의 파라미터로 담아 전달한다. */
				boardService.modArticle(articleVO);
				
				/* 수정하려는 이미지 파일의 이름이 null이나 공백이 아니라면, */
				if (imageFileName != null && imageFileName.length() != 0) {
					/* 기존의 파일 이름을 가져온다. */
					String originalFileName = articleMap.get("originalFileName");
					/* temp 폴더에 임시저장한 새로 업로드할 파일의 경로  */
					File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
					/* 실제 파일을 저장할 경로  */
					File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO);
					/* 파일을 실제로 저장할 폴더의 경로에 articleNO 값을 이름으로 하는 새 폴더를 만든다. */
					destDir.mkdirs();
					/* 파일을 temp 폴더에서 실제로 파일을 저장할 경로로 이동한다. */
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
					
					/* 기존 파일의 경로를 File 객체에 담은 후, 삭제한다. */
					File oldFile = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO + "\\" + originalFileName);
					oldFile.delete();
				}
				/* 수정 후 alert창을 띄워주고, 상세보기 페이지로 다시 이동한다. */
				PrintWriter pw = response.getWriter();
				pw.print("<script>" + "  alert('글을 수정했습니다.');" + " location.href='" + request.getContextPath()
						+ "/board/viewArticle.do?articleNO=" + articleNO + "';" + "</script>");
				return;
			} 
			else if (action.equals("/removeArticle.do")) {
				/* 선택한 게시글을 삭제하기 위해 해당 게시글의 번호를 받아오고, 웹 상에서 데이터는 String 형태로 이동되기 때문에 int 형으로 파싱한다. */
				int articleNO = Integer.parseInt(request.getParameter("articleNO"));
				/* 게시글 번호를 파라미터로 DAO에 접근하여 데이터를 삭제하기 위해 Service Method를 호출한다. */
				List<Integer> articleNOList = boardService.removeArticle(articleNO);
				/* 삭제 쿼리메서드의 return 값으로 글 번호를 받아오고, 해당 글번호 이름으로 된 폴더에 접근한다. */
				for (int _articleNO : articleNOList) {
					File imgDir = new File(ARTICLE_IMAGE_REPO + "\\" + _articleNO);
					/* 글번호 이름으로 된 폴더를 삭제한다. */ 
					if (imgDir.exists()) {
						FileUtils.deleteDirectory(imgDir);
					}
				}
				/* 삭제를 완료한 후 alert창을 띄우고, listArticle.do로 이동한다. */
				PrintWriter pw = response.getWriter();
				pw.print("<script>" + "  alert('글을 삭제했습니다.');" + " location.href='" + request.getContextPath()
						+ "/board/listArticles.do';" + "</script>");
				return;

			} 
			else if (action.equals("/replyForm.do")) {
				/* 답글을 다려면 필요한 부모 게시글의 번호를 가져와, int형으로 파싱한다. */
				int parentNO = Integer.parseInt(request.getParameter("parentNO"));
				/* 답글을 작성하는 Form 양식이 있는 JSP로 이동하기 전, 부모 게시글 번호를 활용하기 위해 session에 저장한다. */
				session = request.getSession();
				session.setAttribute("parentNO", parentNO);
				nextPage = "/board06/replyForm.jsp";
			} 
			else if (action.equals("/addReply.do")) {
				/* 부모 글 번호를 가져오기 위해 session에 접근, 데이터를 추출 후 필요없어진 데이터를 session에서 삭제한다. */
				session = request.getSession();
				int parentNO = (Integer) session.getAttribute("parentNO");
				session.removeAttribute("parentNO");
				
				/* 답글 작성 시 파일 업로드를 하기위해, upload 메서드를 실행해 request, response 객체를 파라미터로 넘겨준다. */
				Map<String, String> articleMap = upload(request, response);
				
				/* DAO의 쿼리메서드를 실행하기 위해 필요한 데이터를 Map에서 추출후, 데이터를 저장한다. */
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");
				
				articleVO.setParentNO(parentNO);
				articleVO.setId("lee");
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setImageFileName(imageFileName);
				
				/* Service Method에 저장한 파라미터를 넘겨  DAO에 접근, 쿼리 실행 결과를 담는다. */
				int articleNO = boardService.addReply(articleVO);
				
				/* 업로드할 이미지 파일이 null이 아니고 이미지 파일의 이름이 공백이 아니라면, */
				if (imageFileName != null && imageFileName.length() != 0) {
					/* 업로드할 파일을 임시저장할 temp 폴더의 경로 */
					File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
					/* 파일을 실제 저장할 경로 */
					File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO);
					/* 실제 저장할 경로에 articleNO의 값을 이름으로 하는 폴더를 생성한다. */
					destDir.mkdirs();
					/* 임시저장 폴더에서 실제 저장할 폴더로 파일을 옮긴다. */
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
				}
				/* 답글을 단 후, alert 창을 띄운 후 viewArticle.do로 이동한다. */
				PrintWriter pw = response.getWriter();
				pw.print("<script>" + "  alert('답글을 추가했습니다.');" + " location.href='" + request.getContextPath()
						+ "/board/viewArticle.do?articleNO=" + articleNO + "';" + "</script>");
				return;
			} 
			else {
				nextPage = "/board06/listArticles.jsp";
			}

			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> articleMap = new HashMap<String, String>();
		String encoding = "utf-8";
		File currentDirPath = new File(ARTICLE_IMAGE_REPO);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024 * 1024);
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> items = upload.parseRequest(request);
			for (int i = 0; i < items.size(); i++) {
				FileItem fileItem = items.get(i);
				if (fileItem.isFormField()) {
					System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
					articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
				} else {
					System.out.println("파라미터명:" + fileItem.getFieldName());
					System.out.println("파일크기:" + fileItem.getSize() + "bytes");
					if (fileItem.getSize() > 0) {
						int idx = fileItem.getName().lastIndexOf("\\");
						if (idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}

						String fileName = fileItem.getName().substring(idx + 1);
						System.out.println("파일명:" + fileName);
						articleMap.put(fileItem.getFieldName(), fileName); // 익스플로러에서 업로드 파일의 경로 제거 후 map에 파일명 저장);
						File uploadFile = new File(currentDirPath + "\\temp\\" + fileName);
						fileItem.write(uploadFile);

					} 
				} 
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleMap;
	}

}
