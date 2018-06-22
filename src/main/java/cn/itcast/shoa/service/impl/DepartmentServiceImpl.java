package cn.itcast.shoa.service.impl;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.Result;

import cn.itcast.shoa.dao.DepartmentDao;
import cn.itcast.shoa.domain.system.Department;
import cn.itcast.shoa.service.DepartmentService;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService{
	@Resource(name="departmentDao")
	private DepartmentDao departmentDao;
	@Override
	public Collection<Department> getAllDepartment() {
		// TODO Auto-generated method stub
		return this.departmentDao.getAllEntry();
	}
	@Transactional(readOnly=false)
	public void saveDepartment(Department department) {
		this.departmentDao.saveEntry(department);
	}
	@Transactional(readOnly=false)
	public void deleteDepartment(Long id) {
		// TODO Auto-generated method stub
		this.departmentDao.deleteEntry(id);
	}
	@Override
	public Department getDepartmentById(Serializable id) {
		// TODO Auto-generated method stub
		return this.departmentDao.getEntryById(id);
	}
	@Transactional(readOnly=false)
	public void updateDepartment(Department department) {
		// TODO Auto-generated method stub
		this.departmentDao.updateEntry(department);
	}
}
