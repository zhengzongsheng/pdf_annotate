package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 在主页面中挑选指定要批注文件,表单将自动提交到该servlet
 * 设两个session值 分别为correct和correct_number
 * 分别是存放所选文件名和所选文件的页数
 * @author sheng
 *
 */
public class GetNumber extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String radio = request.getParameter("radio");
		request.getSession().setAttribute("correct", radio);
		request.getSession().setAttribute("correct_number", Dao.CRUD.GetNumber(radio));
		request.getServletContext().getRequestDispatcher("/correct.jsp").forward(request, response);
	}

}
