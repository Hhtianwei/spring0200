<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>Hello World!</h2>
	<br>
	<br>
	<br>
	<br>
	<br>
	
	<c:url var="upload" value="/upload"/>
	
	<table border="1">
		<thead>
			<tr>
				<th>No</th>
				<th>fileList</th>
				<th>download</th>
			</tr>
		</thead>
		<tbody>
			
			<c:choose>
				<c:when test="${not empty fileNames}">
					<c:forEach items="${fileNames }" var="file" varStatus="s">
						<tr>
							<td>${s.index+1 }</td>
							<td><img src="${upload }/${file.key}"/></td>
							<td><a href="${upload }/${file.key }">下载</a></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr><td colspan="3">没有上传数据</td></tr>
				</c:otherwise>
			</c:choose>
			
		</tbody>
	</table>
	
		<hr/>
		copy files to server
		<br>
		<c:url var="copyFile" value="/copyImages"/>
		<form action="${copyFile}" method="post">
			<input type="text" name="filePath"/>
			<input type="submit" value="copy"/>
		</form>
	<hr/>
</body>
</html>
