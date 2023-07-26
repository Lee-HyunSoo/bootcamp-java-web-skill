<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="member2" method="post">
		<table>
			<th>회원정보 수정 창</th>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="id" value="<%=request.getParameter("id")%>" readonly="readonly"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pwd"></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="email"></td>
			</tr>
		</table>
		<input type="submit" value="수정" />
		<input type="hidden" name="command" value="modifyMember" />
	</form>
</body>
</html>