package cn.itcast.home.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import cn.itcast.core.utils.QueryHelper;
import cn.itcast.nsfw.complain.entity.Complain;
import cn.itcast.nsfw.complain.service.ComplainService;
import cn.itcast.nsfw.user.entity.User;
import cn.itcast.nsfw.user.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class HomeAction extends ActionSupport {

	@Resource
	private UserService userService;    //查询某部门下的所有员工
	
	@Resource
	private ComplainService complainService;   //投诉的工具类对象
	
	private Map<String,Object> return_map;
	//收集complainUI.jsp页面传过来的参数
	private Complain comp;
	
	//跳转到系统首页的方法
	@Override
	public String execute() throws Exception {
		return "home";
	}
	
	//跳转到我要投诉页面
	public String complainAddUI(){
		return "complainAddUI";
	}
	
	//获取某部门下的所有用户对象(使用ajax异步传输)
	public void getUserJson(){
		try{
			//1.获取用户传输过来的部门对象
			String dept=ServletActionContext.getRequest().getParameter("dept");
			if(StringUtils.isNotBlank(dept)){
				//创建查询助手
				QueryHelper queryHelper = new QueryHelper(User.class, "u");
				//2.部门名前面可以模糊查询，但是后面必须精确匹配
				queryHelper.addCondition("u.dept like ?","%"+dept);
				//查询出所有的用户，并且不分页显示
				List<User> userList = userService.findObjects(queryHelper);
				//创建JSON
				JSONObject json=new JSONObject();
				json.put("msg", "success");
				json.accumulate("userList", userList);
				//3.以json的格式输出用户列表
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/html;charset=utf-8");
				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.write(json.toString().getBytes("utf-8"));
				outputStream.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//保存投诉
	public void complainAdd(){
		try{
			if(comp!=null){
				//保存当前用户的投诉
				//下面的这两个参数由系统自动创建，用户只需要填写comp对象的其他参数对象即可
				comp.setState(Complain.COMPLAIN_STATE_UNDONE);  //初始值为未受理
				comp.setCompTime(new Timestamp(new Date().getTime()));  //保存投诉时间
				complainService.save(comp);
				
				//使用ajax输出保存成功后的信息
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/html");
				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.write("success".getBytes("utf-8"));
				outputStream.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public Map<String, Object> getReturn_map() {
		return return_map;
	}

	public void setReturn_map(Map<String, Object> return_map) {
		this.return_map = return_map;
	}

	public Complain getComp() {
		return comp;
	}

	public void setComp(Complain comp) {
		this.comp = comp;
	}
	
	
}
