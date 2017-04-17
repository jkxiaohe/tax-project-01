package test.action;

import javax.annotation.Resource;

import test.service.TestService;

import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport {

	@Resource(name="testService")
	TestService testService;
	
	@Override
	public String execute() throws Exception {
		testService.say();
		return SUCCESS;
	}
	
}
