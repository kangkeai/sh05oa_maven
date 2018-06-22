package cn.itcast.shoa.dao;

import java.util.Collection;

import cn.itcast.shoa.dao.base.BaseDao;
import cn.itcast.shoa.domain.system.Privilege;

public interface PrivilegeDao extends BaseDao<Privilege>{
	public Collection<Privilege> getPrivilegesByRid(Long rid);
	
	public Collection<Privilege> getMenuitemsByUid(Long uid,String username);
	
	/**
	 * 根据用户ID得到该用户的功能权限
	 * @param uid
	 * @return
	 */
	public Collection<Privilege> getFunctionsByUid(Long uid);
}
