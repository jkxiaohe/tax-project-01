package cn.itcast.nsfw.complain.action;

import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import cn.itcast.core.action.BaseAction;
import cn.itcast.core.utils.QueryHelper;
import cn.itcast.nsfw.complain.entity.Complain;
import cn.itcast.nsfw.complain.entity.ComplainReply;
import cn.itcast.nsfw.complain.service.ComplainService;

import com.opensymphony.xwork2.ActionContext;

public class ComplainAction extends BaseAction {

	//工具类
	@Resource
	private ComplainService complainService;
	//投诉对象
	private Complain complain;
	//回复对象
	private ComplainReply reply;
	private String startTime;
	private String endTime;
	
	//列表页面
	public String listUI(){
		//加载信息状态集合
		ActionContext.getContext().getContextMap().put("complainStateMap", Complain.COMPLAIN_STATE_MAP);
		try{
			//分页显示所有信息，传入分页查询助手
			QueryHelper queryHelper = new QueryHelper(Complain.class, "c");
			//查询的4个条件，需要一一设置
			//1.查询的开始时间
			if(StringUtils.isNotBlank(startTime)){
				//使用日期工具类将时间转换为mysql中指定的显示格式
				startTime=URLDecoder.decode(startTime,"utf-8");
				queryHelper.addCondition("c.compTime >= ?",DateUtils.parseDate(startTime+":00", "yyyy-MM-dd HH:mm:ss"));
			}
		    //2.查询的结束时间
			if(StringUtils.isNotBlank(endTime)){
				endTime=URLDecoder.decode(endTime,"utf-8");
				queryHelper.addCondition("c.compTime <= ?", DateUtils.parseDate(endTime+":59", "yyyy-MM-dd HH:mm:ss"));
			}
			if(complain!=null){
				//3.查询的标题
				if(StringUtils.isNotBlank(complain.getCompTitle())){
					complain.setCompTitle(URLDecoder.decode(complain.getCompTitle(),"utf-8"));
					queryHelper.addCondition("c.compTitle like ?", "%"+complain.getCompTitle()+"%");
					
					//4查询的状态（在有标题的情况下进行加入状态条件查询，否则在非查询的状态也会按照投诉的状态去数据库中查询数据）
					if(StringUtils.isNotBlank(complain.getState())){
						queryHelper.addCondition("c.state=?", complain.getState());
					}
					
				}

			}
			//排序方式
			queryHelper.addOrderByProperty("c.state", queryHelper.ORDER_BY_ASC);
			queryHelper.addOrderByProperty("c.compTime", queryHelper.ORDER_BY_DESC);
			
			pageResult=complainService.getPageResult(queryHelper, getPageNo(), getPageSize());
		}catch(Exception e){
			e.printStackTrace();
		}
		return "listUI";
	}
	
	//跳转到受理页面
	public String dealUI(){
		ActionContext.getContext().getContextMap().put("complainStateMap", Complain.COMPLAIN_STATE_MAP);
		if(complain!=null){
			complain=complainService.findObjectById(complain.getCompId());
		}
		return "dealUI";
	}
	
	//处理受理
	public String deal(){
		//根据页面传过来的信息id获取投诉信息对象
		Complain tem=complainService.findObjectById(complain.getCompId());
		//1.更新投诉信息的状态
		if(complain!=null){
			if(!Complain.COMPLAIN_STATE_DONE.equals(tem.getState())){
				//当前投诉信息的状态为待受理
				tem.setState(Complain.COMPLAIN_STATE_DONE);
			}
		}
		//2.保存回复信息
		if(reply!=null){
			//在多的一方保存外键
			reply.setComplain(tem);
			//保存回复的其他信息
			reply.setReplyTime(new Timestamp(new Date().getTime()));
			//在投诉中保存每次的回复信息
			tem.getComplainReplies().add(reply);
		}
		complainService.update(tem);
		return "list";
	}

	
	public Complain getComplain() {
		return complain;
	}

	public void setComplain(Complain complain) {
		this.complain = complain;
	}

	public ComplainReply getReply() {
		return reply;
	}

	public void setReply(ComplainReply reply) {
		this.reply = reply;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}
