package com.lhs.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/download.do")
public class FileDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	/*
	 * 파일 다운로드 기능은 자바 IO 를 이용해 구현
	 * 1. response.getOutputStream(); 을 호출해 OutputStream을 얻기
	 * 2. 배열로 버퍼 생성 (브라우저 제한으로 파일을 한번에 전송할 수 없기 때문)
	 * 3. while 문을 이용해 파일에서 데이터를 한번에 8KB씩 버퍼에 읽어오기 (JSP 버퍼 사이즈가 기본 8KB 이기 때문) 
	 * 4. OutputStream의 write() 를 이용해 브라우저로 출력
	 * 
	 * 주요 코드 설명
	 * 1. 매개변수로 전송 된 파일 이름을 읽기 (fileName 파라미터 값 추출 : 파일명.확장자)
	 * 2. response에서 OutputStream 객체를 가져오기
	 * 3. 파일 다운로드 가능
	 * 4. 버퍼를 이용해 파일에서 버퍼로 데이터를 읽어와 한번에 출력
	 */
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String fileName = (String) request.getParameter("fileName"); // 파일명.확장자
		/* 실제 다운로드 가능하도록 설정 : 라이브러리마다 다르다. */
		response.setHeader("Cache-Control", "no-cache"); 
		response.addHeader("Content-disposition", "attachment; fileName=" + fileName);

		String downFile = "C:\\file_repo\\" + fileName; // 어디에 업로드 되어있는 파일인지
		File file = new File(downFile);
		FileInputStream in = new FileInputStream(file); // 파일을 스트림 방식으로 읽기 위한 객체 생성
		OutputStream out = response.getOutputStream(); // 브라우저로 출력하기 위한 OutputStream
		
		byte[] buffer = new byte[1024 * 8]; // 8KB 씩 읽기 위한 byte 배열
		while (true) {
			int count = in.read(buffer); // byte 단위로 읽는 read(), read() 내에 byte배열을 넣게 되면 읽은 byte값을 하나 씩 배열에 채운다.
			if (count == -1) // 8KB 씩 읽다가, 읽은 내용이 없으면 -1 을 반환
				break;
			out.write(buffer, 0, count); // 브라우저에 출력, write(출력대상, startIndex, 개수)
		}
		in.close();
		out.close();
	}

}
