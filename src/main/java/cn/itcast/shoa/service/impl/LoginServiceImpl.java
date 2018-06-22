package cn.itcast.shoa.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.shoa.dao.LoginDao;
import cn.itcast.shoa.domain.system.User;
import cn.itcast.shoa.service.LoginService;

@Service("loginService")
public class LoginServiceImpl implements LoginService{
	@Resource(name="loginDao")
	private LoginDao loginDao;
	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		return this.loginDao.getEntryByCondition("from User where username=? and password=?", username,password);
	}
}
