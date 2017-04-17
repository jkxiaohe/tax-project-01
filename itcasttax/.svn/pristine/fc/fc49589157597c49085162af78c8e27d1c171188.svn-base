package test.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import test.dao.TestDao;
import test.entity.Person;
import test.service.TestService;

//加入spring的服务层
@Service(value="testService")
public class TestServiceImpl implements TestService {

	//通过spring注入指定的值
	@Resource
	private TestDao testDao;
	
	@Override
	public void say() {
		System.out.println("service saying hi.");
	}

	@Override
	public void save(Person person) {
		testDao.save(person);
	}

	@Override
	public Person findPerson(Serializable id) {
		save(new Person("test"));
		return testDao.findPerson(id);
	}

}
