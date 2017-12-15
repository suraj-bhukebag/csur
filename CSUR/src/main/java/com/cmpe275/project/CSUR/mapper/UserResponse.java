package com.cmpe275.project.csur.mapper;

import com.cmpe275.project.csur.model.User;

public class UserResponse {
	
	private String msg;
	
	
	private User user;


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
}
