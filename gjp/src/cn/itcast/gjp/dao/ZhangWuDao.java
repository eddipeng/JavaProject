package cn.itcast.gjp.dao;
/*
 * 实现对数据表gjp_zhangwu 数据增删改查操作
 * 用dbutiles工具类完成,类成员QueryRunner对象
 * 指定数据源
 */

import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.gjp.domain.ZhangWu;
import cn.itcast.gjp.tools.JDBCUtils;

public class ZhangWuDao {
	private QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
	/*
	 * 查询数据库,获取所有的账务信息
	 * 由service层调用
	 * 
	 */
	public List<ZhangWu> selectAll() {
		//查询账务数据的sql
		String sql = "SELECT * FROM gjp_zhangwu";
//		调用qr对象的方法,结果集是BeanListHandler
		List<ZhangWu> list;
		try {
			list = qr.query(sql,new BeanListHandler<>(ZhangWu.class) );
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询所有账务失败");
		}
	}
	/*
	 * 定义方法,查询数据库,带有条件去查询账务表
	 	由业务层调用
	 	2个日期字符串
	 */
	public List<ZhangWu> select(String startDate,String endDate) {
		String sql = "SELECT * FROM gjp_zhangwu WHERE createtime BETWEEN ? AND ?";
		Object[] params = {startDate,endDate};
		try {
			return qr.query(sql, params,new BeanListHandler<>(ZhangWu.class));
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new RuntimeException("条件查询失败");
		}
	}
	/*
	 * 定义添加账务方法
	 * 由service层调用,将zhangwu对象数据添加到数据表
	 */
	public void addZhangWu(ZhangWu zw) {
		//拼写添加数据的sql
		String sql = "INSERT INTO gjp_zhangwu (flname,money,zhanghu,createtime,description) VALUES (?,?,?,?,?)";
		Object[] params = {zw.getFlname(),zw.getMoney(),zw.getZhanghu(),zw.getCreatetime(),zw.getDescription()};
		//调用qr方法update执行添加
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public void edit(ZhangWu zw) {
		//更新sql
		String sql = "UPDATE gjp_zhangwu SET flname=?,money=?,zhanghu=?,createtime=?,description=? WHERE zwid=?";
		Object[] params = {zw.getFlname(),zw.getMoney(),zw.getZhanghu(),zw.getCreatetime(),zw.getDescription(),zw.getZwid()};
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	public void deleteZhangWu(int zwid) {
		String sql = "DELETE FROM gjp_zhangwu WHERE ZWID=?";
		try {
			qr.update(sql, zwid);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	
}
