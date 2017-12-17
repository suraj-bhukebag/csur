package com.cmpe275.project.CSUR.validators;

import com.cmpe275.project.CSUR.mapper.UserResponse;
import com.cmpe275.project.CSUR.model.User;

public class Validator {

	public static boolean validateCreateUserRequest(User user,
			UserResponse userResponse) {
		
		boolean isValid = true;
		if (user == null) {
			userResponse.setMsg("Invalid request.");
			isValid = false;
		} else if (user.getFirstname() == null) {
			userResponse.setMsg("First Name cannot be empty ");
			isValid = false;
		} else if (user.getEmail().equals("")) {
			userResponse.setMsg("Email cannot be empty ");
			isValid = false;
		}
		return isValid;
		
	}
	
}
