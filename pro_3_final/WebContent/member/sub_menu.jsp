<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<nav id="sub_menu">
	<ul>
		<li><a href="${contextPath}/members/loginForm">Login</a></li>
		<li><a href="${contextPath}/members/contract">Join us</a></li>
	</ul>
</nav>