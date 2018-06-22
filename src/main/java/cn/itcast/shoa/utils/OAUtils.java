package cn.itcast.shoa.utils;

import java.util.Collection;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shoa.domain.system.Privilege;
import cn.itcast.shoa.domain.system.User;

public class OAUtils {
	public static void putUserToSession(User user){
		ServletActionContext.getRequest()
		.getSession().setAttribute("user", user);
	}
	
	public static void putFunctionsToSession(Collection<Privilege> privileges){
		ServletActionContext.getRequest()
		.getSession().setAttribute("privileges", privileges);
	}
	
	public static User getUserFromSession(){
		return (User)ServletActionContext.getRequest()
		.getSession().getAttribute("user");

	}
}
