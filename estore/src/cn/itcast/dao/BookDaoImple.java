package cn.itcast.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.utils.MyJdbcUtil;
import cn.itcast.vo.Book;
import cn.itcast.vo.PageBean;

/**
 * 图书的实现类
 * @author Administrator
 *
 */
public class BookDaoImple implements BookDao {

	/**
	 * 查询所有分类的图书
	 */
	public List<Book> findAll() {
		QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		try {
			return runner.query("select * from book where isdel = ?", new BeanListHandler<Book>(Book.class), 0);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据分类查询图书
	 * @param cid
	 * @return
	 */
	public List<Book> findByCid(String cid) {
		QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		try {
			return runner.query("select * from book where cid = ? and isdel = ?", new BeanListHandler<Book>(Book.class), cid,0);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 通过图书的主键获取改本书
	 * @param bid
	 * @return
	 */
	public Book findByBid(String bid) {
		QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		try {
			return runner.query("select * from book where bid = ?", new BeanHandler<Book>(Book.class), bid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 */
	public void updateByCid(String cid) {
		QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		String sql = "update book set cid = null where cid = ? and isdel = 1";
		try {
			runner.update(sql, cid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 分页查询图书
	 */
	public PageBean<Book> findByPage(int pageCode, int pageSize) {
		// 创建
		PageBean<Book> page = new PageBean<Book>();
		// 设置值
		page.setPageCode(pageCode);
		page.setPageSize(pageSize);
		
		// 查询数据库，查询数据库中图书的总数量（isdel=0）
		QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		try {
			String countSql = "select count(*) from book where isdel = 0";
			// 查询数据库，查询数据库中图书的总数量（isdel=0）
			long count = (Long) runner.query(countSql, new ScalarHandler());
			int totalCount = (int) count;
			// 图书的总数量
			page.setTotalCount(totalCount);
			
			// 查询当前页显示的图书的信息
			String limitSql = "select * from book where isdel = 0 limit ?,?";
			List<Book> beanList = runner.query(limitSql, new BeanListHandler<Book>(Book.class), (pageCode-1)*pageSize,pageSize);
			page.setBeanList(beanList);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return page;
	}
	
	/**
	 * 添加图书
	 */
	public void save(Book book) {
		QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		String sql = "insert into book values (?,?,?,?,?,?,?)";
		Object [] params = {book.getBid(),book.getBname(),book.getPrice(),book.getAuthor(),book.getImage(),book.getCid(),book.getIsdel()};
		try {
			runner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 修改的图书
	 */
	public void updateBook(Book book) {
		QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		String sql = "update book set bname = ? , price = ? ,author = ? ,image = ? ,cid = ?, isdel = ? where bid = ?";
		Object [] params = {book.getBname(),book.getPrice(),book.getAuthor(),book.getImage(),book.getCid(),book.getIsdel(),book.getBid()};
		try {
			runner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除图书
	 * 修改图书的isdel值为1
	 */
	public void deleteByBid(String bid) {
		QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		String sql = "update book set isdel = 1 where bid = ?";
		try {
			runner.update(sql, bid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
