package cn.itcast.shoa.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import cn.itcast.shoa.domain.system.Role;

public interface RoleService {
	public Collection<Role> getAllRole();
	
	public Role getRoleById(Serializable id);
	
	public void updateRole(Role role);
	
	public void deleteRole(Serializable id);
	
	public void saveRole(Role role);
	
	public Set<Role> getRolesByIds(String ids);
	
	public Collection<Role> getRolesByUid(Long uid);
}
