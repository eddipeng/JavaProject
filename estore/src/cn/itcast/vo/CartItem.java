package cn.itcast.vo;
/*
 * 购物项
 */
public class CartItem {
	//包含图书信息
	private Book book;
	private int count;
//	private double subtotal;
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	//获取小计
	public double getSubtotal() {
		//小计=数量*书的单价
		return count*book.getPrice();
	}

}
