<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<% request.setCharacterEncoding("utf-8"); %>
<html>
<head>
<meta charset="UTF-8">
<title>파일 다운로드 요청하기</title>
</head>
<body>
	<pre>
		다운로드할 파일 이름을 매개변수로 전달 (duke.png 나 duke2.png가 아닌 다른 파일을 업로드 했다면 해당 파일 이름으로 수정)
	</pre>
	<hr>

	<form method="post" action="result.jsp">
		<input type=hidden name="param1" value="image1.jpg" /> <br> 
		<input type=hidden name="param2" value="image2.jpg" /> <br> 
		<input type="submit" value="이미지 다운로드">
	</form>
</body>
</html>