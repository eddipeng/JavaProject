package cn.itcast.service;

import cn.itcast.dao.UserDao;
import cn.itcast.dao.UserDaoImple;
import cn.itcast.utils.MailUtil;
import cn.itcast.utils.UUIDUtil;
import cn.itcast.vo.User;

/*
 * 用户相关的业务类
 */
public class UserService {

	public boolean regisUser(User user) {
		//调用持久层
		UserDao dao = new UserDaoImple();
		/*
		 * 添加业务
		 * 1.主键
		 * 2.设置用户状态
		 * 3.生成激活码
		 */
		//生成主键
		user.setUid(UUIDUtil.getUUID());
		//设置状态码为0
		user.setState(0);
		//生成激活码
		String code = UUIDUtil.getUUID()+UUIDUtil.getUUID();
		user.setCode(code);
		//发送邮件
		MailUtil.sendMail(user.getEmail(), code);
		return dao.saveUser(user);
		
	}

	public User findUserByCode(String code) {
		// TODO Auto-generated method stub
		UserDao dao = new UserDaoImple();
		return dao.findUserByCode(code);
	}
	/*
	 * 修改方法
	 */
	public void updateUser(User user) {
		UserDao dao = new UserDaoImple();
		dao.updateUser(user);
	}

	public User login(User user) {
		// TODO Auto-generated method stub
		UserDao dao = new UserDaoImple();
		return dao.login(user);
	}
	
}
