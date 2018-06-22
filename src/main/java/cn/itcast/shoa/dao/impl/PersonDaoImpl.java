package cn.itcast.shoa.dao.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.opensymphony.xwork2.ObjectFactory;

import cn.itcast.shoa.dao.PersonDao;
import cn.itcast.shoa.dao.base.impl.BaseDaoImpl;
import cn.itcast.shoa.domain.Person;

@Repository("personDao")
public class PersonDaoImpl extends BaseDaoImpl<Person> implements PersonDao{

}
