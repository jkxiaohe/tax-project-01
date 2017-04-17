package cn.itcast.core.action;

import cn.itcast.core.page.PageResult;

import com.opensymphony.xwork2.ActionSupport;

public abstract class BaseAction extends ActionSupport {

	//抽取所有action类公共的属性和方法
	//批量删除多个用户时，将用户选中的id值保存到数组对象中
	protected String[] selectedRow;
	//分页对象，各个不同的类可能都需要用到，所以存放到core中
	protected PageResult pageResult;    
	private int pageNo;    //页号（用户可以不指定）
	private int pageSize;    //页大小（用户可以不指定）
	public static int DEFAULT_PAGE_SIZE=2;   //默认页大小
	
	public String[] getSelectedRow() {
		return selectedRow;
	}
	public void setSelectedRow(String[] selectedRow) {
		this.selectedRow = selectedRow;
	}
	public PageResult getPageResult() {
		return pageResult;
	}
	public void setPageResult(PageResult pageResult) {
		this.pageResult = pageResult;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		//用户刚开始进入页面时没有传递pageSize的值，所以需要程序自动注入一个值对象
		if(pageSize==0)pageSize=DEFAULT_PAGE_SIZE;
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
