import java.util.ArrayList;
import java.util.Scanner;

/*
 * ���й���ϵͳ����
 * ʵ��:
 * 		1.��Ʒ���ݵĳ�ʼ��
 * 		2.�û��Ĳ˵�ѡ��
 * 		3.����ѡ��ִ�в�ͬ�Ĺ���
 * 			3.1 Creat  �����Ʒ
 			3.2 Update �޸���Ʒ
 			3.3 Read   �鿴��Ʒ
 			3.4 Delete ɾ����Ʒ
 			
	���й��ܱ��붨�巽��ʵ��
	������mainֻ���������
 */
public class Shopp {

	public static void main(String[] args) {
		//����Arrarlist����,��FruitItem����
		ArrayList<FruitItem> array = new ArrayList<FruitItem>();
		//������Ʒ��ʼ������
		init(array);
		while (true) {
			//���ò˵�����
			mainMenu();
			//�����û�ѡ�����
			int choose = chooseFunction();
			switch (choose) {
			case 1:
				//���û����嵥
				showFruitList(array);
				break;
			case 2:
				//������ӻ���
				addFruit(array);
				break;
			case 3:
				//����ɾ������
				deleteFruit(array);
				break;
			case 4:
				//�����޸Ļ���
				updateFruit(array);
				break;
			case 5:
				//��������
				return;

			default:
				System.out.println("�������û��");
				break;
			}
		}
	}

	/*
	 * ����һ������,ʵ����Ʒ���ݳ�ʼ��
	 * ����ֵ:void
	 * ����:array
	 * ������:init
	 */
	public static void init(ArrayList<FruitItem> array){
		//���������FruitItem����,���Ҹ�ֵ
		FruitItem f1 = new FruitItem();
		f1.ID = 9527;
		f1.name = "�������ֱ�����";
		f1.price = 12.7;
		
		FruitItem f2 = new FruitItem();
		f2.ID = 9008;
		f2.name = "�п�����ĵ����";
		f2.price = 6.5;
		
		FruitItem f3 = new FruitItem();
		f3.ID = 6328;
		f3.name = "Big Roll";
		f3.price = 11.8;
		
		//������3��FruitItem�浽array������
		array.add(f1);
		array.add(f2);
		array.add(f3);
	}
	
	/*
	 * ���巽��,ʵ�����˵�
	 * ��ʾ�û�����Щѡ��,��ѡ�����
	 * ����ֵ:void  ֮ǰ��,���ڰ���˹���
	 * ����:��
	 */
	public static void mainMenu() {
		System.out.println();
		System.out.println("==============��ӭ���ٴ�ų���==============");
		System.out.println("1: ���� �嵥 2: ��ӻ��� 3: ɾ������ 4: �޸Ļ��� 5: �˳�");
		System.out.println("��������Ҫ�����Ĺ������:");
	}
	
	/*
	 * ���巽��,ʵ���û���������
	 * ����ֵ: ���
	 * ����:void
	 */
	public static int chooseFunction() {
		Scanner sc = new Scanner(System.in);
		return sc.nextInt();
	}
	/*
	 * ���巽��,ʵ����ʾ�����嵥�Ĺ���
	 * ����ֵ:��
	 * ����:array
	 */
	public static void showFruitList(ArrayList<FruitItem> array) {
		System.out.println();
		System.out.println("==============��Ʒ����嵥==============");
		System.out.println("��Ʒ���     ��Ʒ����        ��Ʒ����");
		//��������
		for (int i = 0; i < array.size(); i++) {
			//����get����,��ȡ��ÿ��FruitItem����
			FruitItem item = array.get(i);
			//����item������������
			System.out.println(item.ID+"         "+item.name+"    "+item.price);
		}
	}
	/*
	 * ���巽��:ʵ����Ʒ��ӹ���
	 * ����ֵ:void
	 * ����:array
	 * ��ʾ�û�ѡ����������Ʒ�Ĺ���
	 * ��ʾ�û��������ʲô
	 * ����FruitItem����,���ñ���������
	 * �������ÿ����Ʒ���Խ��и�ֵ
	 */
	public static void addFruit(ArrayList<FruitItem> array) {
		System.out.println("��ѡ����������Ʒ����!");
		//����Scanner����,sc��Ϊ���������û��������Ϣ
		Scanner sc = new Scanner(System.in);
		System.out.println("��������Ʒ�ı��:");
		//�������Ʒ�ı��
		int ID = sc.nextInt();
		//������Ʒ������
		System.out.println("������Ʒ������");
		String name = sc.next();
		//������Ʒ�ĵ���
		System.out.println("������Ʒ�ĵ���");
		double price = sc.nextDouble();
		//����FruitItem���͵ı���
		FruitItem item = new FruitItem();
		//item.���Ը�ֵ
		item.ID = ID;
		item.name = name;
		item.price = price;
		array.add(item);
		
	}
	
/*
 * ���巽��:ʵ��ɾ������
 * ����ֵ:void
 * ����:����
 * ɾ������������Ʒ�ı��
 * ��ʾ�û�ѡ�����ɾ������
 * ����������Ʒ�ı��
 * ��������,��ȡ����Fruitem����,����ID,���û������ID�Ա�,��ͬ��ɾ��
 */
	public static void deleteFruit(ArrayList<FruitItem> array) {
		System.out.println("��ѡ�����ɾ������");
		System.out.println("��������Ʒ�ı��");
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		for (int i = 0; i < array.size(); i++) {
			if(num == array.get(i).ID){
				array.remove(i);
				System.out.println("ɾ���ɹ�");
				//дbreak����˼�Ǳ�����ɾ�����˾Ͳ��ñ�����
				return;
			}

		}
		System.out.println("û���ҵ�����Ʒ");

	}

/*
 * ���巽��:ʵ����Ʒ�޸Ĺ���
 * ����ֵ:void
 * ����:array
 * 
 * ��ʾ�û�ѡ������޸Ĺ���
 * ��ʾ�û�������Ҫ�޸ĵ���Ʒ���
 * ��������,����ID����,���û�����Ƚ�
 * �����ͬ,�޸�����ֵ,�����Ҫ��������
 * 
 * 	
 */
	public static void updateFruit(ArrayList<FruitItem> array) {
		System.out.println("��ѡ�������Ʒ�޸Ĺ���");
		System.out.println("��������Ʒ�޸ı��");
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		for (int i = 0; i < array.size(); i++) {
			if(num == array.get(i).ID){
				System.out.println("�������µ���Ʒ���");
				array.get(i).ID = sc.nextInt();
				
				System.out.println("�������µ���Ʒ����");
				array.get(i).name = sc.next();
				
				System.out.println("�������µ���Ʒ����");
				array.get(i).price = sc.nextDouble();
				
				System.out.println("��Ʒ�޸ĳɹ�!");
				return;
			}
			
		}
		System.out.println("����Ҫ�޸ĵ���Ʒ������!");
	}
}

