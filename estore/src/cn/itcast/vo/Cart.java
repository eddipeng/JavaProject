package cn.itcast.vo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.sun.java.swing.plaf.windows.WindowsTreeUI.CollapsedIcon;

/*
 * 购物车
 * 包含多个购物项和三个方法(把购物车项都添加购物车,移除某一个购物项,清空购物车)
 */
public class Cart {
	private Map<String, CartItem> map = new HashMap<String, CartItem>();
	private double total;
	
	/*
	 * 提供了一个方法
	 * cartItems现在是javabean的一个属性
	 * ${ cart.cartItems }--获取collection的集合 c:forEach
	 */
	public Collection<CartItem> getCartItems(){
		return map.values();
	}



	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	/*
	 * 购物项添加到购物车
	 * 把某一个购物项添加到map
	 * 	如果map中存在购物项,数量+,总计变
	 * 	如果map中不存在,把书添加到map
	 */
	public void addCart(CartItem item) {
		//判断map中是否包含当前购物项
		String bookId = item.getBook().getBid();
		if (map.containsKey(bookId)) {
			//如果包含,map已经存在购物项
			//计算数量
			CartItem existedItem = map.get(bookId);
			existedItem.setCount(existedItem.getCount()+item.getCount());
		}else {
			//说明map中没有购物项
			map.put(bookId, item);
		
		}
		//计算总计
		total += item.getSubtotal();
	}
	
	/*
	 * 移除某一个购物项
	 * 根据图书的id把购物项从map移除
	 * 总计=总计-购物项小计
	 */
	public void removeCartItem(String bookId) {
		 CartItem item = map.remove(bookId);
		 double subtotal = item.getSubtotal();
		 total-=subtotal;
		 
	}
	
	/*
	 * 清空购物车--购物项在map中存着,把map清空
	 */
	public void clearCart() {
		//清空map
		map.clear();
		//总计变0
		total = 0;
	}
}
