//获取canvas的id
var canvas = document.getElementById('canvas');
// getContext() 方法返回一个用于在画布上绘图的环境
var cvs = canvas.getContext('2d');

var penWeight = 5; // 画笔粗细
// 通过获取选择器中指定range的值,对penWeight进行重新赋值,从而达到修改批注宽度的效果
$("#range").click(function() {
	penWeight = $(this).val();
});
var penColor = '#f00'; // 画笔颜色
// 通过获取选择器中指定li的data-value值,对penColor进行重新赋值,从而达到修改批注颜色的效果
$("ul.color_list li").click(function() {
	penColor = $(this).attr("data-value");
	$(".color_select").css("background-color", penColor);
});
var a = -($("#background").offset().left); // 获取div到浏览器右边距的距离
var b = -($("#background").offset().top); // 获取div到浏览器顶端的距离
// fillStyle 属性设置或返回用于填充绘画的颜色
// 防止保存转换出的图片背景为黑色
cvs.fillStyle = 'rgba(255, 255, 255, 0)';
canvas.onmousedown = function(e) {

	/* 找到鼠标（画笔）的坐标 */
	var pageX = e.pageX;
	var pageY = e.pageY;
	if (pageX === undefined) { // IE8及更早版本
		pageX = e.clientX
				+ (document.body.scrollLeft || document.documentElement.scrollLeft);
	}
	if (pageY === undefined) {
		pageY = e.clientY
				+ (document.body.scrollTop || document.documentElement.scrollTop);
	}
	var start_x = a + pageX;
	var start_y = b + pageY;
	cvs.beginPath(); // 开始本次绘画
	console.log("鼠标坐标 x坐标:" + start_x + "y坐标:" + start_y)
	cvs.moveTo(start_x, start_y); // 画笔起始点

	/* 设置画笔属性 */
	cvs.lineCap = 'round';
	cvs.lineJoin = "round";
	cvs.strokeStyle = penColor; // 画笔颜色
	cvs.lineWidth = penWeight; // 画笔粗细

	canvas.onmousemove = function(e) {
		var pageX = e.pageX;
		var pageY = e.pageY;
		if (pageX === undefined) { // IE8及更早版本
			pageX = e.clientX
					+ (document.body.scrollLeft || document.documentElement.scrollLeft);
		}
		if (pageY === undefined) {
			pageY = e.clientY
					+ (document.body.scrollTop || document.documentElement.scrollTop);
		}
		/* 找到鼠标（画笔）的坐标 */
		// 此处要改 不兼容其他分辨率
		var move_x = a + pageX;
		var move_y = b + pageY;

		cvs.lineTo(move_x, move_y);
		// lineTo(x,y)Canvas 2D API 的方法x, y使用直线将子路径中的最后一个点连接到坐标（但不实际绘制它）
		// 添加一个新点，然后创建从该点到画布中最后指定点的线条
		cvs.stroke(); // 立即渲染 [strəʊk]
		// stroke() Canvas 2D API 的方法使用当前笔划样式描绘当前或给定路径。
	}
	canvas.onmouseup = function(e) {

		cvs.closePath(); // 结束本次绘画

		canvas.onmousemove = null;
		canvas.onmouseup = null;
	}
}
// 显示修改完成标志
$("canvas").click(function() {
	// 获取批注的base64编码
	var image = new Image();
	image.src = canvas.toDataURL("image/png");
	var abc = image.src
	// base64 编码中含有大量加号，而+在 URL 传递时会被当成空格
	var base64_ = abc.replace(/\+/g, '%2B');
	// 获取id为correct中的backgroundImage值
	var a = document.getElementById("background").style.backgroundImage;
	// 通过正则表达式得知当前正在批注的图片
	var str = a.match(/([^/]+).png/)[1];
	// 显示修改完成标志
	var annotated = "#img_" + str
	$(annotated).addClass("annotated");
})
// 清空画布事件
$("#clear_canvas").click(function() {
	// canvas.width = canvas.height; 用此方法会改变canvas画布大小
	// 此处用clearRect()方法
	cvs.clearRect(0, 0, canvas.width, canvas.height);
})
// 此处ajax只能返回字符串 所以采用location.assign()方法跳转页面。
$("#download").click(function() {
	window.location.assign("DownloadDocument")
});
// 通过ajax调用ImgToImg、ImgToPdf类使得原图片和批注图片合并生成对应的pdf文件
function save_correct() {
	var xmlhttp;
	if (window.XMLHttpRequest) {
		// IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
		xmlhttp = new XMLHttpRequest();
	} else {
		// IE6, IE5 浏览器执行代码
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.open("GET", "SaveCorrect", true);
	xmlhttp.send();
}
// 将图片以base64方式发送到后台
$("#save_canvas")
		.click(
				function() {
					// 获取批注的base64编码
					var image = new Image();
					image.src = canvas.toDataURL("image/png");
					var abc = image.src
					// base64 编码中含有大量加号，而+在 URL 传递时会被当成空格
					var base64_ = abc.replace(/\+/g, '%2B');

					// 获取id为correct中的backgroundImage值
					var a = document.getElementById("background").style.backgroundImage;
					// 通过正则表达式得知当前正在批注的图片
					var str = a.match(/([^/]+).png/)[1];

					// 通过ajax将获取到的base64以post方式传输到后台处理
					var xmlhttp;
					if (window.XMLHttpRequest) {
						// IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
						xmlhttp = new XMLHttpRequest();
					} else {
						// IE6, IE5 浏览器执行代码
						xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
					}
					xmlhttp.open("POST", "Encode", true);
					xmlhttp.setRequestHeader("Content-Type",
							"application/x-www-form-urlencoded");
					xmlhttp.send("img_name=" + str + "&base64=" + base64_);
					save_correct();
				});
// 渐变动画效果
$(".color_select").click(function() {
	$("#color_fadeToggle").fadeToggle();
});
// 自适应画板的大小
$("#canvas").attr({
	width : $(".correct").width(),
	height : $(".correct").height()
});
