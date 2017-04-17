package cn.itcast.login.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import cn.itcast.core.constant.Constant;
import cn.itcast.nsfw.user.entity.User;
import cn.itcast.nsfw.user.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

//处理用户的登录与注销操作
public class LoginAction extends ActionSupport{

	@Resource
	private UserService userService;    //工具类对象
	private User user;     //将登录的用户信息存储在user对象中
	private String loginResult;     //登录操作的结果
	
	//跳转到登录页面
	public String toLoginUI(){
		return "loginUI";
	}
	
	//处理用户的登录
	public String login(){
		//判断用户的信息是否为空
		if(user!=null){
			//用户的信息不为空，判断用户的账号和密码是否正确（去DB中查询是否存在）
			if(StringUtils.isNotBlank(user.getAccount()) && StringUtils.isNotBlank(user.getPassword())){
				//对账号和密码的正确性进行判断---->交给工具类去完成
//				System.out.println(user.getAccount()+"   "+user.getPassword());
				List<User> list = userService.findUsersByAccountAndPass(user.getAccount(), user.getPassword());
				if(list!=null && list.size()>0){
					//2.1登录成功
					User user=list.get(0);
					//2.2根据用户id查询该用户具有的所有角色
					user.setUserRoles(userService.getUserRolesByUserId(user.getId()));
					//2.3将用户的信息保存在session域中
					ServletActionContext.getRequest().getSession().setAttribute(Constant.USER, user);
					//2.4将用户的登录信息保存到登录日志中
					Log log=LogFactory.getLog(getClass());
					log.info("用户名称为: "+user.getName()+" 的用户登录了系统");
					//2.5重定向到首页
					return "home";
				}else{
					loginResult="用户名或密码不正确";
				}
			}else{
				//用户的账号或密码为空，
				loginResult="用户名或密码不能为空";
			}
		}else{
			//将登录结果存放到loginResult中
			loginResult="用户不能为空";
		}
		return toLoginUI();
	}
	
	//退出，注销操作
	public String logout(){
		//从服务器域中删除该登录用户的相关信息
		ServletActionContext.getRequest().getSession().removeAttribute(Constant.USER);
		return toLoginUI();
	}
	
	//当用户访问某个子系统但是权限不存在时，跳转到权限不存在的页面
	public String toNoPermissionUI(){
		return "noPermissionUI";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getLoginResult() {
		return loginResult;
	}

	public void setLoginResult(String loginResult) {
		this.loginResult = loginResult;
	}
	
	
	
}
