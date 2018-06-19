package Service;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImgToImg {
	/**
	 * 
	 * @param int_maxa
	 *            pdf或批注图片数量(两者是相等的)
	 * @param s
	 *            pdf文件名
	 * @throws Exception
	 */
	public static void ITI(int int_maxa, String s) throws Exception {
		URL path = UploadTreatment.class.getResource("/../../");
		String absolute_path_r = path + "Document/correct/";
		String absolute_path = absolute_path_r.replace("file:/", "");

		for (int i = 1; i <= int_maxa; i++) {
			String sourceFilePath = absolute_path + "img/" + s + "/" + i + ".png";
			String canvasFilePath = absolute_path + "canvas/" + s + "/" + i + ".png";
			String saveFilePath = absolute_path + "img_canvas/" + s + "/" + i + ".png";
			File folder = new File(saveFilePath);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			// 构建叠加层
			BufferedImage buffImg = watermark(new File(sourceFilePath), new File(canvasFilePath), 0, 0, 1.0f);
			// 输出水印图片
			generateWaterFile(buffImg, saveFilePath);
		}
		Service.ImgToPdf.ITP(int_maxa, s);
	}

	/**
	 * 
	 * @param file
	 *            pdf原图
	 * @param waterFile
	 *            批注图片
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage watermark(File file, File waterFile, int x, int y, float alpha) throws IOException {
		// 获取底图
		BufferedImage buffImg = ImageIO.read(file);
		// 获取层图
		BufferedImage waterImg = ImageIO.read(waterFile);
		// 创建Graphics2D对象，用在底图对象上绘图
		Graphics2D g2d = buffImg.createGraphics();
		int waterImgWidth = waterImg.getWidth();// 获取层图的宽度
		int waterImgHeight = waterImg.getHeight();// 获取层图的高度
		// 在图形和图像中实现混合和透明效果
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
		// 绘制
		g2d.drawImage(waterImg, x, y, waterImgWidth, waterImgHeight, null);
		g2d.dispose();// 释放图形上下文使用的系统资源
		return buffImg;
	}

	/**
	 * 
	 * @param buffImg
	 *            合并后的图片
	 * @param savePath
	 *            合并后图片的保存路径
	 */
	static void generateWaterFile(BufferedImage buffImg, String savePath) {
		int temp = savePath.lastIndexOf(".") + 1;
		try {
			ImageIO.write(buffImg, savePath.substring(temp), new File(savePath));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
