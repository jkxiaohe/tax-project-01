package cn.itcast.nsfw.role.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.core.service.impl.BaseServiceImpl;
import cn.itcast.nsfw.role.dao.RoleDao;
import cn.itcast.nsfw.role.entity.Role;
import cn.itcast.nsfw.role.service.RoleService;

//将当前类加入spring的service层中
@Service(value="roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
    
	
	private RoleDao roleDao;
	@Resource
	public void setRoleDao(RoleDao roleDao) {
		super.setBaseDao(roleDao);
		this.roleDao = roleDao;
	}

	@Override
	public void update(Role entity) {
		//更新角色信息之前，先从数据库中删除角色原有的权限
	    roleDao.deleteRolePrivilegeByRoleId(entity.getRoleId());
		roleDao.update(entity);
	}


}
