package com.cmpe275.project.mapper;

import com.cmpe275.project.model.User;

public class SignInResponse extends GenericResponse{

	private boolean loggedIn;
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
}
