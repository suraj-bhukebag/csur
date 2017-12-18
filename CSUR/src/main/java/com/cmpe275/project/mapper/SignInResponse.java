package com.cmpe275.project.mapper;

public class SignInResponse extends GenereicResponse{

	private boolean loggedIn;
	;
	
	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
}
