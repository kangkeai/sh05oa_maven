package cn.itcast.shoa.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.omg.CORBA.TRANSACTION_MODE;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.shoa.domain.system.Department;
import cn.itcast.shoa.service.DepartmentService;

public class DepartmentTest {
	@Test
	public void testSessionFactory(){
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		DepartmentService departmentService = (DepartmentService) context.getBean("departmentService");
		Department department = new Department();
		department.setName("公关部");
		department.setDescription("美女多");
		departmentService.saveDepartment(department);
	}
	
	@Test
	public void test(){
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Department department = (Department)session.get(Department.class, 1L);
		
		Department department2 = new Department();
		department2.setDid(1L);
		department2.setDescription("aaaa");
		
		session.update(department2);
		transaction.commit();
		session.close();
	}
}
