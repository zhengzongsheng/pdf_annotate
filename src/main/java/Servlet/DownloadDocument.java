package Servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 批注完提供下载功能
 * 
 * @author sheng
 *
 */
public class DownloadDocument extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1.获取要下载的文件的绝对路径
		String realPath = this.getServletContext()
				.getRealPath("/Document/correct/pdf_canvas/" + request.getSession().getAttribute("correct") + ".pdf");
		// 2.获取要下载的文件名
		String fileName = realPath.substring(realPath.lastIndexOf("\\") + 1);
		// 3.设置content-disposition响应头控制浏览器以下载的形式打开文件
		response.setHeader("content-disposition", "attachment;filename=" + fileName);
		// 4.获取要下载的文件输入流
		InputStream in = new FileInputStream(realPath);
		int len = 0;
		// 5.创建数据缓冲区
		byte[] buffer = new byte[1024];
		// 6.通过response对象获取OutputStream流
		OutputStream out = response.getOutputStream();
		// 7.将FileInputStream流写入到buffer缓冲区
		while ((len = in.read(buffer)) > 0) {
			// 8.使用OutputStream将缓冲区的数据输出到客户端浏览器
			out.write(buffer, 0, len);
		}
		in.close();
		request.getSession().removeAttribute("");
	}

}
