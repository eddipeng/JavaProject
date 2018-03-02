package cn.itcast.gjp.service;
/*
 * 业务层
 * 接受上一层,控制层controller的数据
 * 经过一系列计算,传递给dao层,操作数据库
 * 调用dao层中的类,类成员位置,创建Dao类的对象
 */

import java.util.List;

import cn.itcast.gjp.dao.ZhangWuDao;
import cn.itcast.gjp.domain.ZhangWu;

public class ZhangWuService {
	private ZhangWuDao dao = new ZhangWuDao();
	 /*
	  * 定义方法,实现查询所有的账务数据
	  * 此方法由controller层调用,调用dao层
	  * 返回存储zhangwu对象的list集合
	  */
	public List<ZhangWu> selectAll() {
		return dao.selectAll();
	}
	/*
	 *	定义方法,实现查询条件的账务数据
	  * 此方法由controller层调用,调用dao层
	  * 返回存储zhangwu对象的list集合
	 */
	public List<ZhangWu> select(String startDate,String endDate) {
		return dao.select(startDate, endDate);
	}
	/*
	 * 定义添加账务功能
	 * 由controller层调用,传递zhangwu类型对象
	 */
	public void addZhangWu(ZhangWu zw) {
		dao.addZhangWu(zw);
	}
	 
	public void edit(ZhangWu zw) {
		dao.edit(zw);
	}
	public void deleteZhangWu(int zwid) {
		dao.deleteZhangWu(zwid);
	}
}
