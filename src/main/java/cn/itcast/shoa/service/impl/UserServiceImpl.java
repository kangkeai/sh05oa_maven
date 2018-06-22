package cn.itcast.shoa.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shoa.dao.DepartmentDao;
import cn.itcast.shoa.dao.RoleDao;
import cn.itcast.shoa.dao.UserDao;
import cn.itcast.shoa.domain.system.Department;
import cn.itcast.shoa.domain.system.Role;
import cn.itcast.shoa.domain.system.User;
import cn.itcast.shoa.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	@Resource(name="userDao")
	private UserDao userDao;
	
	@Resource(name="departmentDao")
	private DepartmentDao departmentDao;
	
	@Resource(name="roleDao")
	private RoleDao roleDao;

	@Override
	public Collection<User> getAllUser() {
		// TODO Auto-generated method stub
		return this.userDao.getAllEntry();
	}

	@Transactional(readOnly=false)
	public void saveUser(Long did,Long[] rids,User user) {
		/**
		 * 1、建立用户和部门之间的关系
		 * 2、建立用户和岗位之间的关系
		 *     用户来维护用户和部门之间的关系，用户维护用户和岗位之间的关系
		 */
		Department department = this.departmentDao.getEntryById(did);
		//建立用户和部门之间的关系
		user.setDepartment(department);
		Set<Role> roles = this.roleDao.getEntrysByIDS(rids);
		//建立用户和岗位之间的关系
		user.setRoles(roles);
		this.userDao.saveEntry(user);
	}

	@Transactional(readOnly=false)
	public void updateUser(Long did,Long[] rids,User user) {
		// TODO Auto-generated method stub
		Department department = this.departmentDao.getEntryById(did);
		//重新建立用户和部门之间的关系
		user.setDepartment(department);
		Set<Role> roles = this.roleDao.getEntrysByIDS(rids);
		//重新建立用户和岗位之间的关系
		user.setRoles(roles);
		this.userDao.updateEntry(user);
	}

	@Transactional(readOnly=false)
	public void deleteUser(Serializable id) {
		// TODO Auto-generated method stub
		this.userDao.deleteEntry(id);
	}

	@Override
	public User getUserById(Serializable id) {
		// TODO Auto-generated method stub
		return this.userDao.getEntryById(id);
	}

	@Transactional(readOnly=false)
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		this.userDao.updateEntry(user);
	}
}
