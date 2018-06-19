<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport"
	content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no, width=device-width">
<title>主页</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/normalize.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index_Phone.css"  />
</head>

<body>
	<noscript>
		<center>
			<h1>请勿禁止当前页面脚本！</h1>
		</center>
	</noscript>
	<div class="main">
		<div class="m-left">
			<span id="title" class="title-text"> <br /> 当前文件
			</span>
			<div id="list">
				<form action="GetNumber" method="get">
					<c:forEach items="${pdfdata}" var="li">
						<ul>
							<li><input type="radio" name="radio" value="${li}" />${li}</li>
						</ul>
					</c:forEach>
			</div>
			<input type="submit" value="开始批注" id="button_correct" />
			</form>

		</div>
		<div class="m-right">
			<div class="upload" id="drop_area">
				<img src=" ${pageContext.request.contextPath}/img/file.png"
					id="img_file" />
			</div>
			<span id="tips">
				<form action="UploadServlet" method="post"
					enctype="multipart/form-data" id="form_auto">
					<span id="t_right" class="tips-text"> <input type="file"
						name="fileUpload" accept=".pdf" id="upload-link" /> <label
						for="upload-link">选择文件上传 </label>
					</span> <span id="t_left" class="tips-text"> 或拖拽文件到上面</span>
			</span>
			</form>

		</div>
	</div>
	<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"
		type="text/javascript" charset="utf-8"></script>
	<script src="${pageContext.request.contextPath}/js/main.js"
		type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
		if(!(/AppleWebKit.*mobile/i.test(navigator.userAgent) || (/MIDP|SymbianOS|NOKIA|SAMSUNG|LG|NEC|TCL|Alcatel|BIRD|DBTEL|Dopod|PHILIPS|HAIER|LENOVO|MOT-|Nokia|SonyEricsson|SIE-|Amoi|ZTE/.test(navigator.userAgent)))) {
				window.location.href = "index.jsp";
			}
	</script>
</body>

</html>