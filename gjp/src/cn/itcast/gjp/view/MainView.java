package cn.itcast.gjp.view;
import java.util.List;
import java.util.Scanner;

/*
 * 视图层,用户看到和操作的界面
 * 数据传给Controller类实现
 */
import cn.itcast.gjp.controller.ZhangWuController;
import cn.itcast.gjp.domain.ZhangWu;

public class MainView {
	private ZhangWuController controller = new ZhangWuController();
	/*
	 * 实现界面效果
	 * 接收用户输入
	 * 根据数据调用不同的方法
	 */
	public void run() {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("==========管家婆记账==========");
			System.out.println("1.添加账务　2.编辑账务　3.删除账务　4.查询账务　5.退出系统");
			System.out.println("请输入要操作的功能序号[1-5]:");
			//接收用户选择
			int choose = sc.nextInt();
			//对选择的菜单进行判断
			switch (choose) {
			case 1:
				//添加账务,调用添加账务的方法
				addZhangWu();
				break;
			case 2:
				edit();
				//编辑账务,调用方法
				break;
			case 3:
				deleteZhangWu();
				//删除账务,调用方法
				break;
			case 4:
				//查询账务,调用方法
				selectZhangWu();
				break;
			case 5:
//				退出
				System.exit(0);
				break;
			default:
				System.out.println("输入错误");
			}
		}
	}
	/*
	 * 定义查询方法,显示查询方式
	 * 1.全部查询
	 * 2.条件查询
	 */
	public void selectZhangWu() {
		System.out.println("1.查询所有   2.条件查询");
		Scanner sc = new Scanner(System.in);
		int selectchoose = sc.nextInt();
		//判断
		switch (selectchoose) {
		case 1:
			//调用查询所有的方法
			selectAll();
			break;
		case 2:
			//调用查询条件的方法
			select();
			break;
		default:
			System.out.println("输入错误");
		}
	}
	/*
	 * 定义方法实现条件查询
	 * 提供用户的输入日期,开始日期和结束日期
	 * 将2个日期传递到controller层
	 * 调用controller层的方法,传递两个日期参数
	 *获取到controller查询的结果集,打印出来
	 * 
	 */
	private void select() {
		System.out.println("选择条件查询,输入格式XXXX-XX-XX");
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入开始日期:");
		String startDate = sc.nextLine();
		System.out.println("请输入结束日期:");
		String endDate = sc.nextLine();
		//调用controller层的方法,传递日期,获取查询结果集
		List<ZhangWu> list = controller.select(startDate, endDate);
		if(list.size() != 0)
		{	
		//输出表头
		System.out.println("ID\t\t类别\t\t\t账户\t\t金额\t\t时间\t\t说明");
		for (ZhangWu zw  : list) {
			System.out.println(zw.getZwid()+"\t\t"+zw.getFlname()+"\t"+zw.getZhanghu()+
					"\t\t"+zw.getMoney()+"\t\t"+zw.getCreatetime()+"\t\t"+zw.getDescription());
		}
			}
		else {
			System.out.println("没有查询到数据");
		}
	}
	/*
	 * 定义方法实现全部查询
	 */
	private void selectAll() {
		//调用控制层的方法,查询所有
		List<ZhangWu> list = controller.selectAll();
		//输出表头
		System.out.println("ID\t\t类别\t\t\t账户\t\t金额\t\t时间\t\t说明");
		for (ZhangWu zw  : list) {
			System.out.println(zw.getZwid()+"\t\t"+zw.getFlname()+"\t"+zw.getZhanghu()+
					"\t\t"+zw.getMoney()+"\t\t"+zw.getCreatetime()+"\t\t"+zw.getDescription());
		}
	}
	
	/*
	 * 定义添加zhangwu方法
	 * 实现思想
	 * 	实现键盘输入,5项输入,调用Controller层方法
	 */
	private void addZhangWu() {
		System.out.println("添加账务功能,输入以下内容:");
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入分类名称:");
		String flname = sc.next();
		System.out.println("请输入金额:");
		double money = sc.nextDouble();
		System.out.println("请输入账户:");
		String zhanghu = sc.next();
		System.out.println("请输入日期:XXXX-XX-XX");
		String createtime = sc.next();
		System.out.println("请输入描述:");
		String description = sc.next();
		//将接收到的数据,调用controller层的方法,传递参数
		//id参数随便写,不会录进数据库
		ZhangWu zw = new ZhangWu(0,flname,money,zhanghu,createtime,description);
		controller.addZhangWu(zw);
		System.out.println("恭喜!添加成功!");
	}

	/*
	 * 定义方法,实现对账务的编辑功能
	 * 实现思想:
	 * 		接收用户的输入
	 * 		数据的信息,封装成zhangwu对象
	 * 		调用控制层的方法,传递zhangwu对象,实现编辑
	 */
	private void edit() {
		//调用查询所有账务数据的功能,显示出来
		//看到所有数据,从中选择一项,进行修改
		selectAll();
		System.out.println("选择的是编辑功能,请输入数据:");
		Scanner sc =new Scanner(System.in);
		System.out.print("输入ID:");
		int zwid = sc.nextInt();
		System.out.println("请输入分类名称:");
		String flname = sc.next();
		System.out.println("请输入金额:");
		double money = sc.nextDouble();
		System.out.println("请输入账户:");
		String zhanghu = sc.next();
		System.out.println("请输入日期:XXXX-XX-XX");
		String createtime = sc.next();
		System.out.println("请输入描述:");
		String description = sc.next();
		//将所有输入数据,封装到zhangwu对象中
		//用户输入的ID必须封装到对象
		ZhangWu zw = new ZhangWu(zwid, flname, money, zhanghu, createtime, description);
		controller.edit(zw);
		System.out.println("账务编辑成功");
	}
	
	/*
	 * 定义方法,实现账务的删除功能
	 * 实现思想:接受用户的输入,输入一个主键数据
	 * 	调用控制层方法,传递一个主键
	 */
	private void deleteZhangWu() {
		selectAll();
		System.out.println("你选择的是删除功能,请输入ID:");
		int zwid = new Scanner(System.in).nextInt();
		controller.deleteZhangWu(zwid);
		System.out.println("删除账务成功");
	}
}
