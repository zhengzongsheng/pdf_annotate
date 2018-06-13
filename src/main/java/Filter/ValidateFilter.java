package Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 * 用于防止未选择批注文件就强制进入批注网页
 * 
 * @author sheng
 *
 */
public class ValidateFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("----过滤器初始化----");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request1 = (HttpServletRequest) request;
		HttpServletResponse response1 = (HttpServletResponse) response;
		// HttpSession session = request1.getSession();
		String a = (String) request1.getSession().getAttribute("correct");
		if (a == null) {
			response1.sendRedirect("index.jsp");
		}
		chain.doFilter(request, response);
	}

	public void destroy() {
		System.out.println("----过滤器销毁----");
	}
}
