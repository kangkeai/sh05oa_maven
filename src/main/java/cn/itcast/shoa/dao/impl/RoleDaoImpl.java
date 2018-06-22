package cn.itcast.shoa.dao.impl;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import cn.itcast.shoa.dao.RoleDao;
import cn.itcast.shoa.dao.base.impl.BaseDaoImpl;
import cn.itcast.shoa.domain.system.Role;

@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao{

	@Override
	public Collection<Role> getRoles(Long uid) {
		/**
		 * 1、把所有的角色全部提取出来
		 * 2、把用户能够访问到的角色提取出来
		 * 3、遍历循环所有的角色 
		 *      再遍历用户能够访问到的角色
		 *          如果正在遍历的所有的角色当中的某一个角色正好是用户能够访问到的，那么设置checked为true
		 */
		Collection<Role> allRoles = this.getAllEntry();
		Collection<Role> userRoles = this.hibernateTemplate.find("from Role r inner join fetch r.users u where u.uid=?",uid);
		for(Role role:allRoles){//遍历所有的role
			for(Role role2:userRoles){//遍历用户能够访问到的role
				if(role.getRid().longValue()==role2.getRid().longValue()){
					role.setChecked(true);
					break;
				}
			}
		}
		return allRoles;
	}

}
