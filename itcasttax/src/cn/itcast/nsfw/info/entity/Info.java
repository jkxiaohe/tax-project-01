package cn.itcast.nsfw.info.entity;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class Info {

	private String infoId;  //信息的id
	private String type;    //信息分类
	private String source;  //来源
	private String title;  //信息标题
	private String content;  //信息内容
	private String memo;  //备注
	private String creator;  //创建人
	private Timestamp createTime;  //申请时间
	private String state;   //信息状态
	
	//信息状态的两种不同的值
	public static String INFO_STATE_PUBLIC="1";
	public static String INFO_STATE_STOP="0";
	
	//信息的分类
	public static String INFO_TYPE_TZGG="tzgg";
	public static String INFO_TYPE_ZCSD="zcsd";
	public static String INFO_TYPE_NSZD="nszd";
	
	public static Map<String,String> INFO_TYPE_MAP;
	static{
		INFO_TYPE_MAP=new HashMap<String, String>();
		INFO_TYPE_MAP.put(INFO_TYPE_TZGG, "通知公告");
		INFO_TYPE_MAP.put(INFO_TYPE_ZCSD, "政策速递");
		INFO_TYPE_MAP.put(INFO_TYPE_NSZD, "纳税指导");
	}
	public String getInfoId() {
		return infoId;
	}
	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Info(String infoId, String type, String source, String title,
			String content, String memo, String creator, Timestamp createTime,
			String state) {
		super();
		this.infoId = infoId;
		this.type = type;
		this.source = source;
		this.title = title;
		this.content = content;
		this.memo = memo;
		this.creator = creator;
		this.createTime = createTime;
		this.state = state;
	}
	public Info() {}
	@Override
	public String toString() {
		return "Info [infoId=" + infoId + ", type=" + type + ", source="
				+ source + ", title=" + title + ", content=" + content
				+ ", memo=" + memo + ", creator=" + creator + ", createTime="
				+ createTime + ", state=" + state + "]";
	}
	
	
}
