package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveCorrect extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String correct = (String) request.getSession().getAttribute("correct");
		int int_maxa = (int) request.getSession().getAttribute("correct_number");
			try {
				Service.ImgToImg.ITI(int_maxa, correct);
				System.out.println("调用成功");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
