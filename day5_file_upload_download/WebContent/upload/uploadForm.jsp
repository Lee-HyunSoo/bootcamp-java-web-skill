<%-- EL을 사용하기 위해서는, page 디렉티브의 isELIgnored 속성을 false 로 설정해야한다. 하지만 명시하지 않을 경우 기본값이 false 이다. --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%request.setCharacterEncoding("UTF-8");%>

<html>
<head>
<meta charset="UTF-8">
<head>
<title>파일 업로드창</title>
</head>
<body>
	<pre>
		1. servlet에 요청해 파일을 업로드.
		2. 파일 업로드 시 반드시 encType을 multipart/form-data로 설정해야한다.
	</pre>
	<hr>
	<form action="${contextPath}/upload.do" method="post" enctype="multipart/form-data">
		파일1: <input type="file" name="file1"><br> 
		파일2: <input type="file" name="file2"> <br> 
		파라미터1: <input type="text" name="param1"> <br> 
		파라미터2: <input type="text" name="param2"> <br> 
		파라미터3: <input type="text" name="param3"> <br> 
		<input type="submit" value="업로드">
	</form>
</body>
</html>