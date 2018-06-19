package Service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

public class PdfToImg {

	public static void PTI(String Upload_Document, String img) throws Exception {
		File file = new File(Upload_Document);
		String[] fileName = file.list();
		for (int i = 0; i < fileName.length; i++) {
			String name = fileName[i].substring(0, fileName[i].lastIndexOf("."));
			String img_unmodified = img + name + "/";
			File files = new File(img_unmodified);
			if (!files.exists()) {
				files.mkdir();
			}
			File filess = new File(Upload_Document + name+".pdf");
			PDDocument doc = null;
			try {
				doc = PDDocument.load(filess);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (doc == null)
				return;
			PDFRenderer renderer = new PDFRenderer(doc);
			int pageCount = doc.getNumberOfPages();// 总页数
			for (int a = 0; a < pageCount; a++) {
				// 按页读取
				BufferedImage image = null;
				try {
					image = renderer.renderImage(a, 2.5f);
				} catch (IOException e) {
					System.out.println("第" + a + "出错！");
				}
				try {
					// 保存转换后的图片
					ImageIO.write(image, "PNG", new File(img_unmodified + (a + 1) + ".png"));
					System.out.println("图片转换成功");
				} catch (IOException e) {
					System.out.println("转换图片保存失败！");
				}
			}
			doc.close();
			Dao.CRUD.AddNumber(name, pageCount);
			InputStream is = new FileInputStream(new File(img_unmodified  + "1.png"));
			BufferedImage Imagesize = ImageIO.read(is);
			int width = Imagesize.getWidth();
			int height = Imagesize.getHeight();
			Dao.CRUD.AddImgWidth(name,width);
			Dao.CRUD.AddImgHeight(name,height);
		}
		
	}
}
