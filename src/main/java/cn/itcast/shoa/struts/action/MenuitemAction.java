package cn.itcast.shoa.struts.action;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.shoa.domain.menuitem.Menuitem;
import cn.itcast.shoa.service.MenuitemService;
import cn.itcast.shoa.struts.action.base.BaseAction;

@Controller("menuitemAction")
@Scope("prototype")
public class MenuitemAction extends BaseAction<Menuitem>{
	@Resource(name="menuitemService")
	private MenuitemService menuitemService;
	
	public String showAllMenuitem(){
		Collection<Menuitem> menuitems = this.menuitemService.getAllMenuitem();
		ActionContext.getContext().getValueStack().push(menuitems);
		return SUCCESS;
	}
}
