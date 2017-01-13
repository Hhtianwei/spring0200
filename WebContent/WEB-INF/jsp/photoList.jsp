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

<script type="text/javascript">
	$(function() {

	});
</script>
</head>
<body>
	<c:set var="list" value="${searchResult.results }" />
	<c:set var="pagination" value="${searchResult.pagination }" />
	<c:url var="images" value="/images" />
	<table>
		<thead>
			<tr>
				<th>image</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var="photo" varStatus="s">
				<tr>
					<td><img alt="" src="${images }${photo.relativePath }" style="max-height:1000px;max-width:1000px;"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<c:url var="upPage"
		value="/showPhotos?currentPage=${pagination.currentPage + 1 }" />
	<c:url var="downPage"
		value="/showPhotos?currentPage=${(pagination.currentPage - 1)<0?0:(pagination.currentPage - 1) }" />
	<c:url var="firstPage" value="/showPhotos?currentPage=0" />
	<c:url var="lastPage"
		value="/showPhotos?currentPage=${pagination.totalPages-1 }" />

<div>
	<a style="font-size: 50px" href="${firstPage }">首页</a>

	<c:if test="${pagination.currentPage > 0 }">
		<a style="font-size: 50px" href="${downPage }">上一页</a>
	</c:if>

	<c:if test="${pagination.totalPages > (pagination.currentPage + 1) }">
		<a style="font-size: 50px" href="${upPage }">下一页</a>
	</c:if>

	<a style="font-size: 50px" href="${lastPage}">尾页</a>
</div>
</body>
</html>