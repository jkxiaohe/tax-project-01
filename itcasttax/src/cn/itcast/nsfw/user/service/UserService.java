package cn.itcast.nsfw.user.service;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.servlet.ServletOutputStream;

import cn.itcast.core.exception.ServiceException;
import cn.itcast.core.service.BaseService;
import cn.itcast.nsfw.user.entity.User;
import cn.itcast.nsfw.user.entity.UserRole;

public interface UserService extends BaseService<User>{

    
	//导出excel文件
	public void exportExcel(List<User> userList,ServletOutputStream output);
	//读取excel文件
	public void importExcel(File userExcel,String fileName);
	//对用户账号进行唯一性校验
	public List<User> findUserByAccountAndId(String id,String account);
	
	//保存用户及角色()
	public void saveUserAndRole(User user,String... roleIds);
	//更新用户及角色
	public void updateUserAndRole(User user,String... roleIds);
	//根据用户的id删除该用户角色（删除该用户所拥有的角色）
	public void deleteUserRoleByUserId(String id);
	//查找该用户所对应的角色（角色可能不止有一个）
	public List<UserRole> getUserRolesByUserId(String id); 
	//根据用户的账号和密码查询用户列表
	public List<User> findUsersByAccountAndPass(String account,String password);
	
}
