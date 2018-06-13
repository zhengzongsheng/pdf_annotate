//获取canvas的id
var canvas = document.getElementById('canvas');
//getContext() 方法返回一个用于在画布上绘图的环境
var cvs = canvas.getContext('2d');

var penWeight = 5; //画笔粗细
//通过获取选择器中指定range的值,对penWeight进行重新赋值,从而达到修改批注宽度的效果
$("#range").click(function() {
	penWeight = $(this).val();
});
var penColor = '#f00'; //画笔颜色
//通过获取选择器中指定li的data-value值,对penColor进行重新赋值,从而达到修改批注颜色的效果
$("ul.color_list li").click(function() {
	penColor = $(this).attr("data-value");
	$(".color_select").css("background-color", penColor);
});
var a = -($("#background").offset().left); //获取div到浏览器右边距的距离
var b = -($("#background").offset().top); //获取div到浏览器顶端的距离
//fillStyle 属性设置或返回用于填充绘画的颜色
//防止保存转换出的图片背景为黑色
cvs.fillStyle = 'rgba(255, 255, 255, 0)';
canvas.onmousedown = function(e) {
	/*找到鼠标（画笔）的坐标*/
	var pageX = e.pageX;
	var pageY = e.pageY;
	if (pageX === undefined) { //IE8及更早版本
		pageX = e.clientX + (document.body.scrollLeft || document.documentElement.scrollLeft);
	}
	if (pageY === undefined) {
		pageY = e.clientY + (document.body.scrollTop || document.documentElement.scrollTop);
	}
	var start_x = a + pageX;
	var start_y = b + pageY;
	cvs.beginPath(); //开始本次绘画
	console.log("鼠标坐标 x坐标:" + start_x + "y坐标:" + start_y)
	cvs.moveTo(start_x, start_y); //画笔起始点

	/*设置画笔属性*/
	cvs.lineCap = 'round';
	cvs.lineJoin = "round";
	cvs.strokeStyle = penColor; //画笔颜色
	cvs.lineWidth = penWeight; //画笔粗细

	canvas.onmousemove = function(e) {
		var pageX = e.pageX;
		var pageY = e.pageY;
		if (pageX === undefined) { //IE8及更早版本
			pageX = e.clientX + (document.body.scrollLeft || document.documentElement.scrollLeft);
		}
		if (pageY === undefined) {
			pageY = e.clientY + (document.body.scrollTop || document.documentElement.scrollTop);
		}
		/*找到鼠标（画笔）的坐标*/
		//此处要改 不兼容其他分辨率

		var move_x = a + pageX;
		var move_y = b + pageY;

		cvs.lineTo(move_x, move_y);
		//lineTo(x,y)Canvas 2D API 的方法x, y使用直线将子路径中的最后一个点连接到坐标（但不实际绘制它）
		//添加一个新点，然后创建从该点到画布中最后指定点的线条
		cvs.stroke(); //立即渲染 [strəʊk]
	//stroke() Canvas 2D API 的方法使用当前笔划样式描绘当前或给定路径。
	}
	canvas.onmouseup = function(e) {

		cvs.closePath(); //结束本次绘画

		canvas.onmousemove = null;
		canvas.onmouseup = null;
	}
}
//清空画布事件
//利用canvas的特性 每当高度或宽度被重设时，画布内容就会被清空
var btn_clear = document.getElementById('clear_canvas');
btn_clear.onclick = function() {
	canvas.width = canvas.height;
}

//此处ajax只能返回字符串 所以采用location.assign()方法跳转页面。
$("#download").click(function() {
	window.location.assign("DownloadDocument")
});


//$(".correct").width()+
$(".color_select").click(function() {
	$("#color_fadeToggle").fadeToggle();
});
document.getElementById("canvas").setAttribute("width", $("#background").width()+"px")
document.getElementById("canvas").setAttribute("height", $(".correct").height()+"px")