<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>  
<%@ include file="../header.jsp" %>  
<%@ include file="sub_img.html"%> 
<%@ include file="sub_menu.html" %>       
  <article>
    <h1> Item </h1>
    <div id="itemdetail" >
      <form  method="post" name="formm">    
        <fieldset>
          <legend> Item detail Info</legend>  
           <a href="${contextPath }/products/productDetail?pseq=${productVO.pseq}">        
            <span style="float: left;">
              <img  src="${contextPath }/product_images/${productVO.image}"  />
            </span>              
            <h2> ${productVO.name} </h2>  
          </a>    
          <label> 가 격 :  </label>  
          <p> ${productVO.price2} 원</p>  
          <label> 수 량 : </label>
          <input  type="text"      name="quantity"  size="2"      value="1"><br>
          <input  type="hidden"    name="pseq"       value="${productVO.pseq}"><br>
         
        </fieldset>
        <div class="clear"></div>
        <div id="buttons" style="cursor:pointer;">
          <input type="button" value="장바구니에 담기"   class="submit"    onclick="insertCart('${contextPath}')"> 
          <input type="button" value="즉시 구매"       class="submit"    onclick="go_order('${contextPath}')"> 
          <input type="reset"  value="취소"    class="cancel">
        </div>
      </form>  
    </div>
    <script>
    function insertCart(aa) {
  	  if (document.formm.quantity.value == "" || document.formm.quantity.value == 0) {
  		    alert("수량을 입력해 주세요.");
  		    document.formm.quantity.focus();
  		  } else {
  		    document.formm.action = aa+"/cart/cart_insert";
  		    document.formm.submit();
  		  }
  		}


  function go_order(aa) {
  	  
  	 
  	  if (document.formm.quantity.value == 0 || document.formm.quantity.value == "") {
  	    alert("수량을 입력해 주세요.");

  	  } else {
  	    document.formm.action = aa+"/order/insertOrder";
  	    document.formm.submit();
  	  }
  	}
    </script>
  </article>
<%@ include file="../footer.jsp" %>    