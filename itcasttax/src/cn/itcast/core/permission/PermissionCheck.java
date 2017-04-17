package cn.itcast.core.permission;

import cn.itcast.nsfw.user.entity.User;

public interface PermissionCheck {

	//鉴定用户是否有指定模块的权限（通过code值来判断）
	/*
	 * 1.传入要鉴别权限的用户对象
	 * 2.要访问的模块权限值
	 * 3.返回值说明该用户是否有访问该权限的信息
	 */
	public boolean isAccessible(User user,String code);
	
}
