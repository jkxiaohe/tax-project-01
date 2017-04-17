package cn.itcast.core.exception;

public class ActionException extends SysException {

	public ActionException(){
		super("表现层出现异常");
	}
	public ActionException(String message){
		super(message);
	}
}
