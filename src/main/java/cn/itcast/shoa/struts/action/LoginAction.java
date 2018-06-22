package cn.itcast.shoa.struts.action;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.shoa.dao.LoginDao;
import cn.itcast.shoa.domain.system.Privilege;
import cn.itcast.shoa.domain.system.User;
import cn.itcast.shoa.service.LoginService;
import cn.itcast.shoa.service.PrivilegeService;
import cn.itcast.shoa.struts.action.base.BaseAction;
import cn.itcast.shoa.utils.OAUtils;

@Controller("loginAction")
@Scope("prototype")
public class LoginAction extends BaseAction<User>{
	@Resource(name="loginService")
	private LoginService loginService;
	
	@Resource(name="privilegeService")
	private PrivilegeService privilegeService;
	
	public String login(){
		User user = this.loginService.login(this.getModel().getUsername(), this.getModel().getPassword());
		if(user==null){//用户名或者密码不正确
			this.addActionMessage("用户名或者密码错误");
			return "input";
		}else{//用户名和密码正确
			OAUtils.putUserToSession(user);//把 user放入到session中
			Collection<Privilege> privileges = this.privilegeService.getFunctionsByUid(user.getUid());
			//该用户能够访问到的功能权限放入到了session中
			OAUtils.putFunctionsToSession(privileges);
			return "index";
		}
	}

	public  String loginOut(){
		return "loginOut";
	}
}
