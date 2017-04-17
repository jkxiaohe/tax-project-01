package cn.itcast.nsfw.user.dao;

import java.util.List;

import cn.itcast.core.dao.BaseDao;
import cn.itcast.nsfw.user.entity.User;
import cn.itcast.nsfw.user.entity.UserRole;

public interface UserDao extends BaseDao<User> {

	//属于用户自己的方法（不是所有类通用的CURD）
	public List<User> findUserByAccountAndId(String id,String account);
	
	//保存用户及角色
	public void saveUserRole(UserRole userRole);
	//根据用户的id删除该用户角色（删除该用户及该用户所拥有的角色）
	public void deleteUserRoleByUserId(String id);
	public List<UserRole> getUserRolesByUserId(String id); 
	//根据用户的账号和密码查询用户列表
    public List<User> findUsersByAccountAndPass(String account,String password);
	
}
