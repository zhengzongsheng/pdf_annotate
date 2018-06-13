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
<title></title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/correct.css" />
<style>
#background {
	cursor: url('${pageContext.request.contextPath}/img/cursor.png'), auto;
}
</style>
</head>

<body>
	<div class="header">
		<div class="img_button img_pencil active">
			<button id="pencil">
		</div>
		<div class="line"></div>
		<div class="color_select">
			<div id="color_fadeToggle">
				<ul class="color_list">
					<li data-value="#000000" style="background: #000000"></li>
					<li data-value="#424242" style="background: #424242"></li>
					<li data-value="#626262" style="background: #626262"></li>
					<li data-value="#9d9d95" style="background: #9d9d95"></li>
					<li data-value="#c7c7c7" style="background: #c7c7c7"></li>
					<li data-value="#efefef" style="background: #efefef"></li>
					<li data-value="#f7f7f7" style="background: #f7f7f7"></li>
					<li data-value="#ffffff" style="background: #ffffff"></li>
					<li data-value="#ff0000" style="background: #ff0000"></li>
					<li data-value="#ff9d00" style="background: #ff9d00"></li>
					<li data-value="#ffff00" style="background: #ffff00"></li>
					<li data-value="#00ff00" style="background: #00ff00"></li>
					<li data-value="#00ffff" style="background: #00ffff"></li>
					<li data-value="#0000ff" style="background: #0000ff"></li>
					<li data-value="#9d00ff" style="background: #9d00ff"></li>
					<li data-value="#ff00ff" style="background: #ff00ff"></li>
					<li data-value="#f7c7cf" style="background: #f7c7cf"></li>
					<li data-value="#ffebcb" style="background: #ffebcb"></li>
					<li data-value="#ffebcb" style="background: #ffebcb"></li>
					<li data-value="#d7efd7" style="background: #d7efd7"></li>
					<li data-value="#d3dbe7" style="background: #d3dbe7"></li>
					<li data-value="#cfe7f7" style="background: #cfe7f7"></li>
					<li data-value="#d3dbe7" style="background: #d3dbe7"></li>
					<li data-value="#e7d7df" style="background: #e7d7df"></li>
					<li data-value="#e79d9d" style="background: #e79d9d"></li>
					<li data-value="#ffc79d" style="background: #ffc79d"></li>
					<li data-value="#ffe79d" style="background: #ffe79d"></li>
					<li data-value="#b5d7a5" style="background: #b5d7a5"></li>
					<li data-value="#a5c7cf" style="background: #a5c7cf"></li>
					<li data-value="#9dc7ef" style="background: #9dc7ef"></li>
					<li data-value="#b5a5d7" style="background: #b5a5d7"></li>
					<li data-value="#d7a5bd" style="background: #d7a5bd"></li>
					<li data-value="#e76262" style="background: #e76262"></li>
					<li data-value="#f7ad6a" style="background: #f7ad6a"></li>
					<li data-value="#ffd762" style="background: #ffd762"></li>
					<li data-value="#95bd7a" style="background: #95bd7a"></li>
					<li data-value="#72a5ad" style="background: #72a5ad"></li>
					<li data-value="#6aaddf" style="background: #6aaddf"></li>
					<li data-value="#8d7ac7" style="background: #8d7ac7"></li>
					<li data-value="#c77aa5" style="background: #c77aa5"></li>
					<li data-value="#cf0000" style="background: #cf0000"></li>
					<li data-value="#e79538" style="background: #e79538"></li>
					<li data-value="#efc730" style="background: #efc730"></li>
					<li data-value="#6aa54a" style="background: #6aa54a"></li>
					<li data-value="#4a7a8d" style="background: #4a7a8d"></li>
					<li data-value="#385c7" style="background: #3885c7"></li>
					<li data-value="#624aa5" style="background: #624aa5"></li>
					<li data-value="#a54a7a" style="background: #a54a7a"></li>
					<li data-value="#9d0000" style="background: #9d0000"></li>
					<li data-value="#b56208" style="background: #b56208"></li>
					<li data-value="#bd9500" style="background: #bd9500"></li>
					<li data-value="#387a20" style="background: #387a20"></li>
					<li data-value="#104a5a" style="background: #104a5a"></li>
					<li data-value="#085295" style="background: #085295"></li>
					<li data-value="#301872" style="background: #301872"></li>
					<li data-value="#721842" style="background: #721842"></li>
					<li data-value="#620000" style="background: #620000"></li>
					<li data-value="#7a3800" style="background: #7a3800"></li>
					<li data-value="#285218" style="background: #285218"></li>
					<li data-value="#285218" style="background: #285218"></li>
					<li data-value="#083038" style="background: #083038"></li>
					<li data-value="#003062" style="background: #003062"></li>
					<li data-value="#20104a" style="background: #20104a"></li>
					<li data-value="#4a1030" style="background: #4a1030"></li>
				</ul>
			</div>
		</div>
		<input type="range" max="15" min="1" id="range" />
		<div class="line"></div>
		<div class="img_button img_empty">
			<button id="clear_canvas">
		</div>
		<div class="img_button img_save">
			<button id="btn">
		</div>
		<div class="img_button img_download">
			<button id="download">
		</div>
	</div>

	<div class="sidebar_left">
		<ul class="img_select">
			<c:forEach var="i" begin="1" end="${correct_number}">

				<li id="img_${i}"><img
					src="${pageContext.request.contextPath}/Document/correct/img/${correct}/${i}.jpg" />
					</form></li>

			</c:forEach>
		</ul>
	</div>
	<div class="main">
		<div class="correct">
			<div id="background"
				style="background-image:url('${pageContext.request.contextPath}/Document/correct/img/${correct}/1.jpg'); background-size: 100% 100%; background-repeat: no-repeat;">
				<canvas id="canvas"></canvas>
			</div>

		</div>

	</div>
	<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"
		type="text/javascript" charset="utf-8"></script>
	<script src="${pageContext.request.contextPath}/js/correct.js"
		type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
			
				
	//获取id为background的对象
		var background = document.getElementById("background")
		<c:forEach var="a" begin="1" end="${correct_number}">
		//a代表当前条目的变量名称
		$("#img_${a}").click(function(){
			
			//获取批注的base64编码
			var image = new Image();
			image.src = canvas.toDataURL("image/png");
			var abc = image.src
			//base64 编码中含有大量加号，而+在 URL 传递时会被当成空格
			var abcd = abc.replace(/\+/g, '%2B');
			
			//获取id为correct中的backgroundImage值
			var a = document.getElementById("background").style.backgroundImage;
			//替代开头固定url地址方便用正则表达式过滤
			var n=a.replace('url("/correct/Document/correct/img/','%2');
			// 通过正则表达式得知当前正在批注的图片
			var str = n.match(/\/(\S*).jpg/)[1];
			// 替换id为background对象的背景图片
			document.getElementById("background").style.cssText = "background-image: url(${pageContext.request.contextPath}/Document/correct/img/${correct}/${a}.jpg);background-size: 100% 100%;background-repeat: no-repeat;"
			
			// 通过ajax将获取到的base64以post方式传输到后台处理
			var xmlhttp;
			if (window.XMLHttpRequest) {
				//  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
				xmlhttp = new XMLHttpRequest();
			} else {
				// IE6, IE5 浏览器执行代码
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			xmlhttp.open("POST","Encode", true);
			xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			xmlhttp.send("img_name="+str+"&base64="+abcd);
			//消除批注
			
			 cvs.clearRect(0,0,canvas.width,canvas.height);  
		});
		</c:forEach>
		//将图片以base64方式发送到后台
		$("#btn").click(function(){
		//获取批注的base64编码
			var image = new Image();
			image.src = canvas.toDataURL("image/png");
			var abc = image.src
			//base64 编码中含有大量加号，而+在 URL 传递时会被当成空格
			var abcd = abc.replace(/\+/g, '%2B');
			
			//获取id为correct中的backgroundImage值
			var a = document.getElementById("background").style.backgroundImage;
			//替代开头固定url地址方便用正则表达式过滤
			var n=a.replace('url("/correct/Document/correct/img/','%2');
			// 通过正则表达式得知当前正在批注的图片
			var str = n.match(/\/(\S*).jpg/)[1];
			
			// 通过ajax将获取到的base64以post方式传输到后台处理
			var xmlhttp;
			if (window.XMLHttpRequest) {
				//  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
				xmlhttp = new XMLHttpRequest();
			} else {
				// IE6, IE5 浏览器执行代码
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			xmlhttp.open("POST","Encode", true);
			xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			xmlhttp.send("img_name="+str+"&base64="+abcd);
			save_correct();
	});
				//通过ajax调用ImgToImg、ImgToPdf类使得原图片和批注图片合并生成对应的pdf文件
				function save_correct() {
				var xmlhttp;
				if(window.XMLHttpRequest) {
					//  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
					xmlhttp = new XMLHttpRequest();
				} else {
					// IE6, IE5 浏览器执行代码
					xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
					}
					xmlhttp.open("GET", "SaveCorrect", true);
					xmlhttp.send();
				}

	</script>
</body>

</html>