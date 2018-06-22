package cn.itcast.shoa.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shoa.dao.PersonDao;
import cn.itcast.shoa.domain.Person;
import cn.itcast.shoa.service.PersonService;

@Service("personService")
public class PersonServiceImpl implements PersonService{
	@Resource(name="personDao")
	private PersonDao personDao;

	public PersonDao getPersonDao() {
		return personDao;
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	@Transactional(readOnly=false)
	public void savePerson(Person person) {
		// TODO Auto-generated method stub
		this.personDao.saveEntry(person);
	}
}
