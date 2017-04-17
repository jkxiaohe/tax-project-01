package cn.itcast.nsfw.role.action;

import java.net.URLDecoder;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import cn.itcast.core.action.BaseAction;
import cn.itcast.core.constant.Constant;
import cn.itcast.core.utils.QueryHelper;
import cn.itcast.nsfw.role.entity.Role;
import cn.itcast.nsfw.role.entity.RolePrivilege;
import cn.itcast.nsfw.role.entity.RolePrivilegeId;
import cn.itcast.nsfw.role.service.RoleService;

import com.opensymphony.xwork2.ActionContext;

public class RoleAction extends BaseAction {

	@Resource
	private RoleService roleService;   //通过spring注入工具类对象
	private Role role;           //保存、更新、删除单个角色时设置的变量
	private String[] privilegeIds;     //保存某一用户的所有权限数组
	private String strName;       //保存搜索变量值
	
	//列表页面显示所有角色信息
	public String listUI() throws Exception{
		try {
			//加载权限集合的显示值
			ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVILEGE_MAP);
			//创建查询工具类
			QueryHelper queryHelper = new QueryHelper(Role.class, "r");
			if(role!=null){
				if(StringUtils.isNotBlank(role.getName())){
					//对传过来的查询条件进行指定格式的解码
					role.setName(URLDecoder.decode(role.getName(),"utf-8"));
					queryHelper.addCondition(" r.name like ?", "%"+role.getName()+"%");
				}
			}
			pageResult=roleService.getPageResult(queryHelper, getPageNo(), getPageSize());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return "listUI";
	}
	
	//跳转到保存页面
	public String addUI(){
		ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVILEGE_MAP);
		return "addUI";
	}
	
	//处理保存
	public String add(){
		try {
			//如果用户填写的内容不为空
			if(role!=null){
				//将封装好的权限集合传到角色role中
				Set<RolePrivilege> set=new HashSet<RolePrivilege>();
				//迭代权限数组，将用户所拥有的所有权限到封装到该set集合中，并再次赋值给role用户
				for(int i=0;i<privilegeIds.length;++i){
					set.add(new RolePrivilege(new RolePrivilegeId(role,privilegeIds[i])));
				}
				//将该权限集合添加给role
				role.setRolePrivileges(set);
				//保存角色
				roleService.save(role);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}
	
	//跳转到编辑页面
	public String editUI(){
		ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVILEGE_MAP);  //存放到非根
		if(role!=null && role.getRoleId()!=null){
			strName=role.getName();
			role=roleService.findObjectById(role.getRoleId());
			//判断当前角色的权限是否为空
			if(role.getRolePrivileges()!=null){
				//取出角色的权限信息，封装到权限数组中，用于权限的数据回显
				privilegeIds=new String[role.getRolePrivileges().size()];
				int i=0;
				for(RolePrivilege rp : role.getRolePrivileges()){
					privilegeIds[i++]=rp.getId().getCode();
				}
			}
			
		}
		return "editUI";
	}
	
	//处理编辑
	public String edit(){
		try {
			if(role!=null){
				if(privilegeIds!=null){
					Set<RolePrivilege> set=new HashSet<RolePrivilege>();
					for(int i=0;i<privilegeIds.length;++i){
						set.add(new RolePrivilege(new RolePrivilegeId(role, privilegeIds[i])));
					}
					//将权限数组封装到role对象中
					role.setRolePrivileges(set);
				}
				//更新操作
				roleService.update(role);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}
	
	//删除单个角色
	public String delete(){
		strName=role.getName();
		roleService.delete(role.getRoleId());
		return "list";
	}
	
	//批量删除用户
	public String deleteAll(){
		if(selectedRow != null){
			strName=role.getName();
			for(String id : selectedRow){
				roleService.delete(id);
			}
		}
		return "list";
	}

	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String[] getPrivilegeIds() {
		return privilegeIds;
	}
	public void setPrivilegeIds(String[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}
	public String getStrName() {
		return strName;
	}
	public void setStrName(String strName) {
		this.strName = strName;
	}
	
}
