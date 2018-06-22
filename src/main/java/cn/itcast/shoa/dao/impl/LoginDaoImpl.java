package cn.itcast.shoa.dao.impl;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.itcast.shoa.dao.LoginDao;
import cn.itcast.shoa.dao.base.impl.BaseDaoImpl;
import cn.itcast.shoa.domain.system.User;

@Repository("loginDao")
public class LoginDaoImpl extends BaseDaoImpl<User> implements LoginDao{
	
}
