package com.keerthi.chatapp;

import java.util.List;

import com.keerthi.datalayer.DatabaseManager;
import com.opensymphony.xwork2.ActionSupport;
public class LoginAction extends ActionSupport{
	
	private String userName;
	private String password;
	static SignUpAction signupAction;
	static List<SignUpAction> getLoginUserResponse;
	
	

	public String execute() {
		boolean isValid=DatabaseManager.getInstance().validateUser(userName,password);
		if(isValid) {
		  setSignupAction(DatabaseManager.getInstance().getLoginUser(userName,password));
		  setLoginUserResponse(DatabaseManager.getInstance().getResponseUser(signupAction.getId()));
		  System.out.println(signupAction.getId()+"this is correct id ");
			System.out.println("login success");
            return SUCCESS;
		}
		else {
			System.out.println("login failed");
			return ERROR;
		}
		    
    }
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public static SignUpAction getSignupAction() {
		return signupAction;
	}

	public static void setSignupAction(SignUpAction signupAction) {
		LoginAction.signupAction = signupAction;
	}
	public static List<SignUpAction> getGetLoginUserResponse() {
		return getLoginUserResponse;
	}

	public static void setLoginUserResponse(List<SignUpAction> getLoginUserResponse) {
		LoginAction.getLoginUserResponse = getLoginUserResponse;
	}
}

