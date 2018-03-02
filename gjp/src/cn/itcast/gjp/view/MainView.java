package cn.itcast.gjp.view;
import java.util.List;
import java.util.Scanner;

/*
 * ��ͼ��,�û������Ͳ����Ľ���
 * ���ݴ���Controller��ʵ��
 */
import cn.itcast.gjp.controller.ZhangWuController;
import cn.itcast.gjp.domain.ZhangWu;

public class MainView {
	private ZhangWuController controller = new ZhangWuController();
	/*
	 * ʵ�ֽ���Ч��
	 * �����û�����
	 * �������ݵ��ò�ͬ�ķ���
	 */
	public void run() {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("==========�ܼ��ż���==========");
			System.out.println("1.�������2.�༭����3.ɾ������4.��ѯ����5.�˳�ϵͳ");
			System.out.println("������Ҫ�����Ĺ������[1-5]:");
			//�����û�ѡ��
			int choose = sc.nextInt();
			//��ѡ��Ĳ˵������ж�
			switch (choose) {
			case 1:
				//�������,�����������ķ���
				addZhangWu();
				break;
			case 2:
				edit();
				//�༭����,���÷���
				break;
			case 3:
				deleteZhangWu();
				//ɾ������,���÷���
				break;
			case 4:
				//��ѯ����,���÷���
				selectZhangWu();
				break;
			case 5:
//				�˳�
				System.exit(0);
				break;
			default:
				System.out.println("�������");
			}
		}
	}
	/*
	 * �����ѯ����,��ʾ��ѯ��ʽ
	 * 1.ȫ����ѯ
	 * 2.������ѯ
	 */
	public void selectZhangWu() {
		System.out.println("1.��ѯ����   2.������ѯ");
		Scanner sc = new Scanner(System.in);
		int selectchoose = sc.nextInt();
		//�ж�
		switch (selectchoose) {
		case 1:
			//���ò�ѯ���еķ���
			selectAll();
			break;
		case 2:
			//���ò�ѯ�����ķ���
			select();
			break;
		default:
			System.out.println("�������");
		}
	}
	/*
	 * ���巽��ʵ��������ѯ
	 * �ṩ�û�����������,��ʼ���ںͽ�������
	 * ��2�����ڴ��ݵ�controller��
	 * ����controller��ķ���,�����������ڲ���
	 *��ȡ��controller��ѯ�Ľ����,��ӡ����
	 * 
	 */
	private void select() {
		System.out.println("ѡ��������ѯ,�����ʽXXXX-XX-XX");
		Scanner sc = new Scanner(System.in);
		System.out.println("�����뿪ʼ����:");
		String startDate = sc.nextLine();
		System.out.println("�������������:");
		String endDate = sc.nextLine();
		//����controller��ķ���,��������,��ȡ��ѯ�����
		List<ZhangWu> list = controller.select(startDate, endDate);
		if(list.size() != 0)
		{	
		//�����ͷ
		System.out.println("ID\t\t���\t\t\t�˻�\t\t���\t\tʱ��\t\t˵��");
		for (ZhangWu zw  : list) {
			System.out.println(zw.getZwid()+"\t\t"+zw.getFlname()+"\t"+zw.getZhanghu()+
					"\t\t"+zw.getMoney()+"\t\t"+zw.getCreatetime()+"\t\t"+zw.getDescription());
		}
			}
		else {
			System.out.println("û�в�ѯ������");
		}
	}
	/*
	 * ���巽��ʵ��ȫ����ѯ
	 */
	private void selectAll() {
		//���ÿ��Ʋ�ķ���,��ѯ����
		List<ZhangWu> list = controller.selectAll();
		//�����ͷ
		System.out.println("ID\t\t���\t\t\t�˻�\t\t���\t\tʱ��\t\t˵��");
		for (ZhangWu zw  : list) {
			System.out.println(zw.getZwid()+"\t\t"+zw.getFlname()+"\t"+zw.getZhanghu()+
					"\t\t"+zw.getMoney()+"\t\t"+zw.getCreatetime()+"\t\t"+zw.getDescription());
		}
	}
	
	/*
	 * �������zhangwu����
	 * ʵ��˼��
	 * 	ʵ�ּ�������,5������,����Controller�㷽��
	 */
	private void addZhangWu() {
		System.out.println("���������,������������:");
		Scanner sc = new Scanner(System.in);
		System.out.println("�������������:");
		String flname = sc.next();
		System.out.println("��������:");
		double money = sc.nextDouble();
		System.out.println("�������˻�:");
		String zhanghu = sc.next();
		System.out.println("����������:XXXX-XX-XX");
		String createtime = sc.next();
		System.out.println("����������:");
		String description = sc.next();
		//�����յ�������,����controller��ķ���,���ݲ���
		//id�������д,����¼�����ݿ�
		ZhangWu zw = new ZhangWu(0,flname,money,zhanghu,createtime,description);
		controller.addZhangWu(zw);
		System.out.println("��ϲ!��ӳɹ�!");
	}

	/*
	 * ���巽��,ʵ�ֶ�����ı༭����
	 * ʵ��˼��:
	 * 		�����û�������
	 * 		���ݵ���Ϣ,��װ��zhangwu����
	 * 		���ÿ��Ʋ�ķ���,����zhangwu����,ʵ�ֱ༭
	 */
	private void edit() {
		//���ò�ѯ�����������ݵĹ���,��ʾ����
		//������������,����ѡ��һ��,�����޸�
		selectAll();
		System.out.println("ѡ����Ǳ༭����,����������:");
		Scanner sc =new Scanner(System.in);
		System.out.print("����ID:");
		int zwid = sc.nextInt();
		System.out.println("�������������:");
		String flname = sc.next();
		System.out.println("��������:");
		double money = sc.nextDouble();
		System.out.println("�������˻�:");
		String zhanghu = sc.next();
		System.out.println("����������:XXXX-XX-XX");
		String createtime = sc.next();
		System.out.println("����������:");
		String description = sc.next();
		//��������������,��װ��zhangwu������
		//�û������ID�����װ������
		ZhangWu zw = new ZhangWu(zwid, flname, money, zhanghu, createtime, description);
		controller.edit(zw);
		System.out.println("����༭�ɹ�");
	}
	
	/*
	 * ���巽��,ʵ�������ɾ������
	 * ʵ��˼��:�����û�������,����һ����������
	 * 	���ÿ��Ʋ㷽��,����һ������
	 */
	private void deleteZhangWu() {
		selectAll();
		System.out.println("��ѡ�����ɾ������,������ID:");
		int zwid = new Scanner(System.in).nextInt();
		controller.deleteZhangWu(zwid);
		System.out.println("ɾ������ɹ�");
	}
}
