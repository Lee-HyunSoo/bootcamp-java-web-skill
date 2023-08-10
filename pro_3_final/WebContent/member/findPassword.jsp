<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<c:set var="contextPath" value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>비밀번호 찾기</title>
		<link href="${contextPath}/CSS/subpage.css" rel="stylesheet">
		<script type="text/javascript" src="${contextPath}/member/member.js"></script>
		<style type="text/css">
		body{   
		   background-color:#f5f5dc;
		   font-family: Verdana;
		}
		#popup{   
		   padding: 0 10px;
		}
		#popup h1 {
		   font-family: "Times New Roman", Times, serif;
		   font-size: 45px;
		   color: #CCC;
		   font-weight: normal;
		}
		
		table#GetPWD {
		    border-collapse:collapse;    /* border 사이의 간격 없앰 */   
		    border-top: 3px solid  #fff;  
		    border-bottom: 3px solid  #fff;
		    width: 100%;  
		    margin-top: 15px; 
		}
		table#GetPWD td{   
		   text-align: center;
		   border-bottom: 1px dotted  #fff;  
		   color:#FFF;   
		}
		table th, td{
		  padding: 10px;
		}
		table#GetPWD  a{
		   display:block; 
		    height:20px;
		    text-decoration:none;
		    color:#fff;
		    padding: 10px;
		}
		table#GetPWD a:hover{
		    color: #F90;
		    font-weight: bold;
		}
		</style>
	</head>
	
	<body>
	<div id="popup">
		<h1>비밀번호 찾기</h1>
		<form method="post" name="findPW" action="${contextPath}/members/findPwdForm">
			<table>
			<tr>
				<td align="right"><label> 아이디</label></td>
				<td><input type="text" name="id" value=""></td>
			</tr>	
			<tr>
				<td align="right"><label> 이름</label></td>
				<td><input type="text" name="name" value=""></td>
			</tr>	
			<tr>
				<td align="right"><label> 이메일</label></td>
				<td><input type="text" name="email" value=""></td>
			</tr>
			<tr>
				<td align="center" colspan="2"><input type="submit" value="비밀번호 찾기" class="submit"></td>
			</tr>
			</table>
		</form>
		
		<table id="GetPWD">
			<tr>
				<th>비밀번호</th>
			</tr>
			<tr>
				<td>${memberPwd}</td>
			</tr>
		</table>
		</div>
	</body>
</html>