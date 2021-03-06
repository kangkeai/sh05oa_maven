package cn.itcast.shoa.struts.action;

import java.util.Collection;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.shoa.annotation.privilege.PrivilegeInfo;
import cn.itcast.shoa.domain.system.Department;
import cn.itcast.shoa.domain.system.Role;
import cn.itcast.shoa.domain.system.User;
import cn.itcast.shoa.service.DepartmentService;
import cn.itcast.shoa.service.RoleService;
import cn.itcast.shoa.service.UserService;
import cn.itcast.shoa.struts.action.base.BaseAction;

@Controller("userAction")
@Scope("prototype")
public class UserAction extends BaseAction<User>{
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="departmentService")
	private DepartmentService departmentService;
	
	@Resource(name="roleService")
	private RoleService roleService;
	
	private Long did;//部门ID
	
	private Long[] rids;//被选中的岗位的ID
	
	
	
	public Long getDid() {
		return did;
	}

	public void setDid(Long did) {
		this.did = did;
	}

	public Long[] getRids() {
		return rids;
	}

	public void setRids(Long[] rids) {
		this.rids = rids;
	}

	@PrivilegeInfo(name="用户查询")
	public String showAllUser(){
		Collection<User> userList = this.userService.getAllUser();
		ActionContext.getContext().put("userList", userList);
		return listAction;
	}
	
	public String addUI(){
		/**
		 * 把部门的信息和岗位的信息全部提取出来
		 *    加入两个service
		 */
		Collection<Department> dList = this.departmentService.getAllDepartment();
		Collection<Role> rList = this.roleService.getAllRole();
		ActionContext.getContext().put("dList", dList);
		ActionContext.getContext().put("rList", rList);
		return addUI;
	}
	
	public String add(){
		/**
		 * 1、获取用户的一般属性
		 *    利用模型驱动可以获取
		 * 2、获取页面上选中的部门ID的值
		 * 3、获取岗位的IDS的值(该值有可能有多个)
		 * 4、在保存用户的时候，建立用户和部门之间的关系
		 * 5、在保存用户的时候，建立用户和岗位之间的关系
		 */
		User user = new User();
		//user获取一般的数据
		BeanUtils.copyProperties(this.getModel(), user);
		this.userService.saveUser(did, rids, user);
		return action2action;
	}
	
	/**
	 * 跳转到修改的页面
	 * @return
	 */
	public String updateUI(){
		/**
		 * 1、涉及到用户一般属性的回显
		 * 2、部门信息的回显
		 * 3、岗位信息的回显
		 * 4、把部门信息和岗位信息全部提取出来
		 */
		User user = this.userService.getUserById(this.getModel().getUid());
		//把user放入到对象栈的栈顶，用于用户的一般属性回显
		ActionContext.getContext().getValueStack().push(user);
		/**
		 * 对于部门和岗位的回显来说，应该是部门的did和岗位的rids赋值
		 */
		this.did = user.getDepartment().getDid();
		Set<Role> roles = user.getRoles();
		//对rids进行创建对象及初始化
		this.rids = new Long[roles.size()];
		int index = 0;
		for(Role role:roles){
			this.rids[index] = role.getRid();
			index++;
		}
		
		Collection<Department> dList = this.departmentService.getAllDepartment();
		Collection<Role> rList = this.roleService.getAllRole();
		ActionContext.getContext().put("dList", dList);
		ActionContext.getContext().put("rList", rList);
		return updateUI;
	}
	
	public String update(){
		/**
		 * 1、修改用户的一般属性
		 * 2、重新建立用户和部门之间的关系
		 * 3、重新建立用户和岗位之间的关系
		 */
		//1根据uid把用户信息提取出来
		User user = this.userService.getUserById(this.getModel().getUid());
		//2、把页面上最新的值赋值给user对象
		BeanUtils.copyProperties(this.getModel(), user);
        //3、把did,rids,user传递到service层
		this.userService.updateUser(did, rids, user);
		return action2action;
	}
}
