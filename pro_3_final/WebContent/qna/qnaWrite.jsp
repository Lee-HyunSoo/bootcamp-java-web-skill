<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../header.jsp" %>
<%@ include file="sub_img.html" %>
<%@ include file="sub_menu.jsp" %>
<script>
    function insertQna() {
        document.form.action = "${contextPath}/qnaPage/qnaInsert";
        document.form.submit();
    }


</script>
<article>
    <h2> 1:1 고객 게시판 </h2>
    <h3> 고객님의 질문에 대해서 운영자가 1:1 답변을 드립니다.</h3>
    <form name="form" method="post">
        <fieldset style="width:100%;">
            <legend>Board Info</legend>
            <label>Title</label>
            <input type="text" name="subject" size="77"><br>
            <label style="display:block;width:100%;">Content</label>
            <textarea rows="8" cols="65" name="content" style="resize:none;width:548px;box-sizing:border-box;" ></textarea><br>
        </fieldset>
        <div class="clear"></div>
        <div id="buttons" style="float:right;margin-right:120px;">
            <input type="submit" value="글쓰기" class="submit" onclick="insertQna('${contextPath}')">
            <input type="reset" value="취소" class="cancel">
            <input type="button" value="쇼핑 계속하기" class="submit" onclick="document.location='${contextPath}/products'">
        </div>
    </form>
</article>
<%@ include file="../footer.jsp" %>