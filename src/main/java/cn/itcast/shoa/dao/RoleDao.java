package cn.itcast.shoa.dao;

import java.util.Collection;

import cn.itcast.shoa.dao.base.BaseDao;
import cn.itcast.shoa.domain.system.Role;

public interface RoleDao extends BaseDao<Role>{
	public Collection<Role> getRoles(Long uid);
}
