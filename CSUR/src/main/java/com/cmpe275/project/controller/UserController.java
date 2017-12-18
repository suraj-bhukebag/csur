package com.cmpe275.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cmpe275.project.mapper.UserResponse;
import com.cmpe275.project.model.User;
import com.cmpe275.project.services.UserService;
import com.cmpe275.project.validators.Validator;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createUser(@RequestBody User user) {
		UserResponse userResponse = new UserResponse();
		boolean isValid = Validator.validateCreateUserRequest(user, userResponse);
		ResponseEntity res = null;
		HttpStatus httpStatus = null;

		if (isValid) {

			if (userService.findUserByEmail(user.getEmail()) != null) {
				userResponse.setMsg("User with entered email already exist. Please enter a different email.");
				httpStatus = HttpStatus.BAD_REQUEST;
			} else {

				User savedUser = userService.createUser(user);
				userResponse.setUser(savedUser);
				userResponse.setMsg("Successfull created a new User");
				httpStatus = HttpStatus.OK;
			}
		} else {
			httpStatus = HttpStatus.BAD_REQUEST;
		}

		res = new ResponseEntity(userResponse, httpStatus);

		return res;

	}

	@GetMapping(path = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getPlayer(@PathVariable(value = "userId") String userId) {

		UserResponse userResponse = new UserResponse();
		ResponseEntity res = null;
		HttpStatus httpStatus = null;
		User user = userService.getUser(userId);
		if (user != null) {
			userResponse.setUser(user);
			userResponse.setMsg("Successfully Retrieved User Details.");
			httpStatus = HttpStatus.OK;
		} else {
			userResponse.setMsg("User does not exist");
			httpStatus = HttpStatus.NOT_FOUND;
		}

		res = new ResponseEntity(userResponse, httpStatus);
		return res;
	}

}
