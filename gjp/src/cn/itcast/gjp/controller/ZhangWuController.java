package cn.itcast.gjp.controller;
/*
 *  控制器层
 *  接收视图层的数据,数据传递给service层
 */

import java.util.List;

import cn.itcast.gjp.domain.ZhangWu;
import cn.itcast.gjp.service.ZhangWuService;

public class ZhangWuController {
	private ZhangWuService service = new ZhangWuService();
	/*
	 * 定义方法,实现查询所有的账务数据
	 * 方法由view层调用,调用service层
	 */
	public List<ZhangWu> selectAll() {
		return service.selectAll();
	}
	/*
	 * 定义方法,实现条件查询账务
	 * 方法由视图层调用,传递两个日期
	 * 调用service层的方法,传递两个日期字符串
	 * 结果集返回给view
	 */
	public List<ZhangWu> select(String startDate,String endDate) {
		return service.select(startDate, endDate);
	}
	/*
	 * 实现添加功能
	 * 由view层调用,传递参数
	 * 传递的不是5个数据,而是1个zhangwu对象
	 * 方法调用service,传递zhangwu对象
	 */
	public  void addZhangWu(ZhangWu zw) {
		service.addZhangWu(zw);
	}
	/*
	 * 定义方法,实现编辑账务功能
	 * 由view层调用,传递参数,也是zhangwu对象
	 * 调用service层方法,传递账务对象
	 */
	public void edit(ZhangWu zw) {
		service.edit(zw);
	}
	public void deleteZhangWu(int zwid) {
		service.deleteZhangWu(zwid);
	}
		
}
