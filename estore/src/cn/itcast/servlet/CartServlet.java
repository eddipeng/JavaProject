package cn.itcast.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.service.BookService;
import cn.itcast.vo.Book;
import cn.itcast.vo.Cart;
import cn.itcast.vo.CartItem;

public class CartServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3836973763188117391L;

	public String addCart(HttpServletRequest request,HttpServletResponse response) {
		//先session获取购物车
		Cart cart = getCart(request);
		//向车存东西
		//先把购物项item准备好,怎么准备购物项?(书自己封装),数量传过来,小计(计算)
		CartItem item = new CartItem();
		String scount = request.getParameter("count");
		int count = Integer.parseInt(scount);
		item.setCount(count);
		//存入书的信息,获取book,通过book主键
		String bookId = request.getParameter("bookId");
		BookService bs = new BookService();
		Book book = bs.findByBid(bookId);
		item.setBook(book);
		cart.addCart(item);
		return "/jsps/cart/list.jsp";
	}
	
	/*
	 * 从session获取购物车
	 */
	public Cart getCart(HttpServletRequest request) {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart==null) {
//			没有购物车,创建
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	/*
	 * 移除指定的购物项
	 */
	public String removeCart(HttpServletRequest request,HttpServletResponse response) {
		//接收数据
		String bookId = request.getParameter("bid");
		//获取车
		Cart cart = getCart(request);
//		调用车里的方法
		cart.removeCartItem(bookId);
		return "/jsps/cart/list.jsp";
	}
	public String clearCart(HttpServletRequest request,HttpServletResponse response) {
		
		//获取车
		Cart cart = getCart(request);
//		调用车里的方法
		cart.clearCart();
		return "/jsps/cart/list.jsp";
	}

}
