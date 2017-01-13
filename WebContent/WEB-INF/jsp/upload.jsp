<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>upload</title>
<meta charset="utf-8">
<title>baidu upload demo</title>
<script type="text/javascript" src="<%=path %>/js/jquery-2.1.1.min.js"></script>

	<script type="text/javascript">
	$(function(){
	
	});
	</script>
</head>
<body>
	
	<div id="uploader" class="wu-example">
	    <!--用来存放文件信息-->
	    <div id="thelist" class="uploader-list"></div>
	    <div class="btns">
	        <div id="picker">选择文件</div>
	        <c:url var="upload" value="/uploadFiles"/>
	        <form action="${upload }" method="post" enctype="multipart/form-data">
	        	<input type="file" name="files"  multiple="true"/>
	        	<input type="submit"/>
	        </form>
	        
	    </div>
	</div>

	


</body>
</html>
