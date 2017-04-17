package cn.itcast.core.constant;

import java.util.HashMap;
import java.util.Map;

public class Constant {

	//在系统中保存用户的表示符
	public static String USER="SYS_USER";
	
	//使用静态变量来保存系统的权限集合（方便实现在DB中的存储形式）
	public static String PRIVILEGE_XZGL="xzgl";
	public static String PRIVILEGE_NSFW="nsfw";
	public static String PRIVILEGE_HQFW="hqfw";
	public static String PRIVILEGE_ZXXX="zxxx";
	public static String PRIVILEGE_SPACE="spaces";
	
	//使用map集合保存静态变量对应的实际参数值（方便给用户显示具体的内容）
	public static Map<String,String> PRIVILEGE_MAP;
	static{
		PRIVILEGE_MAP=new HashMap<String,String>();
		PRIVILEGE_MAP.put(PRIVILEGE_XZGL, "行政管理");
		PRIVILEGE_MAP.put(PRIVILEGE_NSFW, "纳税服务");
		PRIVILEGE_MAP.put(PRIVILEGE_HQFW, "后勤管理");
		PRIVILEGE_MAP.put(PRIVILEGE_ZXXX, "在线学习");
		PRIVILEGE_MAP.put(PRIVILEGE_SPACE, "我的空间");
		
	}
	
}
