package cn.itcast.nsfw.role.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;

import cn.itcast.core.dao.impl.BaseDaoImpl;
import cn.itcast.nsfw.role.dao.RoleDao;
import cn.itcast.nsfw.role.entity.Role;

public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

	@Override
	public void deleteRolePrivilegeByRoleId(String roleId) {
		String sql="delete from RolePrivilege where id.role.roleId=?";
		Query query = this.getSession().createQuery(sql);
		query.setParameter(0, roleId);
        query.executeUpdate();
	}

}
