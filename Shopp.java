import java.util.ArrayList;
import java.util.Scanner;

/*
 * 超市管理系统主类
 * 实现:
 * 		1.商品数据的初始化
 * 		2.用户的菜单选择
 * 		3.根据选择执行不同的功能
 * 			3.1 Creat  添加商品
 			3.2 Update 修改商品
 			3.3 Read   查看商品
 			3.4 Delete 删除商品
 			
	所有功能必须定义方法实现
	主方法main只起调用作用
 */
public class Shopp {

	public static void main(String[] args) {
		//创建Arrarlist集合,存FruitItem类型
		ArrayList<FruitItem> array = new ArrayList<FruitItem>();
		//调用商品初始化方法
		init(array);
		while (true) {
			//调用菜单方法
			mainMenu();
			//调用用户选择序号
			int choose = chooseFunction();
			switch (choose) {
			case 1:
				//调用货物清单
				showFruitList(array);
				break;
			case 2:
				//调用添加货物
				addFruit(array);
				break;
			case 3:
				//调用删除货物
				deleteFruit(array);
				break;
			case 4:
				//调用修改货物
				updateFruit(array);
				break;
			case 5:
				//结束调用
				return;

			default:
				System.out.println("输入序号没有");
				break;
			}
		}
	}

	/*
	 * 定义一个功能,实现商品数据初始化
	 * 返回值:void
	 * 变量:array
	 * 方法名:init
	 */
	public static void init(ArrayList<FruitItem> array){
		//创建出多个FruitItem类型,并且赋值
		FruitItem f1 = new FruitItem();
		f1.ID = 9527;
		f1.name = "少林寺酥饼核桃";
		f1.price = 12.7;
		
		FruitItem f2 = new FruitItem();
		f2.ID = 9008;
		f2.name = "尚康杂粮牡丹饼";
		f2.price = 6.5;
		
		FruitItem f3 = new FruitItem();
		f3.ID = 6328;
		f3.name = "Big Roll";
		f3.price = 11.8;
		
		//创建的3个FruitItem存到array集合中
		array.add(f1);
		array.add(f2);
		array.add(f3);
	}
	
	/*
	 * 定义方法,实现主菜单
	 * 提示用户有哪些选择,让选择序号
	 * 返回值:void  之前有,现在剥离此功能
	 * 变量:无
	 */
	public static void mainMenu() {
		System.out.println();
		System.out.println("==============欢迎光临大脚超市==============");
		System.out.println("1: 货物 清单 2: 添加货物 3: 删除货物 4: 修改货物 5: 退出");
		System.out.println("请您输入要操作的功能序号:");
	}
	
	/*
	 * 定义方法,实现用户键盘输入
	 * 返回值: 编号
	 * 变量:void
	 */
	public static int chooseFunction() {
		Scanner sc = new Scanner(System.in);
		return sc.nextInt();
	}
	/*
	 * 定义方法,实现显示货物清单的功能
	 * 返回值:无
	 * 变量:array
	 */
	public static void showFruitList(ArrayList<FruitItem> array) {
		System.out.println();
		System.out.println("==============商品库存清单==============");
		System.out.println("商品编号     商品名称        商品单价");
		//遍历集合
		for (int i = 0; i < array.size(); i++) {
			//集合get方法,获取出每个FruitItem变量
			FruitItem item = array.get(i);
			//变量item调用类中属性
			System.out.println(item.ID+"         "+item.name+"    "+item.price);
		}
	}
	/*
	 * 定义方法:实现商品添加功能
	 * 返回值:void
	 * 变量:array
	 * 提示用户选择的是添加商品的功能
	 * 提示用户输入的是什么
	 * 创建FruitItem变量,调用变量的属性
	 * 将输入的每个商品属性进行赋值
	 */
	public static void addFruit(ArrayList<FruitItem> array) {
		System.out.println("您选择的是添加商品功能!");
		//创建Scanner变量,sc作为变量接收用户输入的信息
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入商品的编号:");
		//输入的商品的编号
		int ID = sc.nextInt();
		//输入商品的名字
		System.out.println("输入商品的名字");
		String name = sc.next();
		//输入商品的单价
		System.out.println("输入商品的单价");
		double price = sc.nextDouble();
		//创建FruitItem类型的变量
		FruitItem item = new FruitItem();
		//item.属性赋值
		item.ID = ID;
		item.name = name;
		item.price = price;
		array.add(item);
		
	}
	
/*
 * 定义方法:实现删除功能
 * 返回值:void
 * 变量:集合
 * 删除依靠的是商品的编号
 * 提示用户选择的是删除功能
 * 键盘输入商品的编号
 * 遍历集合,获取集合Fruitem变量,调用ID,和用户输入的ID对比,相同就删除
 */
	public static void deleteFruit(ArrayList<FruitItem> array) {
		System.out.println("您选择的是删除功能");
		System.out.println("请输入商品的编号");
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		for (int i = 0; i < array.size(); i++) {
			if(num == array.get(i).ID){
				array.remove(i);
				System.out.println("删除成功");
				//写break的意思是遍历到删除完了就不用遍历了
				return;
			}

		}
		System.out.println("没有找到该商品");

	}

/*
 * 定义方法:实现商品修改功能
 * 返回值:void
 * 参数:array
 * 
 * 提示用户选择的是修改功能
 * 提示用户输入需要修改的商品编号
 * 遍历集合,调用ID属性,与用户输入比较
 * 如果相同,修改属性值,需改需要键盘输入
 * 
 * 	
 */
	public static void updateFruit(ArrayList<FruitItem> array) {
		System.out.println("您选择的是商品修改功能");
		System.out.println("请输入商品修改编号");
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		for (int i = 0; i < array.size(); i++) {
			if(num == array.get(i).ID){
				System.out.println("请输入新的商品编号");
				array.get(i).ID = sc.nextInt();
				
				System.out.println("请输入新的商品名称");
				array.get(i).name = sc.next();
				
				System.out.println("请输入新的商品单价");
				array.get(i).price = sc.nextDouble();
				
				System.out.println("商品修改成功!");
				return;
			}
			
		}
		System.out.println("您想要修改的商品不存在!");
	}
}

