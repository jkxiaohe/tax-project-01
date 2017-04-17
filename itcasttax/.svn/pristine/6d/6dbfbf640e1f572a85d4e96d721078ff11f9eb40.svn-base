package cn.itcast.nsfw.user.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;

import cn.itcast.core.dao.impl.BaseDaoImpl;
import cn.itcast.nsfw.user.dao.UserDao;
import cn.itcast.nsfw.user.entity.User;
import cn.itcast.nsfw.user.entity.UserRole;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public List<User> findUserByAccountAndId(String id, String account) {
		//创建sql语句到DB中查询
		//如果是在add.jsp中，那么只需要这条sql就行了
		String sql="from User where account=?";
		//如果是edit.jsp，那么还需要排除掉编辑用户的id值
		if(StringUtils.isNotBlank(id)){
			sql+=" and id!=?";
		}
		//创建hibernate的hql查询对象
		Query query = this.getSession().createQuery(sql);
		query.setParameter(0, account);
		if(StringUtils.isNotBlank(id)){
			query.setParameter(1, id);
		}
		return query.list();
	}

	@Override
	public void saveUserRole(UserRole userRole) {
		this.getHibernateTemplate().save(userRole);
	}

	@Override
	public void deleteUserRoleByUserId(String id) {
		//删除用户拥有的角色
		String sql="delete from UserRole where id.userId=?";
		Query query = this.getSession().createQuery(sql);
		query.setParameter(0, id);
		query.executeUpdate();
	}

	@Override
	public List<UserRole> getUserRolesByUserId(String id) {
		//根据用户的id查询所对应的所有角色
		String sql="from UserRole where id.userId=?";
		Query query = this.getSession().createQuery(sql);
		query.setParameter(0, id);
		return query.list();
	}

	@Override
	public List<User> findUsersByAccountAndPass(String account, String password) {
		String sql="from User where account=? and password=?";
		Query query = this.getSession().createQuery(sql);
		query.setParameter(0, account);
		query.setParameter(1, password);
		return query.list();
	}
	
}
