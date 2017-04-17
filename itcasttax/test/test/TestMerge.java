package test;


import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.dao.TestDao;
import test.entity.Person;
import test.service.TestService;

public class TestMerge {

	ApplicationContext ac;
	
	@Before
	public void loadac(){
		ac=new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	//测试spring的注解是否成功执行
	@Test
    public void testSpring(){
    	TestService service=(TestService) ac.getBean("testService");
    	service.say();
    }
	
	//测试hibernate的整合是否成功
	@Test
	public void testHibernate(){
		//会话工厂
		SessionFactory sf=(SessionFactory) ac.getBean("sessionFactory");
		//获取会话连接
		Session session = sf.openSession();
		//开启事务
		session.beginTransaction();
//		Person p=new Person("test");
		Person p=new Person("jack");
		session.save(p);
		//提交事务
		session.getTransaction().commit();
		session.close();
	}
	
	//测试dao层是否成功
	@Test
	public void testService(){
		TestDao dao=(TestDao) ac.getBean("testDao");
		Person p=dao.findPerson("4028ee815872e6f9015872e6fa960000");
		System.out.println(p.getName());
	}
	
	//测试回滚事务方法是否成功
	@Test
	public void testRollBack(){
		TestDao dao=(TestDao) ac.getBean("testDao");
		dao.save(new Person("Alice"));
	}
}
