package cn.itcast.gjp.service;
/*
 * ҵ���
 * ������һ��,���Ʋ�controller������
 * ����һϵ�м���,���ݸ�dao��,�������ݿ�
 * ����dao���е���,���Աλ��,����Dao��Ķ���
 */

import java.util.List;

import cn.itcast.gjp.dao.ZhangWuDao;
import cn.itcast.gjp.domain.ZhangWu;

public class ZhangWuService {
	private ZhangWuDao dao = new ZhangWuDao();
	 /*
	  * ���巽��,ʵ�ֲ�ѯ���е���������
	  * �˷�����controller�����,����dao��
	  * ���ش洢zhangwu�����list����
	  */
	public List<ZhangWu> selectAll() {
		return dao.selectAll();
	}
	/*
	 *	���巽��,ʵ�ֲ�ѯ��������������
	  * �˷�����controller�����,����dao��
	  * ���ش洢zhangwu�����list����
	 */
	public List<ZhangWu> select(String startDate,String endDate) {
		return dao.select(startDate, endDate);
	}
	/*
	 * �������������
	 * ��controller�����,����zhangwu���Ͷ���
	 */
	public void addZhangWu(ZhangWu zw) {
		dao.addZhangWu(zw);
	}
	 
	public void edit(ZhangWu zw) {
		dao.edit(zw);
	}
	public void deleteZhangWu(int zwid) {
		dao.deleteZhangWu(zwid);
	}
}
