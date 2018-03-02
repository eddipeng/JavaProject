package cn.itcast.gjp.dao;
/*
 * ʵ�ֶ����ݱ�gjp_zhangwu ������ɾ�Ĳ����
 * ��dbutiles���������,���ԱQueryRunner����
 * ָ������Դ
 */

import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.gjp.domain.ZhangWu;
import cn.itcast.gjp.tools.JDBCUtils;

public class ZhangWuDao {
	private QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
	/*
	 * ��ѯ���ݿ�,��ȡ���е�������Ϣ
	 * ��service�����
	 * 
	 */
	public List<ZhangWu> selectAll() {
		//��ѯ�������ݵ�sql
		String sql = "SELECT * FROM gjp_zhangwu";
//		����qr����ķ���,�������BeanListHandler
		List<ZhangWu> list;
		try {
			list = qr.query(sql,new BeanListHandler<>(ZhangWu.class) );
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("��ѯ��������ʧ��");
		}
	}
	/*
	 * ���巽��,��ѯ���ݿ�,��������ȥ��ѯ�����
	 	��ҵ������
	 	2�������ַ���
	 */
	public List<ZhangWu> select(String startDate,String endDate) {
		String sql = "SELECT * FROM gjp_zhangwu WHERE createtime BETWEEN ? AND ?";
		Object[] params = {startDate,endDate};
		try {
			return qr.query(sql, params,new BeanListHandler<>(ZhangWu.class));
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new RuntimeException("������ѯʧ��");
		}
	}
	/*
	 * ����������񷽷�
	 * ��service�����,��zhangwu����������ӵ����ݱ�
	 */
	public void addZhangWu(ZhangWu zw) {
		//ƴд������ݵ�sql
		String sql = "INSERT INTO gjp_zhangwu (flname,money,zhanghu,createtime,description) VALUES (?,?,?,?,?)";
		Object[] params = {zw.getFlname(),zw.getMoney(),zw.getZhanghu(),zw.getCreatetime(),zw.getDescription()};
		//����qr����updateִ�����
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public void edit(ZhangWu zw) {
		//����sql
		String sql = "UPDATE gjp_zhangwu SET flname=?,money=?,zhanghu=?,createtime=?,description=? WHERE zwid=?";
		Object[] params = {zw.getFlname(),zw.getMoney(),zw.getZhanghu(),zw.getCreatetime(),zw.getDescription(),zw.getZwid()};
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	public void deleteZhangWu(int zwid) {
		String sql = "DELETE FROM gjp_zhangwu WHERE ZWID=?";
		try {
			qr.update(sql, zwid);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	
}
