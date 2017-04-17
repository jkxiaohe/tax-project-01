package cn.itcast.core.permission.impl;

import java.util.List;

import javax.annotation.Resource;

import cn.itcast.core.permission.PermissionCheck;
import cn.itcast.nsfw.role.entity.Role;
import cn.itcast.nsfw.role.entity.RolePrivilege;
import cn.itcast.nsfw.user.entity.User;
import cn.itcast.nsfw.user.entity.UserRole;
import cn.itcast.nsfw.user.service.UserService;

public class PermissionCheckImpl implements PermissionCheck {

	@Resource
	private UserService userService;
	
	@Override
	public boolean isAccessible(User user, String code) {
		//1.首先获取用户所拥有的角色
		List<UserRole> list = user.getUserRoles();
		if(list==null){
			list=userService.getUserRolesByUserId(user.getId());
		}
		//2.对每个角色所拥有的权限进行对比，判断是否拥有指定的code权限值
		if(list!=null && list.size()>0){
			for(UserRole ur : list){
				Role role = ur.getId().getRole();
				for(RolePrivilege rp : role.getRolePrivileges()){
					if(code.equals(rp.getId().getCode())){
						return true;
					}
				}
			}
		}
		//3.如果没有找到，返回false
		return false;
	}

}
