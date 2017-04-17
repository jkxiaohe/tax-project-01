package cn.itcast.nsfw.user.entity;

import java.io.Serializable;

public class UserRole implements Serializable{

	//用户与角色也存在多对多的关系，所以也需要通过复合主键来维持一张中间表
    private UserRoleId id;

	public UserRoleId getId() {
		return id;
	}

	public void setId(UserRoleId id) {
		this.id = id;
	}

	public UserRole(UserRoleId id) {
		super();
		this.id = id;
	}

	public UserRole() {
	}
    
	
}
