<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>  
<%@ include file="../header.jsp" %>  
<%@ include file="sub_img.html"%> 
<%@ include file="sub_menu.jsp" %>       

  <article>
    <h2> My Page(${title}) </h2>
    <form name="formm" method="post">
      <table id="cartList">
      <tr>
        <th>주문일자</th> <th>주문번호</th> <th>상품명</th> <th>결제 금액</th> <th>주문 상세</th> <th>상태</th>    
      </tr>
      <c:forEach items="${orderList}"  var="orderVO">
      <tr>  
        <td> <fmt:formatDate value="${orderVO.indate}" type="date"/></td>
        <td> ${orderVO.oseq} </td>    
        <td> ${orderVO.pname} </td>
        <td> <fmt:formatNumber value="${orderVO.price2}" type="currency"/> </td>
        <td> <a href="${contextPath}/order/orderDetail?oseq=${orderVO.oseq}"> 조회 </a></td>
        <td><c:choose>
        <c:when test="${orderVO.gubun == 0}">처리대기중</c:when>
     	   <c:otherwise>주문완료</c:otherwise>
        </c:choose></td>
      </tr>
      </c:forEach>    
      </table>   
          
      <div class="clear"></div>
      
<div style="width:100%; text-align:center;">
    <c:if test="${totalPages > 1}">
        <c:forEach begin="1" end="${totalPages}" var="pageNum">
            <c:set var="buttonStyle" value="color:#000; font-size:14px; " />
            <c:if test="${pageNum == currentPage}">
                <c:set var="buttonStyle" value="color:blue; font-size:14px; padding:3px; border:1px solid blue; border-radius:100px;" />
            </c:if>
            <a style="${buttonStyle}" href="?tpage=${pageNum}">${pageNum}</a>
        </c:forEach>
    </c:if>
</div>
      
      <div id="buttons" style="float: right">
       <input type="button"    value="쇼핑 계속하기"  class="cancel"  onclick="location.href='${contextPath}/products'"> 
      </div>
    </form>  
  </article>
<%@ include file="../footer.jsp" %>    