<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>主页</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/index.css" />

</head>

<body onload="scan_filename()">
	<div class="main">
		<div class="m-left">
			<span id="title">
				<h2>当前文件</h2>
			</span>
			<!--  -->
			<form action="GetNumber" method="get">
				<c:forEach items="${pdfdata}" var="li">

					<ul>
						<li><input type="radio" name="radio" value="${li}" />${li}</li>
					</ul>
				</c:forEach>
				<input type="submit" value="开始批注" id="button_correct" />
			</form>
		</div>
		<div class="m-right">
			<div class="upload" id="drop_area">
				<img src="${pageContext.request.contextPath}/img/file.png"
					id="img_file" />
			</div>
			<span id="tips">
				<form action="UploadServlet" method="post"
					enctype="multipart/form-data" id="form_auto">
					<span id="t_right"> <input type="file" name="fileUpload"
						accept=".pdf" id="upload-link" /> <label for="upload-link">选择文件上传
					</label>
					</span> <span id="t_left"> 或拖拽文件到上面</span>
			</span>
			</form>


		</div>
	</div>
	<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"
		type="text/javascript" charset="utf-8"></script>
	<script src="${pageContext.request.contextPath}/js/main.js"
		type="text/javascript" charset="utf-8"></script>
	
</body>

</html>