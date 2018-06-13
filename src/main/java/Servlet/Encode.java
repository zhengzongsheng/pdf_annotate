package Servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.ImgToImg;
import sun.misc.BASE64Decoder;

/**
 * base64解码
 * 
 * @author sheng
 *
 */
public class Encode extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取canva图像的页数位置
		String a = request.getParameter("img_name");
		String ready = request.getParameter("ready");
		String base64 = request.getParameter("base64");
		String Str = new String(base64);
		// base64转图片需要删除开头,故用replace方法替换
		String replaces = Str.replace("data:image/png;base64,", "");
		String correct = (String) request.getSession().getAttribute("correct");
		String path = getServletContext().getRealPath("/") + File.separator + "/Document/correct/canvas/" + correct
				+ "/";
		String filename = a + ".jpg";
		File folder = new File(path);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		File dest = new File(folder, filename);
		BASE64Decoder decoder = new BASE64Decoder();
		FileOutputStream write = new FileOutputStream(dest);
		System.out.println(dest);
		System.out.println(path);
		// Base64解码
		byte[] bytes = decoder.decodeBuffer(replaces);
		write.write(bytes);
		write.close();
		

	}

}
