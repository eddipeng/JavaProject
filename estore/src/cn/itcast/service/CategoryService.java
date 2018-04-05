package cn.itcast.service;

import java.util.List;

import cn.itcast.dao.CategoryDaoImple;
import cn.itcast.dao.CategroyDao;
import cn.itcast.vo.Category;

public class CategoryService {

	public List<Category> findAllCate() {
		CategroyDao dao = new CategoryDaoImple();
		return dao.findAllCate();
	}
	/**
	 * 添加分类
	 * @param c
	 */
	public void save(Category c) {
		CategroyDao dao = new CategoryDaoImple();
		dao.save(c);
	}

	public Category findByCid(String cid) {
		CategroyDao dao = new CategoryDaoImple();
		return dao.findByCid(cid);
	}

	public void update(Category c) {
		CategroyDao dao = new CategoryDaoImple();
		dao.update(c);
	}

	public void delete(String cid) {
		CategroyDao dao = new CategoryDaoImple();
		dao.delete(cid);
	}
}
