package cn.itcast.nsfw.user.action;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.core.action.BaseAction;
import cn.itcast.core.exception.ActionException;
import cn.itcast.core.exception.ServiceException;
import cn.itcast.core.utils.QueryHelper;
import cn.itcast.nsfw.role.service.RoleService;
import cn.itcast.nsfw.user.entity.User;
import cn.itcast.nsfw.user.service.UserService;

public class UserAction extends BaseAction {

	//注入服务层的工具类
	@Resource
	private UserService userService;
	//注入用户角色的工具类,用户在新增，编辑页面中显示用户所对应的所有角色信息
	@Resource
	private RoleService roleService;
	//将查询到的所有用户信息封装到集合对象中
//	private List<User> userList;
	//查询到的单个用户对象,用于保存用户，更新用户时值的自动注入
	private User user;
	//将用户对应的所有角色信息封装到一个角色对象数组中
	private String[] userRoleIds;
	private String strName;      //存储用户临时的搜索条件

	
	//获取用户上传头像的相关参数配置
	private File headImg;
	private String headImgContentType;
	private String headImgFileName;
	
	//创建变量接收用户上传的excel文件的相关参数
	private File userExcel;
	private String userExcelContentType;
	private String userExcelFileName;
	
	
	//用户CRUD跳转的页面，
/*	addUI.jsp --> add --> listUI.jsp
	edit.jsp --> edit --> listUI.jsp
	delete --> listUI.jsp
*/
	//列表显示页面
	public String listUI()  throws ActionException{
        try{
        	QueryHelper queryHelper = new QueryHelper(User.class, "u");
        	if(user!=null){
        		if(StringUtils.isNotBlank(user.getName())){
        			//对搜索条件进行指定格式的编码
        			user.setName(URLDecoder.decode(user.getName(), "utf-8"));
        			queryHelper.addCondition(" u.name like ?", "%"+user.getName()+"%");
        			
        		}
        	}
        	pageResult=userService.getPageResult(queryHelper, getPageNo(), getPageSize());
        }catch(Exception e){
        	e.printStackTrace();
        }
		return "listUI";
	}
	
	//跳转到新增页面
	public String addUI(){
		//显示所有的角色信息
		ActionContext.getContext().getContextMap().put("roleList", roleService.findObjects());
		return "addUI";
	}
	
	//处理新增
	public String add(){
		//处理保存用户时的头像问题
		//防止用户不填写任何内容，传递过来一个空值
		try {
			if(user!=null){
			    if(headImg!=null){
					//获取保存用户头像时的地址
					String filePath=ServletActionContext.getServletContext().getRealPath("upload/user");
					//重写用户的头像名：1.防止重名，2.防止出现特殊的字符
					String fileName=UUID.randomUUID().toString().replaceAll("-","")+headImgFileName.substring(headImgFileName.lastIndexOf("."));
					//使用工具类保存用户头像到upload目录下
					FileUtils.copyFile(headImg, new File(filePath,fileName));
					user.setHeadImg("user/"+fileName);
			    }
			}
//			userService.save(user);
			userService.saveUserAndRole(user,userRoleIds);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "list";
	}
	
	//跳转到编辑页面
	public String editUI(){
		//显示当前用户的所有角色信息
	    ActionContext.getContext().getContextMap().put("roleList", roleService.findObjects());
		//将用户的信息进行数据回显，由前段传送过来id来通过工具类查询用户的所有信息
		if(user!=null && user.getId()!=null){
			strName=user.getName();
			//将查询到的用户信息封装到成员变量中
			user=userService.findObjectById(user.getId());
		}
		return "editUI";
	}
	
	//处理编辑
	public String edit(){
		//为防止用户保存空值，先进行判断
		try {
			if(user!=null){
				//仅仅只是对用户头像是否为空进行判断
				if(headImg!=null){
					String filePath=ServletActionContext.getServletContext().getRealPath("upload/user");
					String fileName=UUID.randomUUID().toString().replaceAll("-", "")+headImgFileName.substring(headImgFileName.lastIndexOf("."));
					FileUtils.copyFile(headImg, new File(filePath,fileName));
					user.setHeadImg("user/"+fileName);
				}
//				userService.update(user);
				userService.updateUserAndRole(user,userRoleIds);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "list";
	}
	
	//删除操作(单个用户)
	public String delete(){
		if(user!=null && user.getId()!=null){
			strName=user.getName();
			userService.delete(user.getId());
		}
		return "list";
	}
	
	//批量删除多个用户
	public String deleteSelected(){
		if(selectedRow!=null){
			strName=user.getName();
			for(String id : selectedRow){
				userService.delete(id);
			}
		}
		return "list";
	}
    
	//导出用户列表
	public void exportExcel()  throws ServiceException{
		try {
			//获取输出对象
			HttpServletResponse response = ServletActionContext.getResponse();
			//设置下载excel文件的相关参数设置
			//下载的文件类型
			response.setContentType("application/x-excel");
			//下载的文件名（对中文名称进行的编码设置，以便下载的时候能够显示正确的中文名称）
			response.setHeader("Content-Disposition", "attachment;filename="+new String("用户列表.xls".getBytes(),"ISO-8859-1"));
			//获取输出流对象，提交给浏览器下载
			ServletOutputStream output = response.getOutputStream();
			userService.exportExcel(userService.findObjects(), output);
			if(output!=null){
				output.close();
			}
		}  catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//读取excel文件
	public String importExcel(){
		if(userExcel!=null){
			//判断用户上传的文件是否是excel
			if(userExcelFileName.matches("^.+\\.(?i)((xls)|(xlsx))$")){
				userService.importExcel(userExcel, userExcelFileName);
			}
		}
		//导入成功后跳转到用户列表页面
		return "list";
	}
    
	//对用户账号的唯一性进行校验(ajax)
	public void verifyAccount(){
		try {
			if(user!=null && StringUtils.isNotBlank(user.getAccount())){
				String result="true";
				//根据账号去DB中查找是否已经有用户存在
				List<User> list=userService.findUserByAccountAndId(user.getId(),user.getAccount());
				//如果在dao中没有查询到相关用户的信息那么会返回一个Null值 list.size()>0  可以省去不写
				if(list!=null && list.size()>0){
					//说明数据库中存在同名的用户
					result="false";
				}
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/html");
				ServletOutputStream output = response.getOutputStream();
				output.write(result.getBytes());
				output.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	//提供get/set方法供struts自动封装值与取出相关的数据
/*	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}*/

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public File getHeadImg() {
		return headImg;
	}

	public void setHeadImg(File headImg) {
		this.headImg = headImg;
	}

	public String getHeadImgContentType() {
		return headImgContentType;
	}

	public void setHeadImgContentType(String headImgContentType) {
		this.headImgContentType = headImgContentType;
	}

	public String getHeadImgFileName() {
		return headImgFileName;
	}

	public void setHeadImgFileName(String headImgFileName) {
		this.headImgFileName = headImgFileName;
	}

	public File getUserExcel() {
		return userExcel;
	}

	public void setUserExcel(File userExcel) {
		this.userExcel = userExcel;
	}

	public String getUserExcelContentType() {
		return userExcelContentType;
	}

	public void setUserExcelContentType(String userExcelContentType) {
		this.userExcelContentType = userExcelContentType;
	}

	public String getUserExcelFileName() {
		return userExcelFileName;
	}

	public void setUserExcelFileName(String userExcelFileName) {
		this.userExcelFileName = userExcelFileName;
	}

	public String[] getUserRoleIds() {
		return userRoleIds;
	}

	public void setUserRoleIds(String[] userRoleIds) {
		this.userRoleIds = userRoleIds;
	}

	public String getStrName() {
		return strName;
	}

	public void setStrName(String strName) {
		this.strName = strName;
	}
	
}
