package cn.itcast.core.utils;

import java.util.ArrayList;
import java.util.List;

public class QueryHelper {

	//搜索查询工具类对象
	//构造from子句
	private String fromClause="";
	//构造where子句
	private String whereClause="";
	//构造orderby子句
	private String orderByClause="";
	
	//where子句中的所有参数值
	private List<Object> parameters;
	
	public static String ORDER_BY_ASC="ASC";   
	public static String ORDER_BY_DESC="DESC";
	
	//通过构造函数构建from 子句
	//1.查询的类对象，2.别名
	public QueryHelper(Class clazz,String alias){
		fromClause="from "+clazz.getSimpleName()+" "+alias;
	}
	
	//组装where子句
	public QueryHelper addCondition(String condition,Object... params){
		//判断当前条件是否是第一个
		if(whereClause.length()>0){
			//已经添加过了条件
			whereClause+=" and "+condition;
		}else{
			//还没有添加过条件
			whereClause=" where "+condition;
		}
		if(parameters==null){
			parameters=new ArrayList<Object>();
		}
		if(params!=null){
			for(Object obj : params){
				parameters.add(obj);
			}
		}
		return this;
	}
	
	//组装orderBy子句
	//1.按property字段排序，2.order指定升序还是将序
	public QueryHelper addOrderByProperty(String property,String order){
		//判断order参数是否已经存在
		if(orderByClause.length()>0){
			orderByClause+=","+property+" "+order;
		}else{
			orderByClause=" order by "+property+" "+order;
		}
		return this;
	}
	
	//返回最后查询的hql语句
	public String getListQueryHql(){
		return fromClause+whereClause+orderByClause;
	}
	
	//返回查询总记录数的hql
	public String getCountHql(){
		return "select count(*) "+fromClause+whereClause;
	}
	
	//获取查询的参数值
	public List<Object> getParameters(){
		return parameters;
	}
	
}
