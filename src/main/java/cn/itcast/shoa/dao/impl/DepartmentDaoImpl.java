package cn.itcast.shoa.dao.impl;

import org.springframework.stereotype.Repository;

import cn.itcast.shoa.dao.DepartmentDao;
import cn.itcast.shoa.dao.base.impl.BaseDaoImpl;
import cn.itcast.shoa.domain.system.Department;

@Repository("departmentDao")
public class DepartmentDaoImpl extends BaseDaoImpl<Department> implements DepartmentDao{
	
}
