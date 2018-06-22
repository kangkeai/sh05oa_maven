package cn.itcast.shoa.dao.impl;

import org.springframework.stereotype.Repository;

import cn.itcast.shoa.dao.UserDao;
import cn.itcast.shoa.dao.base.impl.BaseDaoImpl;
import cn.itcast.shoa.domain.system.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

}
