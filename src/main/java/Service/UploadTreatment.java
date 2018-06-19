package Service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * 对上传的文件作初步处理
 * @author sheng
 *
 */
public class UploadTreatment {
		/**
		 * 
		 * @throws Exception
		 */
		public static void Treatment () throws Exception {
		URL path = UploadTreatment.class.getResource("/../../");
		String Upload_Document_f = path + "Upload_Document/";
		String pdf_f = path + "Document/correct/pdf/";
		String img_f = path + "Document/correct/img/";
		String Upload_Document = Upload_Document_f.replace("file:/", "");
		String pdf = pdf_f.replace("file:/", "");
		String img = img_f.replace("file:/", "");
		File[] file = (new File(Upload_Document)).listFiles();
		for (int i = 0; i < file.length; i++) {
			if (file[i].isFile()) {
				// 复制文件
				CopyFile(file[i], new File(pdf + file[i].getName()));
			}
		}
		// 将PDF文件转成图片
		Service.PdfToImg.PTI(Upload_Document, img);
		// 清空临时文件夹内容
		DelAllFile(Upload_Document);
	}

	// 复制文件
	public static void CopyFile(File sourceFile, File targetFile) throws IOException {
		// 新建文件输入流并对它进行缓冲
		FileInputStream input = new FileInputStream(sourceFile);
		BufferedInputStream inBuff = new BufferedInputStream(input);

		// 新建文件输出流并对它进行缓冲
		FileOutputStream output = new FileOutputStream(targetFile);
		BufferedOutputStream outBuff = new BufferedOutputStream(output);

		// 缓冲数组
		byte[] b = new byte[1024 * 5];
		int len;
		while ((len = inBuff.read(b)) != -1) {
			outBuff.write(b, 0, len);
		}
		// 刷新此缓冲的输出流
		outBuff.flush();

		// 关闭流
		inBuff.close();
		outBuff.close();
		output.close();
		input.close();
	}
	// 清空临时文件夹内容
	public static void DelAllFile(String Upload_Document) {
		File file = new File(Upload_Document);
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (Upload_Document.endsWith(File.separator)) {
				temp = new File(Upload_Document + tempList[i]);
			} else {
				temp = new File(Upload_Document + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				DelAllFile(Upload_Document + "/" + tempList[i]);// 删除文件夹里面的文件
				System.out.println("临时文件夹删除成功！！");
			}
		}
	}
	
}
