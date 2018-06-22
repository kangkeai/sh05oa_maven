package cn.itcast.shoa.struts.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.ModelDrivenInterceptor;
import com.opensymphony.xwork2.interceptor.ParametersInterceptor;

import cn.itcast.shoa.dao.A;
import cn.itcast.shoa.domain.system.Department;
import cn.itcast.shoa.domain.system.User;
import cn.itcast.shoa.service.DepartmentService;
import cn.itcast.shoa.struts.action.base.BaseAction;

@Controller("departmentAction")
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department>{
	@Resource(name="departmentService")
	private DepartmentService departmentService;
	
	public String showAllDepartment(){
		ActionContext.getContext().getValueStack().pop();
		ActionContext.getContext().getValueStack().peek();
		Collection<Department> departmentList = this.departmentService.getAllDepartment();
		ActionContext.getContext().put("departmentList", departmentList);
		return listAction;
	}
	
	public String showList(){
		Department department  = new Department();
		department.setName("王二麻子");
		List<Department> dList = new ArrayList<Department>();
		dList.add(department);
		ActionContext.getContext().put("dList", dList);
		return listAction;
	}
	
	public String showMap(){
		Department department  = new Department();
		department.setName("王二麻子");
		Map<String, Department> map = new HashMap<String, Department>();
		map.put("d1",department);
		ActionContext.getContext().put("map", map);
		return listAction;
	}
	
	public String showListMap(){
		List<Map<String, Department>> list = new ArrayList<Map<String,Department>>();
		Map<String, Department> map = new HashMap<String, Department>();
		Department department  = new Department();
		department.setName("王二麻子");
		map.put("d1", department);
		list.add(map);
		ActionContext.getContext().put("listMap", list);
		return listAction;
	}
	
	public String showMapList(){
		Map<String, List<Department>> map = new HashMap<String, List<Department>>();
		List<Department> list = new ArrayList<Department>();
		Department department  = new Department();
		department.setName("王二麻子");
		list.add(department);
		map.put("list1", list);
		ActionContext.getContext().put("mapList", map);
		return listAction;
	}
	
	public String showListMapList(){
		List<Map<String, List<Department>>> list = new ArrayList<Map<String,List<Department>>>();
		
		Department department  = new Department();
		department.setName("王二麻子");
		List<Department> dList = new ArrayList<Department>();
		dList.add(department);
		
		Map<String, List<Department>> map = new HashMap<String, List<Department>>();
		map.put("map1", dList);
		list.add(map);
		ActionContext.getContext().put("listMapList", list);
		return listAction;
	}
	
	public String addUI(){
		return addUI;
	}
	
	public String add(){
		Department department = new Department();
		/**
		 * 把model的值赋值给了department
		 */
		BeanUtils.copyProperties(this.getModel(), department);
		this.departmentService.saveDepartment(department);
		return action2action;
	}
	
	public String delete(){
		SessionFactory sessionFactory = null;
		sessionFactory.getCurrentSession();
		/**
		 * 1、提取department对象
		 * 2、获取该部门的所有的用户
		 */
		Department department = this.departmentService.getDepartmentById(this.getModel().getDid());
		//提取该部门的用户
		Set<User> users = department.getUsers();
		for(User user:users){
			user.setDepartment(null);//解除user和department之间的关系
		}
		this.departmentService.deleteDepartment(this.getModel().getDid());
		return action2action;
	}
	
	public String updateUI(){
		Department department = this.departmentService.getDepartmentById(this.getModel().getDid());
		ActionContext.getContext().getValueStack().push(department);
		ActionContext.getContext().getValueStack().setParameter("name", "bbbb");
		return updateUI;
	}
	
	public String update(){
		Department department =  this.departmentService.getDepartmentById(this.getModel().getDid());
		BeanUtils.copyProperties(this.getModel(), department);
		this.departmentService.updateDepartment(department);
		return action2action;
	}
}
