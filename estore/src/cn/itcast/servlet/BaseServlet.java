package cn.itcast.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {

	/**
	 * 通用的servlet
	 */
	private static final long serialVersionUID = 3156401394761547677L;
	
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		
		/*		//可以进行一些判断
		//对用户有一些要求,传过来要调用的方法
		String methodName = req.getParameter("method");
		if ("addUser".equals(methodName)) {
			addUser(req, res);
		}else if ("updateUser".equals(methodName)) {
			updateUser(req, res);
		}else if ("deleteUser".equals(methodName)) {
			deleteUser(req, res);
		}*/
		
		//设置编码
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		
		//用反射技术,获取class,获取的method对象
		String methodName = req.getParameter("method");
		if (methodName==null||methodName.trim().isEmpty()) {
			throw new RuntimeException("需要传入method参数");
		}
		Class clazz = this.getClass();
		Method method = null;
		try {
			method = clazz.getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
		} catch (Exception e) {
 			e.printStackTrace();
		}
		//现在method有值了
		try {
			String result = (String) method.invoke(this, req,res);
			if (result != null && !result.trim().isEmpty()) {
				req.getRequestDispatcher(result).forward(req, res);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
