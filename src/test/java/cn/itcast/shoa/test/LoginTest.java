package cn.itcast.shoa.test;

import org.junit.Test;

import cn.itcast.shoa.dao.LoginDao;
import cn.itcast.shoa.domain.system.User;

public class LoginTest extends SpringUtils{
	@Test
	public void testLogin(){
		LoginDao loginDao = (LoginDao)context.getBean("loginDao");
		User user = loginDao.getEntryByCondition("from User where username=? and password=?", "aa","1234");
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
	}
}
