package cn.itcast.core.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.core.dao.BaseDao;
import cn.itcast.core.page.PageResult;
import cn.itcast.core.utils.QueryHelper;

public  class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	private Class<T> clazz;
	public BaseDaoImpl(){
		ParameterizedType pt=(ParameterizedType) this.getClass().getGenericSuperclass();
		clazz=(Class<T>) pt.getActualTypeArguments()[0];
	}
	
	@Override
	public void save(T entity) {
		this.getHibernateTemplate().save(entity);
	}

	@Override
	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
	}

	@Override
	public void delete(Serializable id) {
		this.getHibernateTemplate().delete(findObjectById(id));
	}

	@Override
	public T findObjectById(Serializable id) {
		return this.getHibernateTemplate().get(clazz, id);
	}

	@Override
	public List<T> findObjects() {
		Query query = this.getSession().createQuery("from "+clazz.getSimpleName());
		return query.list();
	}

	@Override
	public List<T> findObjects(String hql, List<Object> parameters)
			throws Exception {
		//hql代表查询的sql语句，parameters代表占位符的值
		Query query = this.getSession().createQuery(hql);
		if(parameters!=null){
			for(int i=0;i<parameters.size();++i){
				query.setParameter(i, parameters.get(i));
			}
		}
		return query.list();
	}

	@Override
	public List<T> findObjects(QueryHelper queryHelper) throws Exception {
		//通过工具类获取查询的hql语句和所有参数
		Query query = this.getSession().createQuery(queryHelper.getListQueryHql());
		List<Object> paramsList = queryHelper.getParameters();
		if(paramsList!=null){
			//在hql语句中注入指定的参数值
			for(int i=0;i<paramsList.size();++i){
				query.setParameter(i, paramsList.get(i));
			}
		}
		return query.list();
	}

	@Override
	public PageResult getPageResult(QueryHelper queryHelper, int pageNo,
			int pageSize) {
		String hql = queryHelper.getListQueryHql();
		List<Object> parameters = queryHelper.getParameters();
		Query query = this.getSession().createQuery(hql);
		
		//查询列表数据
		if(parameters!=null){
			for(int i=0;i<parameters.size();++i){
				query.setParameter(i, parameters.get(i));
			}
		}
		//当前页号至少为第一页 --->> (页号-1)*页大小  = 查询记录的索引号
		if(pageNo==0) pageNo=1;
		query.setFirstResult((pageNo-1)*pageSize);       //设置查询的起始位置
		query.setMaxResults(pageSize);          //设置查询的页大小
		List items = query.list();
		
	    //查询总记录数
		Query totalQuery = this.getSession().createQuery(queryHelper.getCountHql());
		if(parameters!=null){
			for(int i=0;i<parameters.size();++i){
				totalQuery.setParameter(i, parameters.get(i));
			}
		}
		long totalCount = (Long) totalQuery.uniqueResult();
		return new PageResult(totalCount, pageNo, pageSize, items);
	}

}
