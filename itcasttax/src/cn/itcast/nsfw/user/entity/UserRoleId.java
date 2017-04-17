package cn.itcast.nsfw.user.entity;

import java.io.Serializable;

import cn.itcast.nsfw.role.entity.Role;

//作为用户与角色之间的联合主键的类
public class UserRoleId implements Serializable{

	private Role role;
	private String userId;
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	public UserRoleId(Role role, String userId) {
		super();
		this.role = role;
		this.userId = userId;
	}
	public UserRoleId() {
	}
	
	
}
