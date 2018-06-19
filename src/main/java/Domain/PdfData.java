package Domain;
/**
 * javabean
 * @author sheng
 *
 */
public class PdfData {
	private int ID;
	private String Name_File;
	private int PDF_Number;
	private String Img_Path;
	private int Img_Height;
	private int Img_Width;
	public PdfData() {

	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName_File() {
		return Name_File;
	}

	public void setName_File(String name_File) {
		Name_File = name_File;
	}

	public int getPDF_Number() {
		return PDF_Number;
	}

	public void setPDF_Number(int pDF_Number) {
		PDF_Number = pDF_Number;
	}

	public String getImg_Path() {
		return Img_Path;
	}

	public void setImg_Path(String img_Path) {
		Img_Path = img_Path;
	}
	@Override
    public String toString() {
        return Name_File;
    }

	public int getImg_Height() {
		return Img_Height;
	}

	public void setImg_Height(int img_Height) {
		Img_Height = img_Height;
	}

	public int getImg_Width() {
		return Img_Width;
	}

	public void setImg_Width(int img_Width) {
		Img_Width = img_Width;
	}
}
