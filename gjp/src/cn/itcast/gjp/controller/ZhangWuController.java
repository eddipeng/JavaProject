package cn.itcast.gjp.controller;
/*
 *  ��������
 *  ������ͼ�������,���ݴ��ݸ�service��
 */

import java.util.List;

import cn.itcast.gjp.domain.ZhangWu;
import cn.itcast.gjp.service.ZhangWuService;

public class ZhangWuController {
	private ZhangWuService service = new ZhangWuService();
	/*
	 * ���巽��,ʵ�ֲ�ѯ���е���������
	 * ������view�����,����service��
	 */
	public List<ZhangWu> selectAll() {
		return service.selectAll();
	}
	/*
	 * ���巽��,ʵ��������ѯ����
	 * ��������ͼ�����,������������
	 * ����service��ķ���,�������������ַ���
	 * ��������ظ�view
	 */
	public List<ZhangWu> select(String startDate,String endDate) {
		return service.select(startDate, endDate);
	}
	/*
	 * ʵ����ӹ���
	 * ��view�����,���ݲ���
	 * ���ݵĲ���5������,����1��zhangwu����
	 * ��������service,����zhangwu����
	 */
	public  void addZhangWu(ZhangWu zw) {
		service.addZhangWu(zw);
	}
	/*
	 * ���巽��,ʵ�ֱ༭������
	 * ��view�����,���ݲ���,Ҳ��zhangwu����
	 * ����service�㷽��,�����������
	 */
	public void edit(ZhangWu zw) {
		service.edit(zw);
	}
	public void deleteZhangWu(int zwid) {
		service.deleteZhangWu(zwid);
	}
		
}
