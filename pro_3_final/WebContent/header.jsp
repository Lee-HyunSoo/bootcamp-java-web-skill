<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="contextPath"  value="${pageContext.request.contextPath}"  /> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nonage Shop</title>
<link href="${contextPath}/css/shopping.css" rel="stylesheet">
<script type="text/javascript" src="${contextPath}/member/member.js"></script>
</head>

<body>
	<div id="wrap">
		<!--헤더파일 들어가는 곳 시작 -->
		<header>
			<!--로고 들어가는 곳 시작--->
			<div id="logo">
				<a href="${contextPath }/products"> <img
					src="${contextPath }/images/nonage.png" width="180" height="100" alt="nonageshop">
				</a>
			</div>
			<!--로고 들어가는 곳 끝-->
			<nav id="catagory_menu">
				<ul>
					<c:choose>
						<c:when test="${empty sessionScope.loginUser}">
							<li>	<a href="${contextPath}/members/loginForm" style="width: 110px;">LOGIN(CUSTOMER</a>
								<a href="${contextPath}/admin/members/adminLogin" style="width: 100px;">| ADMIN)</a></li>
							<li>/</li>
						<li><a href="${contextPath}/members/contract">JOIN</a></li>
						</c:when>
						<c:otherwise>
							<li style="color: orange">
								${sessionScope.loginUser.name}(${sessionScope.loginUser.id})</li>
							<li><a href="${contextPath}/members/logout">LOGOUT</a></li>
						</c:otherwise>
					</c:choose>
					<li>/</li>
					<li><a href="${contextPath }/cart/cart_list">CART</a></li>
					<li>/</li>
					<li><a href="${contextPath }/order/mypage">MY PAGE</a></li>
					<li>/</li>
					<li><a href="${contextPath }/qnaPage/qnaList">Q&amp;A(1:1)</a>
					</li>
				</ul>
			</nav>

			<nav id="top_menu">
				<ul>
				  <li><a href="${contextPath}/products/catagory?kind=1">Heels</a></li>  
				  <li><a href="${contextPath}/products/catagory?kind=2">Boots</a></li>  
				  <li><a href="${contextPath}/products/catagory?kind=3">Sandals</a></li> 
				  <li><a href="${contextPath}/products/catagory?kind=4">Slipers</a></li> 
				  <li><a href="${contextPath}/products/catagory?kind=5">Sneakers</a></li> 
				</ul>
			</nav>
			<div class="clear"></div>
			<hr>
		</header>
		<!--헤더파일 들어가는 곳 끝 -->