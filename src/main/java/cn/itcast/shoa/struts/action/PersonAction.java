package cn.itcast.shoa.struts.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.shoa.domain.Person;
import cn.itcast.shoa.service.PersonService;
import cn.itcast.shoa.struts.action.base.BaseAction;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("personAction")
@Scope("prototype")
public class PersonAction extends BaseAction<Person>{
	@Resource(name="personService")
	private PersonService personService;
	
	
	public String savePerson(){
		Person person = new Person();
		person.setName("王二麻子");
		this.personService.savePerson(person);
		return null;
	}
}
