package cn.itcast.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.utils.MyJdbcUtil;
import cn.itcast.vo.Category;

public class CategoryDaoImple implements CategroyDao {
/*
 * 查询所有分类 
 */
	public List<Category> findAllCate() {
		QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		String sql = "SELECT * FROM category";
		try {
			return runner.query(sql, new BeanListHandler<Category>(Category.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	/**
	 * 添加分类
	 */
	public void save(Category c) {
		QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		String sql = "insert into category values (?,?)";
		try {
			runner.update(sql, c.getCid(),c.getCname());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 通过分类的主键查询分类
	 */
	public Category findByCid(String cid) {
		QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		String sql = "select * from category where cid = ?";
		try {
			return runner.query(sql, new BeanHandler<Category>(Category.class), cid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	
	/**
	 * 修改分类的实现
	 */
	public void update(Category c) {
		QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		String sql = "update category set cname = ? where cid = ?";
		try {
			runner.update(sql, c.getCname(),c.getCid());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 删除分类
	 */
	public void delete(String cid) {
		QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		String sql = "delete from category where cid = ?";
		try {
			runner.update(sql, cid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
