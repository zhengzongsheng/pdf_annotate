package Servlet;

import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
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
		String filename = a + "_.png";
		File folder = new File(path);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		//此处逻辑待优化
		File dest = new File(folder, filename);
		BASE64Decoder decoder = new BASE64Decoder();
		FileOutputStream write = new FileOutputStream(dest);
		// Base64解码
		byte[] bytes = decoder.decodeBuffer(replaces);
		write.write(bytes);
		write.close();
		File file = new File(path + filename);
		InputStream is = new FileInputStream(file);
		OutputStream os = new FileOutputStream(new File(path + a + ".png"));
		file.delete();
		BufferedImage prevImage = ImageIO.read(is);
		double width_ = prevImage.getWidth();
		double height_ = prevImage.getHeight();
		double width_percent = Dao.CRUD.GetImgWidth(correct) / width_;
		double height_percent = Dao.CRUD.GetImgHeight(correct) / height_;
		int newWidth = (int) (width_ * width_percent);
		int newHeight = (int) (height_ * height_percent);
		BufferedImage image = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_BGR);

		Graphics2D graphics = image.createGraphics();
		image = graphics.getDeviceConfiguration().createCompatibleImage(newWidth, newHeight, Transparency.TRANSLUCENT);
		graphics = image.createGraphics();
		// Graphics graphics = image.createGraphics();
		graphics.drawImage(prevImage, 0, 0, newWidth, newHeight, null);
		ImageIO.write(image, "png", os);
		os.flush();
		is.close();
		os.close();
	}

}
