package cn.itcast.shoa.struts.action;

import java.util.List;

import cn.itcast.shoa.domain.Person;

import com.opensymphony.xwork2.ActionSupport;

public class TableAction extends ActionSupport{
	private List<Person> persons;

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
	
	
	public String showPersons(){
		for(Person person:persons){
			System.out.print(person.getPid()+","+person.getName()+","+person.getSex());
			System.out.println();
		}
		return null;
	}
}
