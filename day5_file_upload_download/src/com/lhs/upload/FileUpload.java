package com.lhs.upload;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/*
 * 1. 업로드할 파일 경로 지정
 * 2. 파일 경로 설정
 * 3. 최대 업로드 가능한 파일 크기를 설정
 * 4. request 객체에서 매개변수를 List로 가져오기
 * 5. 파일 업로드 창에서 업로드 된 항목들을 하나씩 가져오기
 * 6. form 필드이면 전송 된 매개변수 값을 출력
 * 7. form 필드가 아니면 파일 업로드 기능을 수행
 * 8. 업로드한 파일 이름을 가져오기
 * 9. 업로드한 파일 이름으로 저장소에 파일을 업로드
 */
@WebServlet("/upload.do")
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String encoding = "utf-8";
		
		/* 업로드 사전 준비 */
		File currentDirPath = new File("C:\\file_repo");
		DiskFileItemFactory factory = new DiskFileItemFactory(); // 이 factory 내부에 파일 경로 설정, 크기 설정 메서드가 있다.
		factory.setRepository(currentDirPath); // 파일을 저장할 디렉터리 설정
		factory.setSizeThreshold(1024 * 1024); // 업로드 시킬 파일의 크기 설정, bit 단위이다.
		ServletFileUpload upload = new ServletFileUpload(factory); // 실질적으로 파일을 업로드 시켜주는 역할
		
		/* 업로드 시작 */
		try {
			List items = upload.parseRequest(request); // request 객체로 들어온 모든 데이터를 List 형태로
			for (int i = 0; i < items.size(); i++) {
				FileItem fileItem = (FileItem) items.get(i); // 파일 아이템 형태로 저장해야 isFormField()를 사용할 수 있다.

				if (fileItem.isFormField()) // isFormField는 파일과 입력한 값을 구별할 수 있다.
					System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
				else {
					/* input type="file" */
					System.out.println("파라미터명:" + fileItem.getFieldName()); // file1
					System.out.println("파일명:" + fileItem.getName()); // 선택한 이미지 파일 
					System.out.println("파일크기:" + fileItem.getSize() + "bytes"); // 선택한 이미지 크기

					if (fileItem.getSize() > 0) { // 업로드 파일 크기 확인
						int idx = fileItem.getName().lastIndexOf("\\");
						if (idx == -1) // "\\" 가 없으면 "/" 를 찾아야한다. (os마다 틀리기 때문)
							idx = fileItem.getName().lastIndexOf("/");

						String fileName = fileItem.getName().substring(idx + 1); // 찾은 index + 1 부분부터 끝까지 자름 -> 파일명 추출
						File uploadFile = new File(currentDirPath + "\\" + fileName);
						fileItem.write(uploadFile); // 설정 완료한 경로에 파일 업로드
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
