package cn.itcast.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletDemo extends BaseServlet {

	/**
	 * 测试
	 */
	private static final long serialVersionUID = 2947357007498494328L;

	public void updateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("update...");
		
	}

	public String addUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("addUser...");
		/*//想做重定向
		response.sendRedirect("重定向地址");
		return null;*/
		return "/jsp/msg.jsp";
		
	}
	
	public void deleteUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("delete...");
	}

}
