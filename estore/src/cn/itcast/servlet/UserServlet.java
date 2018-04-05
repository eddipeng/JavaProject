package cn.itcast.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.itcast.service.UserService;
import cn.itcast.vo.User;

import com.sun.org.apache.commons.beanutils.BeanUtils;

public class UserServlet extends BaseServlet {

	/**
	 * 和用户相关的servlet
	 */
	private static final long serialVersionUID = -8230838990948681763L;

	public String registUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			//接收数据,封装,处理,显示
		Map<String,String[]> map = request.getParameterMap();
		User user = new User();
		try {
			BeanUtils.populate(user, map);
			//处理数据,调用业务层
			UserService us = new UserService();
			boolean flag = us.regisUser(user);
			//根据返回的结果调用不同的页面处理
			if (flag == false) {
				//注册失败,向request域中存msg
				request.setAttribute("msg", "注册失败!");
			}else {
				request.setAttribute("msg", "注册成功");
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return "/jsps/msg.jsp";
		
	}
	/*
	 * 激活邮件
	 */
	public String active(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取code
		String code = request.getParameter("code");
		UserService us = new UserService();
		//通过激活码查到的用户
		User user = us.findUserByCode(code);
		//判断
		if (user == null) {
			//没查到
			request.setAttribute("msg", "激活失败!");
		}else {
			//修改用户状态
			user.setState(1);
			us.updateUser(user);
			request.setAttribute("msg", "激活成功请登录");
		}
		return "/jsps/msg.jsp";
		
	}
	
	/*
	 * 登录方法
	 */
	public String login(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException {
		Map<String, String[]> map = request.getParameterMap();
		User user = new User();
		try {
			BeanUtils.populate(user, map);
			UserService us = new UserService();
			User existUser = us.login(user);
			if (existUser == null) {
				request.setAttribute("msg", "用户名密码错误或未激活!");
				return "/jsps/msg.jsp";
			} else {
				//用户信息存入session
				HttpSession session = request.getSession();
				session.setAttribute("existUser", existUser);
				//转发到main.jsp的页面
				return "/jsps/main.jsp";
			}
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
		
	}
	public String exit(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		//获取session然后销毁
		request.getSession().invalidate();
		return "/jsps/main.jsp";
		
	}
}
