package cn.itcast.shoa.struts.action;

import java.util.Collection;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.DefaultActionInvocation;

import cn.itcast.shoa.domain.system.Role;
import cn.itcast.shoa.domain.system.User;
import cn.itcast.shoa.service.RoleService;
import cn.itcast.shoa.service.UserService;
import cn.itcast.shoa.struts.action.base.BaseAction;

@Controller("roleAction")
@Scope("prototype")
public class RoleAction extends BaseAction<Role>{
	
	private Long uid;
	private String checkedStr;
	
	
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getCheckedStr() {
		return checkedStr;
	}

	public void setCheckedStr(String checkedStr) {
		this.checkedStr = checkedStr;
	}

	@Resource(name="roleService")
	private RoleService roleService;
	
	@Resource(name="userService")
	private UserService userService;
	
	public String showAllRole(){
		Collection<Role> roleList = this.roleService.getAllRole();
		ActionContext.getContext().put("roleList",roleList);
		return listAction;
	}
	
	public String addUI(){
		return addUI;
	}
	
	public String add(){
		Role role = new Role();
		BeanUtils.copyProperties(this.getModel(), role);
		this.roleService.saveRole(role);
		return action2action;
	}
	
	public String update(){
		Role role = this.roleService.getRoleById(this.getModel().getRid());
		BeanUtils.copyProperties(this.getModel(), role);
		this.roleService.updateRole(role);
		return action2action;
	}
	
	public String updateUI(){
		Role role = this.roleService.getRoleById(this.getModel().getRid());
		ActionContext.getContext().getValueStack().push(role);
		return updateUI;
	}
	
	public String delete(){
		this.roleService.deleteRole(this.getModel().getRid());
		return action2action;
	}
	
	/**
	 * 加载角色树
	 * @throws Exception 
	 */
	public String showRoleTree() throws Exception{
		Collection<Role> roles = this.roleService.getRolesByUid(uid);
		ActionContext.getContext().getValueStack().push(roles);
		Thread.sleep(6000l);
		return SUCCESS;
	}
	
	/**
	 * 建立用户和角色之间的关系
	 */
	public String saveRole(){
		User user = this.userService.getUserById(this.uid);
		Set<Role> roles = this.roleService.getRolesByIds(this.checkedStr);
		user.setRoles(roles);
		this.userService.updateUser(user);
		return SUCCESS;
	}
}
