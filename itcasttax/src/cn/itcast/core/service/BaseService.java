package cn.itcast.core.service;

import java.io.Serializable;
import java.util.List;

import cn.itcast.core.page.PageResult;
import cn.itcast.core.utils.QueryHelper;

public interface BaseService<T> {

	public void save(T entity);
	public void update(T entity);
	public void delete(Serializable id);
	public T findObjectById(Serializable id);
	public List<T> findObjects();
	
	public List<T> findObjects(String hql,List<Object> parameters) throws Exception;
	public List<T> findObjects(QueryHelper queryHelper) throws Exception;
	//分页查询
	public PageResult getPageResult(QueryHelper queryHelper,int pageNo,int pageSize);
}
