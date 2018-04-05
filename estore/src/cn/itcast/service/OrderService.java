package cn.itcast.service;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import cn.itcast.dao.OrderDao;
import cn.itcast.dao.OrderDaoImple;
import cn.itcast.utils.MyJdbcUtil;
import cn.itcast.vo.Order;
import cn.itcast.vo.OrderItem;

/**
 * 订单的业务类
 * @author Administrator
 *
 */
public class OrderService {

	/**
	 * 保存订单
	 * @param order
	 */
	public void save(Order order) {
		// 在订单中，包含订单的信息和包含一些订单项的信息。
		// 在保存数据的时候，先保存订单的信息	inert into orders values ();
		// 还需要保存订单项的信息				inert into orderitem values ();
		Connection conn = null;
		try {
			conn = MyJdbcUtil.getConnection();
			// 开启事物
			conn.setAutoCommit(false);
			// 添加的操作
			OrderDao dao = new OrderDaoImple();
			// 保存订单
			dao.saveOrder(conn,order);
			
			// 循环遍历，获取到多个订单项
			for (OrderItem orderItem : order.getOrderItems()) {
				// 保存订单项
				dao.saveOrderItem(conn,orderItem);
			}
			
			// 提交事物
			DbUtils.commitAndCloseQuietly(conn);
			
		} catch (Exception e) {
			DbUtils.rollbackAndCloseQuietly(conn);
			// 遇到问题了 回滚事物
			e.printStackTrace();
		}
	}

	/**
	 * 通过用户的id查询所有的订单
	 * @param uid
	 * @return
	 */
	public List<Order> findByUid(String uid) {
		OrderDao dao = new OrderDaoImple();
		return dao.findByUid(uid);
	}

	public Order findByOid(String oid) {
		OrderDao dao = new OrderDaoImple();
		return dao.findByOid(oid);
	}

	/**
	 * 修改订单
	 * @param order
	 */
	public void updateOrder(Order order) {
		OrderDao dao = new OrderDaoImple();
		dao.updateOrder(order);
	}

	/**
	 * 查询所有的订单
	 * @return
	 */
	public List<Order> findAll() {
		OrderDao dao = new OrderDaoImple();
		return dao.findAll();
	}

	/**
	 * 通过订单的状态查询所有的订单
	 * @param state
	 * @return
	 */
	public List<Order> findByState(int state) {
		OrderDao dao = new OrderDaoImple();
		return dao.findByState(state);
	}

	/**
	 * 根据订单的编号来修改订单的状态
	 * @param oid
	 * @param state
	 */
	public void updateByState(String oid, int state) {
		OrderDao dao = new OrderDaoImple();
		dao.updateByState(oid, state);
	}

}









