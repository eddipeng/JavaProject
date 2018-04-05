package cn.itcast.service;

import java.util.List;

import cn.itcast.dao.BookDao;
import cn.itcast.dao.BookDaoImple;
import cn.itcast.vo.Book;
import cn.itcast.vo.PageBean;

/**
 * 图书的业务类
 * @author Administrator
 *
 */
public class BookService {

	/**
	 * 查询所有分类的图书
	 * @return
	 */
	public List<Book> findAll() {
		BookDao dao = new BookDaoImple();
		return dao.findAll();
	}

	/**
	 * 根据分类查询图书
	 * @param cid
	 * @return
	 */
	public List<Book> findByCid(String cid) {
		BookDao dao = new BookDaoImple();
		return dao.findByCid(cid);
	}

	/**
	 * 通过图书的主键获取改本书
	 * @param bid
	 * @return
	 */
	public Book findByBid(String bid) {
		BookDao dao = new BookDaoImple();
		return dao.findByBid(bid);
	}

	/**
	 * 根据分类的主键修改图书的外键cid
	 * @param cid
	 */
	public void updateByCid(String cid) {
		BookDao dao = new BookDaoImple();
		dao.updateByCid(cid);
	}

	/**
	 * 分页查询
	 * @param pageCode
	 * @param pageSize
	 * @return
	 */
	public PageBean<Book> findByPage(int pageCode, int pageSize) {
		BookDao dao = new BookDaoImple();
		return dao.findByPage(pageCode,pageSize);
	}

	/**
	 * 添加图书
	 * @param book
	 */
	public void save(Book book) {
		BookDao dao = new BookDaoImple();
		dao.save(book);
	}

	/**
	 * 修改图书的信息
	 * @param book
	 */
	public void updateBook(Book book) {
		BookDao dao = new BookDaoImple();
		dao.updateBook(book);
	}

	/**
	 * 删除图书
	 * @param bid
	 */
	public void deleteByBid(String bid) {
		BookDao dao = new BookDaoImple();
		dao.deleteByBid(bid);
	}

}
