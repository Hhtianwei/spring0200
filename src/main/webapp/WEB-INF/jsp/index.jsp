<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>111Hello World!</h2>
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
						<c:url var="img" value="/images2"/>
					<img alt="" src="${img }/${mov.key }">
						<%-- <embed src="${mov.value }" /> --%>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>
