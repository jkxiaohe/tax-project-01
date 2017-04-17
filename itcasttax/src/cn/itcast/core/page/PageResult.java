package cn.itcast.core.page;

import java.util.ArrayList;
import java.util.List;

public class PageResult {

	//分页查询的工具
	//1.总记录数
	private long totalCount;
	//2.总页数
	private int totalPageCount;
	//3.当前页号
	private int pageNo;
	//4.页面大小
	private int pageSize;
	//5.数据列表
	private List items;
	
/*	分页对象的参数
	1.总页数      可由总记录数/每页大小   计算得到
	2.总记录数     用户传入
	3.当前页         用户传入
	4.每页显示的记录数          用户传入
	5.当前页的内容      用户传入或工具加入*/
	public PageResult(){}
	public PageResult(long totalCount,int pageNo,int pageSize,List items){
		//判断当前列表数据是否已经被其他类使用过了（使用过后list集合就会有实例了）
		this.items = items==null ? new ArrayList() : items;
		this.totalCount=totalCount;
		this.pageSize=pageSize;
		//pageNo有2种可能的值  ：1.当总记录数不为0时，=pageNo; 2.当总记录数=0时 ， pageNo = 0
		if(totalCount!=0){
			int tem=(int) ((totalCount%pageSize==0) ? (totalCount/pageSize) : (totalCount/pageSize+1));
			this.totalPageCount=tem;
			this.pageNo=pageNo;
		}else{
			this.pageNo=0;
		}
	}
	
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPageCount() {
		return totalPageCount;
	}
	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List getItems() {
		return items;
	}
	public void setItems(List items) {
		this.items = items;
	}
	
}
