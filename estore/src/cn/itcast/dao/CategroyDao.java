package cn.itcast.dao;

import java.util.List;

import cn.itcast.vo.Category;

public interface CategroyDao {

	List<Category> findAllCate();
	void save(Category c);

	Category findByCid(String cid);

	void update(Category c);

	void delete(String cid);

}
