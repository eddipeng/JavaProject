package cn.itcast.utils;

import java.util.UUID;


public class UUIDUtil {
	
	/**
	 * 获取唯一的字符串
	 * @return
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
/*	@Test
	public void run(){
		String id = UUID.randomUUID().toString().replace("-", "");
		System.out.println(id);
	}*/

}
