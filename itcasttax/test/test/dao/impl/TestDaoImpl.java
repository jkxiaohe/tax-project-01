package test.dao.impl;

import java.io.Serializable;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import test.dao.TestDao;
import test.entity.Person;



//继承hibernate对dao 的工具类
public class TestDaoImpl extends HibernateDaoSupport  implements TestDao {

	@Override
	public void save(Person person) {
//		int i=1/0;
		getHibernateTemplate().save(person);
	}

	@Override
	public Person findPerson(Serializable id) {
		return getHibernateTemplate().get(Person.class,id);
	}

}
