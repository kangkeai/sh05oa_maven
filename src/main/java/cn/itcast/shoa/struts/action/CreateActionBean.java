package cn.itcast.shoa.struts.action;

import java.util.Map;

import com.opensymphony.xwork2.ObjectFactory;
import com.opensymphony.xwork2.config.entities.ActionConfig;

public class CreateActionBean extends ObjectFactory{
	@Override
	public Object buildAction(String actionName, String namespace,
			ActionConfig config, Map<String, Object> extraContext)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		return null;
	}
}
