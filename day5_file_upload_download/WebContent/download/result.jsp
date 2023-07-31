<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<% request.setCharacterEncoding("utf-8"); %>

<html>
<head>
<meta charset="UTF-8">
<c:set var="file1" value="${param.param1}" />
<c:set var="file2" value="${param.param2}" />

<title>이미지 파일 출력하기</title>
</head>
<body>
	<pre>
		작업 내용
		1. 이미지 파일 표시창에서 <img> 태그의 src 속성에 다운로드를 요청할 서블릿 이름 download.do와 파일 이름을 get 방식으로 전달
		2. 다운로드한 이미지 파일을 바로 <img> 태그에 표시
		3. <a>태그를 클릭해 서블릿에 다운로드를 요청
		4. 파일 전체를 로컬 pc에 다운로드 (기본값 : download 폴더)
		
		참고
		<a href="~~~~.txt 또는 ~~~.zip"></a> : 파일 다운로드
		<a href="javascript:mailTo='이메일주소'"></a> : MS -> Outlook이 실행 되어 메일 발송 화면으로 전환 
		
		주요 코드 설명
		1. 다운로드할 파일 이름을 추출
		2. 파일 이름으로 서블릿에서 이미지를 다운로드해 표시
		3. 이미지를 파일로 다운로드
	</pre>
	<hr>
	파일명 1 : <c:out value="${file1}" /> <br> 
	파일명 2 : <c:out value="${file2}" /> <br><br>

	<c:if test="${not empty file1}">
		<img src="${contextPath}/download.do?fileName=${file1}" width=300 height=300 /> <br>
	</c:if>
	<br>
	<c:if test="${not empty file2}">
		<img src="${contextPath}/download.do?fileName=${file2}" width=300 height=300 /> <br>
	</c:if>
	파일 내려받기 : <br>
	<a href="${contextPath}/download.do?fileName=${file1}">파일 1 내려받기</a> <br>
	<a href="${contextPath}/download.do?fileName=${file2}">파일 2 내려받기</a> <br>
</body>
</html>