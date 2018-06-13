
var from_auto = document.getElementById("form_auto");
form_auto.addEventListener("change", function() {
	form_auto.submit();
});
$(document).on({
	dragleave : function(e) { //拖离
		e.preventDefault();
	},
	drop : function(e) { //拖后放
		e.preventDefault();
	},
	dragenter : function(e) { //拖进
		e.preventDefault();
	},
	dragover : function(e) { //拖来拖去
		e.preventDefault();
	}
});
var box = document.getElementById('drop_area'); //拖拽区域
box.addEventListener("drop",
	function(e) {
		var fileList = e.dataTransfer.files; //获取文件对象
		//检测是否是拖拽文件到页面的操作
		if (fileList.length == 0) {
			return false;
		}
		//检测文件是不是pdf文件
		if (fileList[0].type.indexOf('pdf') === -1) {
			alert("请拖拽pdf文件");
			return false;
		}
		//上传
		xhr = new XMLHttpRequest();
		xhr.open("post", "/correct/UploadServlet", true);
		xhr.setRequestHeader("X-Requested-With", "XMLHttpRequest");
		var fd = new FormData();
		fd.append('pdf', fileList[0]);
		xhr.send(fd);

	},
	false);
/**
 * 该函数用于获取数据库中所有pdf文件名
 */
function scan_filename() {
	var xmlhttp;
	if (window.XMLHttpRequest) {
		//  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
		xmlhttp = new XMLHttpRequest();
	} else {
		// IE6, IE5 浏览器执行代码
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.open("GET", "Select", true);
	xmlhttp.send();
}