<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/admin/header.jsp"%>
<%@ include file="/admin/sub_menu.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<article>
	<h1>상품등록</h1>
	<!-- [1] 파일을 업로드 하기 위해서는 폼태그를 post 방식으로 전송하고, 인코딩 타입을 multipart/form-data 로 지정해야 한다. -->
	<form name="frm" method="post" enctype="multipart/form-data">
		<table id="list">
			<tr>
				<th>상품분류</th>
				<td colspan="5"><select name="kind">
						<c:forEach items="${kindList}" var="kind" varStatus="status">
							<option value="${status.count}">${kind}</option>
						</c:forEach>
				</select>
			<tr>
				<th>상품명</th>
				<td width="343" colspan="5">
					<input type="text" name="name" size="47" maxlength="100">
				</td>
			</tr>
			<tr>
				<th>원가[A]</th>
				<td width="70">
					<input type="text" name="price1" size="11" value="0" onKeyUp='NumFormat(this)'>
				</td>
				<th>판매가[B]</th>
				<td width="70">
					<input type="text" name="price2" size="11" value="0" onBlur="go_ab()" onKeyUp='NumFormat(this)'>
				</td>
				<th>[B-A]</th>
				<td width="72">
					<input type="text" name="price3" size="11" readonly onKeyUp='NumFormat(this)'>
				</td>
			</tr>
			<tr>
				<th>베스트상품</th>
				<td>
					<input type="checkbox" name="bestyn" value="y" checked="checked">	
				</td>
				<th>사용유무</th>
				<td>
					<input type="checkbox" name="useyn" value="y" checked="checked">	
				</td>
			</tr>
			<tr>
				<th>상세설명</th>
				<td colspan="5"><textarea name="content" rows="8" cols="70"></textarea>
				</td>
			</tr>
			<tr>
				<th>상품이미지</th>
				<td width="343" colspan="5">
					<!--  [2] 파일 업로드를 하기 위한 input 태그는 타입 속성 값을 file로 지정해야 한다.  --> 
					<input type="file" name="image">
				</td>
			</tr>
		</table>
		
		<input class="btn" type="button" value="등록" onClick="go_save('${contextPath}')">
		<input class="btn" type="button" value="취소" onClick="go_mov('${contextPath}')">
	</form>
</article>
<%@ include file="/admin/footer.jsp"%>
</body>
</html>