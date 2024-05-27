package com.keerthi.chatapp;

import com.keerthi.datalayer.DatabaseManager;
import com.opensymphony.xwork2.ActionSupport;

public class SignUpAction extends ActionSupport{

	    private int id;
		private String name;
		private String username;
		private String email;
		private String password;
		private String repassword;
		
		public SignUpAction() {
	    }
		public SignUpAction(int id,String name,String username,String email,String repassword) {
			this.setId(id);
			this.name=name;
			this.username=username;
			this.email=email;
			this.repassword=repassword;
		}
		
	public String execute() {
		boolean isValidUser=DatabaseManager.getInstance().addUser(name,username,email,repassword);
		if(isValidUser) {
			System.out.println("signup-successfull");
	        return SUCCESS;
		}
		else {
			System.out.println("signup failed");
			return ERROR;
		}
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
