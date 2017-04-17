package cn.itcast.nsfw.info.action;

import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import cn.itcast.core.action.BaseAction;
import cn.itcast.core.utils.QueryHelper;
import cn.itcast.nsfw.info.entity.Info;
import cn.itcast.nsfw.info.service.InfoService;

import com.opensymphony.xwork2.ActionContext;

public class InfoAction extends BaseAction {

	@Resource
	private InfoService infoService;  //注入信息的工具类对象
	private Info info;
	private String[] privilegeIds;
	//定义变量保存用户的查询条件
	private String strTitle;
	
	//列表显示页面
	public String listUI(){
		try{
			//创建工具类对象
			QueryHelper queryHelper=new QueryHelper(Info.class,"i");
			if(info!=null){
				if(StringUtils.isNotBlank(info.getTitle())){
					//对于jsp页面中的中文字符要进行指定格式的编码(jsp页面已经对中文进行了编码，故在服务端要进行解码)
					info.setTitle(URLDecoder.decode(info.getTitle(), "utf-8"));
					queryHelper.addCondition("i.title like ?", "%"+info.getTitle()+"%");
				}
			}
			//默认情况下根据创建时间来进行将序排序
			queryHelper.addOrderByProperty("i.createTime", QueryHelper.ORDER_BY_DESC);
			pageResult=infoService.getPageResult(queryHelper, getPageNo(), getPageSize());
			ActionContext.getContext().getContextMap().put("infoTypeMap", info.INFO_TYPE_MAP);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "listUI";
	}
	
/*	public String listUI() throws Exception{
		try {
			//创建查询语句
			String hql="from Info i ";
			List<Object> parameters=new ArrayList<Object>();
			if(info!=null){
				//根据标题去查询
				if(StringUtils.isNotBlank(info.getTitle())){
					hql+=" where i.title like ?";
					parameters.add(new String("%"+info.getTitle()+"%"));
				}
			}
			//加载分类集合（用于在jsp页面中显示具体的数值）
			ActionContext.getContext().getContextMap().put("infoTypeMap", info.INFO_TYPE_MAP);
			infoList=infoService.findObjects(hql, parameters);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "listUI";
	}*/
	
	//跳转到新增页面
	public String addUI(){
		ActionContext.getContext().getContextMap().put("infoTypeMap", info.INFO_TYPE_MAP);
		//新建一个info对象时，创建时间应该由系统来设置，而不能交给用户去指定创建的时间(所以要在用户填写新增信息之前就将createTime属性设置好)
		info=new Info();
		info.setCreateTime(new Timestamp(new Date().getTime()));
		return "addUI";
	}
	
	//处理新增
	public String add(){
		//当信息不为空时
		try {
			if(info!=null){
				System.out.println(info);
				infoService.save(info);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}
	
	//跳转到编辑页面
	public String editUI(){
		ActionContext.getContext().getContextMap().put("infoTypeMap", info.INFO_TYPE_MAP);
		if(info!=null && info.getInfoId()!=null){
			strTitle=info.getTitle();
			info=infoService.findObjectById(info.getInfoId());
		}
		return "editUI";
	}
	
	//处理编辑
	public String edit(){
		try {
			if(info!=null){
				infoService.update(info);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}
	
	//删除单个
	public String delete(){
		if(info!=null && info.getInfoId()!=null){
			strTitle=info.getTitle();
			infoService.delete(info.getInfoId());
		}
		return "list";
	}
	
	//批量删除
	public String deleteAll(){
		if(selectedRow!=null){
			strTitle=info.getTitle();
			for(String id : selectedRow){
				infoService.delete(id);
			}
		}
		return "list";
	}
	
	//异步信息的管理（发布/停用）  ajax的应用
	public void publicInfo(){
		try {
			if(info!=null){
			   Info tem = infoService.findObjectById(info.getInfoId());
			   tem.setState(info.getState());
			   infoService.update(tem);
			   
			   //以流的方式输出
			   HttpServletResponse response = ServletActionContext.getResponse();
			   response.setContentType("text/html");
			   ServletOutputStream output = response.getOutputStream();
			   output.write("更新状态成功".getBytes("utf-8"));
			   output.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public String[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(String[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}

	public String getStrTitle() {
		return strTitle;
	}

	public void setStrTitle(String strTitle) {
		this.strTitle = strTitle;
	}
	
	
}
