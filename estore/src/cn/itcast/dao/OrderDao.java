package cn.itcast.dao;

import java.sql.Connection;
import java.util.List;

import cn.itcast.vo.Order;
import cn.itcast.vo.OrderItem;

public interface OrderDao {

	void saveOrder(Connection conn, Order order);
	void saveOrderItem(Connection conn, OrderItem orderItem);
	List<Order> findByUid(String uid);
	Order findByOid(String oid);

	void updateOrder(Order order);
	void updateByState(String oid, int state);
	List<Order> findByState(int state);
	List<Order> findAll();
}
