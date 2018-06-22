package cn.itcast.shoa.service;

import cn.itcast.shoa.domain.system.User;

public interface LoginService {
	public User login(String username,String password);
}
