package cn.itcast.shoa.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shoa.dao.RoleDao;
import cn.itcast.shoa.domain.system.Role;
import cn.itcast.shoa.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService{
	@Resource(name="roleDao")
	private RoleDao roleDao;

	@Override
	public Collection<Role> getAllRole(){
		// TODO Auto-generated method stub
		
		return this.roleDao.getAllEntry();
	}

	@Override
	public Role getRoleById(Serializable id) {
		// TODO Auto-generated method stub
		return this.roleDao.getEntryById(id);
	}

	@Transactional(readOnly=false)
	public void updateRole(Role role) {
		// TODO Auto-generated method stub
		this.roleDao.updateEntry(role);
	}

	@Transactional(readOnly=false)
	public void deleteRole(Serializable id) {
		// TODO Auto-generated method stub
		this.roleDao.deleteEntry(id);
	}

	@Transactional(readOnly=false)
	public void saveRole(Role role) {
		// TODO Auto-generated method stub
		this.roleDao.saveEntry(role);
	}

	@Override
	public Set<Role> getRolesByIds(String ids) {
		// TODO Auto-generated method stub
		return this.roleDao.getEntrysByIDS(ids);
	}

	@Override
	public Collection<Role> getRolesByUid(Long uid) {
		// TODO Auto-generated method stub
		return this.roleDao.getRoles(uid);
	}
}
