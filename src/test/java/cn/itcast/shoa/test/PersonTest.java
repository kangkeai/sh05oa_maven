package cn.itcast.shoa.test;

import org.junit.Test;

import cn.itcast.shoa.service.PersonService;

public class PersonTest extends SpringUtils{
	@Test
	public void testPersonService(){
		PersonService personService = (PersonService) context.getBean("personService");
		System.out.println(personService);
	}
}
