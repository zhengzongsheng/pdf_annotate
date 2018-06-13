package Servlet;

import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
/**
 * 让servlet用于创建批注功能所要用的固定文件夹
 * 在tomcat开启时自动检测
 * @author sheng
 *
 */
public class CreateFolder extends HttpServlet {

	public void init() throws ServletException {
		String file_img = getServletContext().getRealPath("/Document/correct/img/");
		File uploadDir_img = new File(file_img);
		if (!uploadDir_img.exists()) {
			uploadDir_img.mkdirs();
		}
		String file_canvas = getServletContext().getRealPath("/Document/correct/canvas/");
		File uploadDir_canvas = new File(file_canvas);
		if (!uploadDir_canvas.exists()) {
			uploadDir_canvas.mkdirs();
		}
		String file_img_canvas = getServletContext().getRealPath("/Document/correct/img_canvas/");
		File uploadDir_img_canvas = new File(file_img_canvas);
		if (!uploadDir_img_canvas.exists()) {
			uploadDir_img_canvas.mkdirs();
		}
		String file_pdf = getServletContext().getRealPath("/Document/correct/pdf/");
		File uploadDir_pdf = new File(file_pdf);
		if (!uploadDir_pdf.exists()) {
			uploadDir_pdf.mkdirs();
		}
		String file_pdf_canvas = getServletContext().getRealPath("/Document/correct/pdf_canvas/");
		File uploadDir_pdf_canvas = new File(file_pdf_canvas);
		if (!uploadDir_pdf_canvas.exists()) {
			uploadDir_pdf_canvas.mkdirs();
		}

	}

}
