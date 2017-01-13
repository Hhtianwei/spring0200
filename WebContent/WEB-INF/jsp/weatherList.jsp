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
<title>weatherList</title>
<script type="text/javascript" src="<%=path %>/js/jquery-2.1.1.min.js"></script>

	<script type="text/javascript">
	$(function(){
		
	});
	</script>
</head>
<body>
	<c:set var="list" value="${searchResult.results }"/>
	<c:set var="pagination" value="${searchResult.pagination }"/>

	<table border="1">
		<thead>
			<tr>
				<th>No</th>
				<th>weatherId</th>
				<th>city</th>
				<th>cityCode</th>
				<th>maxTemperature</th>
				<th>Option</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var="weather" varStatus="s">
				<tr>
					<td>${s.index+1 }</td>
					<td>${weather.id }</td>
					<td>${weather.city }</td>
					<td>${weather.cityCode }</td>
					<td>${weather.maxTemperature }</td>
					<td>no</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<c:url var="upPage" value="/showWeathers?currentPage=${pagination.currentPage + 1 }"/>
	<c:url var="downPage" value="/showWeathers?currentPage=${(pagination.currentPage - 1)<0?0:(pagination.currentPage - 1) }"/>
	<c:url var="firstPage" value="/showWeathers?currentPage=0"/>
	<c:url var="lastPage" value="/showWeathers?currentPage=${pagination.totalPages-1 }"/>
	
	<a href="${firstPage }" >首页</a>
	
	<c:if test="${pagination.currentPage > 0 }">
		<a href="${downPage }" >上一页</a>
	</c:if>

	<c:if test="${pagination.totalPages > (pagination.currentPage + 1) }">
		<a href="${upPage }" >下一页</a>
	</c:if>
	
<a href="${lastPage}" >尾页</a>

	
	
</body>
</html>