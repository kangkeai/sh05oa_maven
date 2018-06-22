package cn.itcast.shoa.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Repository;

import cn.itcast.shoa.dao.PrivilegeDao;
import cn.itcast.shoa.dao.base.impl.BaseDaoImpl;
import cn.itcast.shoa.domain.system.Privilege;

@Repository("privilegeDao")
public class PrivilegeDaoImpl extends BaseDaoImpl<Privilege> implements PrivilegeDao{

	@Override
	public Collection<Privilege> getPrivilegesByRid(Long rid) {
		Collection<Privilege> allPrivilege = this.getAllEntry();
		Collection<Privilege> rolePrivilege = this.hibernateTemplate.find("from Privilege p inner join fetch p.roles r where r.rid=?",rid);
		for(Privilege privilege:allPrivilege){
			for(Privilege privilege2:rolePrivilege){
				if(privilege.getId().longValue()==privilege2.getId().longValue()){
					privilege.setChecked(true);
					break;
				}
			}
		}
		return allPrivilege;
	}

	@Override
	public Collection<Privilege> getMenuitemsByUid(Long uid,String username) {
		List<Privilege> privileges = null;
		if("admin".equals(username)){
			privileges = this.hibernateTemplate.find("from Privilege where flag='1'");
		}else{
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append("from Privilege p inner join fetch p.roles r inner join fetch r.users u");
			stringBuffer.append("  where u.uid=?");
			stringBuffer.append(" and flag='1'");
			privileges = this.hibernateTemplate.find(stringBuffer.toString(),uid);
		}
		return new HashSet<Privilege>(privileges);
	}

	@Override
	public Collection<Privilege> getFunctionsByUid(Long uid) {
		List<Privilege> privileges = null;
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("from Privilege p inner join fetch p.roles r inner join fetch r.users u");
		stringBuffer.append(" where u.uid=?");
		stringBuffer.append(" and flag='2'");
		privileges = this.hibernateTemplate.find(stringBuffer.toString(),uid);
		return new HashSet<Privilege>(privileges);
	}
}
