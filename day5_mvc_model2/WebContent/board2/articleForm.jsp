<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<head>
<meta charset="UTF-8">
<title>글쓰기창</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">

	// 사용자가 선택한 이미지 미리보기 기능
	function readURL(input) {
		// 파일 선택 여부 확인
		// input.files : 사용자가 여러개의 파일을 동시에 선택하면 ["파일1", "파일2", "파일3" ...] 과 같이 반환되기 때문에 files 이다.
		// 단, <input type="file" > 부분에 파일을 다중선택할 수 있는 option을 설정해야 한다. 이때, 특정 파일의 종류만 선택 가능하게도 설정 가능하다.
		if (input.files && input.files[0]) { 
			var reader = new FileReader(); 
			
			// 지정 파일을 읽고, 준비가 끝나면 (onload)
			reader.onload = function(e) {
				// e : 매개변수 -> 이벤트 관련 된 모든 내용이 전달
				$('#preview').attr('src', e.target.result); // e.target = reader 즉, reader.result를 의미
			}
			// 선택한 파일의 full 경로
			reader.readAsDataURL(input.files[0]);
		}
	}
	
	// 이동해야하는 버튼이 두개인 경우, <input type="submit"> 을 여러개 만들 수 없기 때문에 javascript를 사용해 해당 기능을 구현
	function backToList(obj) {
		obj.action = "${contextPath}/board/listArticles.do";
		obj.submit(); // <form action><input type="submit"/></form>
	}
</script>
<title>새글 쓰기 창</title>
</head>
<body>
	<h1 style="text-align: center">새글 쓰기</h1>
	<form name="articleForm" method="post" action="${contextPath}/board/addArticle.do" enctype="multipart/form-data">
		<table border=0 align="center">
			<tr>
				<td align="right">글제목:</td>
				<td colspan="2"><input type="text" size="67" maxlength="500" name="title" /></td>
			</tr>
			<tr>
				<td align="right" valign="top"><br>글내용:</td>
				<td colspan=2><textarea name="content" rows="10" cols="65" maxlength="4000"></textarea></td>
			</tr>
			<tr>
				<td align="right">이미지파일 첨부:</td>
				<td><input type="file" name="imageFileName" onchange="readURL(this);" /></td>
				<td><img id="preview" src="#" width=200 height=200 /></td>
			</tr>
			<tr>
				<td align="right"></td>
				<td colspan="2">
					<input type="submit" value="글쓰기" /> 
					<input type=button value="목록보기" onClick="backToList(this.form)" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>