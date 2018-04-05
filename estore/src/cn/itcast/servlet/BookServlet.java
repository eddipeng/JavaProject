package cn.itcast.servlet;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.itcast.service.BookService;
import cn.itcast.service.CategoryService;
import cn.itcast.vo.Book;
import cn.itcast.vo.Category;
import cn.itcast.vo.PageBean;

public class BookServlet extends BaseServlet {
	private static final long serialVersionUID = 4485461378695529458L;

	/**
	 * 查询所有分类的图书
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response){
		// 调用业务层的代码
		BookService bs = new BookService();
		// 查询所有的分类的图书
		List<Book> bookList = bs.findAll();
		// 存入到request域中
		request.setAttribute("bookList", bookList);
		return "/jsps/book/list.jsp";
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String findByCid(HttpServletRequest request, HttpServletResponse response){
		// 接收分类的主键
		String cid = request.getParameter("cid");
		// 调用业务层的代码
		BookService bs = new BookService();
		// 根据分类查询图书
		List<Book> bookList = bs.findByCid(cid);
		// 存入到request域中
		request.setAttribute("bookList", bookList);
		return "/jsps/book/list.jsp";
	}
	
	/**
	 * 通过图书的主键查询是一本书
	 * @param request
	 * @param response
	 * @return
	 */
	public String findByBid(HttpServletRequest request, HttpServletResponse response){
		// 接收图书的主键
		String bid = request.getParameter("bid");
		BookService bs = new BookService();
		// 通过图书的主键获取改本书
		Book book = bs.findByBid(bid);
		// 存入到域中
		request.setAttribute("book", book);
		// 转发到页面
		return "/jsps/book/desc.jsp";
	}
	
	/**
	 * 分页的查询所有的图书
	 * @param request
	 * @param response
	 * @return
	 */
	public String findByPage(HttpServletRequest request, HttpServletResponse response){
		// 从页面获取的当前页
		int pageCode = getPageCode(request);
		// 定义每页显示的数据条数
		int pageSize = 3;
		
		BookService bs = new BookService();
		// 分页查询
		// page = pageCode=当前页（从页面获取）  pageSize=一页显示多少条数据（自定义） 	
		// totalCount=总记录数（从数据库查询）	 beanList=查询图书的真实数据（从数据库中查询 limit ）
		PageBean<Book> page = bs.findByPage(pageCode,pageSize);
		
		request.setAttribute("page", page);
		return "/adminjsps/admin/book/list.jsp";
	}
	
	/**
	 * 获取pageCode的值
	 * @param request
	 * @return
	 */
	public int getPageCode(HttpServletRequest request){
		String pc = request.getParameter("pc");
		// pc是空的
		if(pc == null || pc.trim().isEmpty()){
			// 查询当前是第一页
			return 1;
		}
		return Integer.parseInt(pc);
	}
	
	/**
	 * 查询所有的分类，跳转添加图书的页面
	 * @param request
	 * @param response
	 * @return
	 */
	public String initAddBook(HttpServletRequest request, HttpServletResponse response){
		// 查询所有凤分类
		CategoryService cs = new CategoryService();
		List<Category> cList = cs.findAllCate();
		request.setAttribute("cList", cList);
		// 转发添加图书的页面
		return "/adminjsps/admin/book/add.jsp";
	}
	
	/**
	 * 查询图书的详细信息（后台的功能）
	 * @param request
	 * @param response
	 * @return
	 */
	public String findByBidAdmin(HttpServletRequest request, HttpServletResponse response){
		// 接收图书的id
		String bid = request.getParameter("bid");
		BookService bs = new BookService();
		// 通过图书的id查询图书的信息
		Book book = bs.findByBid(bid);
		
		// 获取所有的分类的信息
		CategoryService cs = new CategoryService();
		List<Category> cList = cs.findAllCate();
		
		request.setAttribute("book", book);
		request.setAttribute("cList", cList);
		return "/adminjsps/admin/book/desc.jsp";
	}
	
	/**
	 * 修改图书方法
	 * @param request
	 * @param response
	 * @return
	 */
	public String mod(HttpServletRequest request, HttpServletResponse response){
		// 接收数据
		Map<String, String[]> map = request.getParameterMap();
		Book book = new Book();
		try {
			// 用户的修改的信息
			BeanUtils.populate(book, map);
			// 修改数据库
			BookService bs = new BookService();
			// 修改的方法
			bs.updateBook(book);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return findByPage(request,response);
	}
	
	/**
	 * 删除图书的方法
	 * @param request
	 * @param response
	 * @return
	 */
	public String del(HttpServletRequest request, HttpServletResponse response){
		// 获取图书的主键
		String bid = request.getParameter("bid");
		BookService bs = new BookService();
		bs.deleteByBid(bid);
		
		return findByPage(request,response);
	}

}










