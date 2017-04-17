package cn.itcast.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.itcast.core.constant.Constant;
import cn.itcast.core.permission.PermissionCheck;
import cn.itcast.nsfw.user.entity.User;

public class LoginFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//用户登录的拦截过滤器
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse resp=(HttpServletResponse) response;
		//获取当前的请求访问地址
		String uri = req.getRequestURI();
		if(!uri.contains("sys/login_")){
			//非用户登录请求，判断用户所要访问的系统模块
			//判断该属性名对应的值是否为空，不为空，说明用户已登录后向session域中的该值注入了对象
			if(req.getSession().getAttribute(Constant.USER)!=null){
				//对用户访问具体的模块进行一个权限鉴定
				if(uri.contains("nsfw")){
					//访问的是纳税服务模块
					//1.获取用户对象，再从用户对象中取出角色信息
					User user=(User) req.getSession().getAttribute(Constant.USER);
					//2.获取spring的容器对象
					WebApplicationContext applicationContext=WebApplicationContextUtils.getWebApplicationContext(req.getSession().getServletContext());
					//3.通过spring容器对象获取指定的bean对象（权限鉴定器对象）
					PermissionCheck pc=(PermissionCheck) applicationContext.getBean("permissionCheck");
					boolean flag = pc.isAccessible(user,"nsfw");
					if(flag){
						//有相应的权限，跳转
						chain.doFilter(request, response);
					}else{
						//没有权限，跳转到没有权限的提示页面
						resp.sendRedirect(req.getContextPath()+"/sys/login_noPermissionUI.action");
					}
				}else{
					//用户访问的不是纳税服务模块,直接放行 
					chain.doFilter(request, response);
				}
				
			}else{
				//用户信息为空，跳转到登录页面
				resp.sendRedirect(req.getContextPath()+"/sys/login_toLoginUI.action");
			}
			
			
		}else{
			//登录请求，直接放行 
			chain.doFilter(request, response);
		}
		
		
	}
	
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
