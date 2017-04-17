package cn.itcast.core.exception;

//配置全局的系统异常,继承总的异常类型
public class SysException extends Exception {

	//配置错误信息
	private String errorMsg;

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	
    //配置异常类型的不同参数的接收形式	
	public SysException() {
		super();
	}

	public SysException(String message, Throwable cause) {
		super(message, cause);
		this.errorMsg=message;
	}

	public SysException(String message) {
		super(message);
		this.errorMsg=message;
	}

	public SysException(Throwable cause) {
		super(cause);
	}
	
	
	
}
