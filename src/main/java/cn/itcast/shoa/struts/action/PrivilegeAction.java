package cn.itcast.shoa.struts.action;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.shoa.domain.system.Privilege;
import cn.itcast.shoa.domain.system.Role;
import cn.itcast.shoa.domain.system.User;
import cn.itcast.shoa.service.PrivilegeService;
import cn.itcast.shoa.service.RoleService;
import cn.itcast.shoa.struts.action.base.BaseAction;
import cn.itcast.shoa.utils.OAUtils;

@Controller("privilegeAction")
@Scope("prototype")
public class PrivilegeAction extends BaseAction<Privilege>{
	@Resource(name="privilegeService")
	private PrivilegeService privilegeService;
	
	@Resource(name="roleService")
	private RoleService roleService;
	
	private Long rid;
	
	private String checkedStr;
	
	public String getCheckedStr() {
		return checkedStr;
	}

	public void setCheckedStr(String checkedStr) {
		this.checkedStr = checkedStr;
	}

	public Long getRid() {
		return rid;
	}

	public void setRid(Long rid) {
		this.rid = rid;
	}

	public String showPrivilegeByRid(){
		Collection<Privilege> privileges = this.privilegeService.getPrivilegesByRid(rid);
		ActionContext.getContext().getValueStack().push(privileges);
		return SUCCESS;
	}
	
	public String showMenuitemTreeByUid(){
		User user = OAUtils.getUserFromSession();
		Collection<Privilege> privileges = this.privilegeService.getPrivilegesByUid(user.getUid(),user.getUsername());
		ActionContext.getContext().getValueStack().push(privileges);
		return SUCCESS;
	}
	
	public String savePrivilege(){
		this.privilegeService.savePrivilege(rid, checkedStr);
		return SUCCESS;
	}
}
