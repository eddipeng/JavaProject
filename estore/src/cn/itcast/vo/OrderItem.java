package cn.itcast.vo;

/**
 * 订单项
 * @author Administrator
 *
 */
public class OrderItem {
	
	// 订单项的主键
	private String itemid;
	// 订单项中的数量
	private int count;
	// 订单项的小计
	private double subtotal;
	
	private Book book;		// bid
	private Order order;	// oid
	
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}

}
