package cn.itcast.shoa.service;

import java.util.Collection;
import java.util.Set;

import cn.itcast.shoa.domain.system.Privilege;

public interface PrivilegeService {
	public Collection<Privilege> getPrivilegesByRid(Long rid);
	
	public void savePrivilege(Long rid,String checkedStr);
	
	public Collection<Privilege> getPrivilegesByUid(Long uid,String username);
	
	public Collection<Privilege> getFunctionsByUid(Long uid);
}
