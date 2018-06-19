package Dao;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Domain.PdfData;
import org.apache.ibatis.session.SqlSession;

/**
 * 此类用于对数据库进行常规的CRUD
 * 
 * @author sheng
 *
 */
public class CRUD {
	/**
	 * 用于上传pdf文件名到数据库
	 * 
	 * @param upload_Document
	 *            pdf文件名
	 */
	public static void AddName(String upload_Document) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
		String statement = "Dao.PdfDataMapper.addname";
		Domain.PdfData p = new Domain.PdfData();
		p.setName_File(upload_Document);
		int retResult = sqlSession.insert(statement, p);
		sqlSession.close();
	}

	/**
	 * 用于上传pdf文件中图片的宽度
	 * 
	 * @param imgwidth
	 *            pdf文件中图片的宽度
	 */
	public static void AddImgWidth(String upload_Document, int imgwidth) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
		String statement = "Dao.PdfDataMapper.addimgwidth";
		Domain.PdfData p = new Domain.PdfData();
		p.setName_File(upload_Document);
		p.setImg_Width(imgwidth);
		int retResult = sqlSession.insert(statement, p);
		sqlSession.close();
	}

	/**
	 * 用于上传pdf文件中图片的高度
	 * 
	 * @param imgheight
	 *            pdf文件中图片的高度
	 */
	public static void AddImgHeight(String upload_Document, int imgheight) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
		String statement = "Dao.PdfDataMapper.addimgheight";
		Domain.PdfData p = new Domain.PdfData();
		p.setName_File(upload_Document);
		p.setImg_Height(imgheight);
		int retResult = sqlSession.insert(statement, p);
		sqlSession.close();
	}

	/**
	 * 用于上传pdf页数到数据库
	 * 
	 * @param upload_Document
	 *            pdf文件名
	 * @param PDF_Number
	 *            pdf转换成图片后的图片个数
	 */
	public static void AddNumber(String upload_Document, int PDF_Number) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
		String statement = "Dao.PdfDataMapper.addnumber";
		Domain.PdfData p = new Domain.PdfData();
		p.setName_File(upload_Document);
		p.setPDF_Number(PDF_Number);
		int retResult = sqlSession.insert(statement, p);
		sqlSession.close();
	}

	/**
	 * 用于从数据库获取pdf页数,此处获取到的值用正则表达式做了处理,用到的相关方法为getSubUtilSimple()
	 * 
	 * @param upload_Document
	 *            pdf文件名
	 * @return pdf转换成图片后的图片个数
	 */
	public static int GetNumber(String upload_Document) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		String statement = "Dao.PdfDataMapper.getnumber";
		Domain.PdfData p = new Domain.PdfData();
		p.setName_File(upload_Document);
		List<String> retResult = (List) sqlSession.selectList(statement, p);
		String str = retResult.toString();
		String rgex = "=(.*?)}";
		String a = getSubUtilSimple(str, rgex);
		int i = Integer.parseInt(a);
		sqlSession.close();
		return i;
	}

	/**
	 * 用于获取pdf文件中图片的宽度
	 * 
	 * @param upload_Document
	 *            pdf文件名
	 * @return pdf文件中图片的宽度
	 */
	public static int GetImgWidth(String upload_Document) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		String statement = "Dao.PdfDataMapper.getimgwidth";
		Domain.PdfData p = new Domain.PdfData();
		p.setName_File(upload_Document);
		List<String> retResult = (List) sqlSession.selectList(statement, p);
		String str = retResult.toString();
		String rgex = "=(.*?)}";
		String a = getSubUtilSimple(str, rgex);
		int i = Integer.parseInt(a);
		sqlSession.close();
		return i;
	}

	/**
	 * 用于获取pdf文件中图片的高度
	 * 
	 * @param upload_Document
	 *            pdf文件名
	 * @return pdf文件中图片的高度
	 */
	public static int GetImgHeight(String upload_Document) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		String statement = "Dao.PdfDataMapper.getimgheight";
		Domain.PdfData p = new Domain.PdfData();
		p.setName_File(upload_Document);
		List<String> retResult = (List) sqlSession.selectList(statement, p);
		String str = retResult.toString();
		String rgex = "=(.*?)}";
		String a = getSubUtilSimple(str, rgex);
		int i = Integer.parseInt(a);
		sqlSession.close();
		return i;
	}


	/**
	 * 用于从数据库获取所用文件名
	 * 
	 * @return 返回list类型的文件名
	 */
	public static List<Object> GetName() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		String statement = "Dao.PdfDataMapper.getname";
		List<Object> user = sqlSession.selectList(statement);
		sqlSession.close();
		System.out.println(user);
		return user;
	}

	/**
	 * 
	 * @param soap
	 *            需要被检索的文本
	 * @param rgex
	 *            正则表达式
	 * @return 返回检索完毕的文本
	 */
	// 正则表达式
	public static String getSubUtilSimple(String soap, String rgex) {
		Pattern pattern = Pattern.compile(rgex);
		Matcher m = pattern.matcher(soap);
		while (m.find()) {
			return m.group(1);
		}
		return "";
	}

}
