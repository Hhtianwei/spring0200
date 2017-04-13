<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>photos</title>
<script type="text/javascript" src="<%=path%>/js/jquery-2.1.1.min.js"></script>
</head>
<body>
	<c:set var="list" value="${searchResult.results }" />
	<c:set var="pagination" value="${searchResult.pagination }" />
	
	
	<c:url var="downPage"
		value="/showPhotos?currentPage=${pagination.currentPage + 1 }" />
	<c:url var="upPage"
		value="/showPhotos?currentPage=${(pagination.currentPage - 1)<0?0:(pagination.currentPage - 1) }" />
	<c:url var="firstPage" value="/showPhotos?currentPage=0" />
	<c:url var="lastPage"
		value="/showPhotos?currentPage=${pagination.totalPages-1 }" />
	
	<c:url var="images" value="/images" />
			<c:forEach items="${list }" var="photo" varStatus="s">
					<img id="mypic" alt="" src="${images }${photo.relativePath }" style="max-height:1000px;max-width:1000px;"/>
					<br/>
			</c:forEach>


<input type="hidden" name="totalPages" value="${pagination.totalPages}"/>
<input type="hidden" name="currentPage" value="${pagination.currentPage}"/>

<div>
	<a style="font-size: 50px" href="${firstPage }">首页</a>

	<c:if test="${pagination.currentPage > 0 }">
		<a style="font-size: 50px" href="${upPage }">上一页</a>
	</c:if>

	<c:if test="${pagination.totalPages > (pagination.currentPage + 1) }">
		<a style="font-size: 50px" href="${downPage }">下一页</a>
	</c:if>

	<a style="font-size: 50px" href="${lastPage}">尾页</a>
</div>

<script type="text/javascript">
	$(function() {
		 $("#mypic").click(function(e){
		        var positionX=e.originalEvent.x-$(this).offset().left||e.originalEvent.layerX-$(this).offset().left||0; 
		        var currentPage = $("input[name='currentPage']").val();
		        var totalPages = $("input[name='totalPages']").val();
		        if (positionX < $(this).width()/2){
		        	if(currentPage > 0){
		        		window.location.href = "${upPage}";
		        	}
		        }else{
		        	if(totalPages > (currentPage +1)){
		        		window.location.href = '${downPage}';
		        	}
		        }
			});
	});	 
</script>
</body>
</html>