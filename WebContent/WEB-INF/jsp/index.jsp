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

<c:url var="video" value="/video"/>
<c:url var="images" value="/images"/>

	<h2>Hello World!</h2>
	<br>
	<br>
	<br>
	<br>
	<br>
	<table border="1">
		<thead>
			<tr>
				<th>No</th>
				<th>fileList</th>
				<th>video</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach items="${movs }" var="mov" varStatus="s">
				<tr>
					<td>${s.index+1 }</td>
					<td>${mov.key }</td>
					<td>
						<%-- <video src="${video }${mov.value }" controls="controls" poster="${images }/jiaotongbank.jpg">
						</video> --%>
						<img alt="" src="${images }${mov.value }"/>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	
	<%-- <c:url var="upload" value="/uploadFiles"/>
	<form method="post" action="${upload }" enctype="multipart/form-data">
		用户名:<input type="text" name="str"><br>
		file:<input type="file" class="multi" name="file" maxlength=20>
		<input type="submit"/>
	</form> --%>
	
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
