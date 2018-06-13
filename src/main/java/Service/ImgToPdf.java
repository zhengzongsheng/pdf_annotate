package Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

public class ImgToPdf {
	/**
	 * 
	 * @param a
	 *            合并后图片的数量
	 * @param correct
	 *            合成后pdf的名字
	 */
	public static void ITP(int a, String correct) {
		ArrayList<String> imageUrllist = new ArrayList<String>();
		URL path = UploadTreatment.class.getResource("/../../");
		String absolute_path_r = path + "Document/correct/";
		String absolute_path = absolute_path_r.replace("file:/", "");
		for (int s = 1; s <= a; s++) {
			imageUrllist.add(absolute_path + "img_canvas/" + correct + "/" + s + ".jpg");
		}
		String pdfUrl = absolute_path + "pdf_canvas/" + correct + ".pdf";
		File file = Pdf(imageUrllist, pdfUrl);
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param imageUrllist
	 * @param mOutputPdfFileName
	 * @return
	 */
	public static File Pdf(ArrayList<String> imageUrllist, String mOutputPdfFileName) {
		String TAG = "PdfManager";
		Document doc = new Document(PageSize.A4, 20, 20, 20, 20);
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(mOutputPdfFileName));
			doc.open();
			for (int i = 0; i < imageUrllist.size(); i++) {
				doc.newPage();
				Image png1 = Image.getInstance(imageUrllist.get(i));
				float heigth = png1.getHeight();
				float width = png1.getWidth();
				int percent = getPercent2(heigth, width);
				png1.setAlignment(Image.MIDDLE);
				png1.scalePercent(percent + 3);// 表示是原来图像的比例;
				doc.add(png1);
			}
			doc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		File mOutputPdfFile = new File(mOutputPdfFileName);
		if (!mOutputPdfFile.exists()) {
			mOutputPdfFile.deleteOnExit();
			return null;
		}
		return mOutputPdfFile;
	}

	public static int getPercent(float h, float w) {
		int p = 0;
		float p2 = 0.0f;
		if (h > w) {
			p2 = 297 / h * 100;
		} else {
			p2 = 210 / w * 100;
		}
		p = Math.round(p2);
		return p;
	}

	public static int getPercent2(float h, float w) {
		int p = 0;
		float p2 = 0.0f;
		p2 = 530 / w * 100;
		p = Math.round(p2);
		return p;
	}
}
