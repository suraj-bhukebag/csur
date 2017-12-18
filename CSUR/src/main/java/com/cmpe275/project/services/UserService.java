package com.cmpe275.project.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cmpe275.project.dao.UserRepository;
import com.cmpe275.project.mapper.SignInResponse;
import com.cmpe275.project.model.User;

@Service
@Transactional
public class UserService {

	@Autowired
	UserRepository userRepositry;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public User createUser(User user) {
		User createdUser = userRepositry.save(user);
		return createdUser;
	}

	public SignInResponse userSignIn(User userRequest){ 
		SignInResponse signinResponse = new SignInResponse();

		User user = this.findUserByEmail(userRequest.getEmail());
		if (user == null) {
			signinResponse.setCode(500);
			signinResponse.setMsg("Invalid Username.");
		} else {
			if (passwordEncoder.matches(userRequest.getPassword(),
					user.getPassword())) {
				signinResponse.setLoggedIn(true);
				signinResponse.setCode(200);
				signinResponse.setMsg("Login Successful.");
			} else {
				signinResponse.setCode(500);
				signinResponse.setMsg("Invalid Password.");
			}
		}
		return signinResponse;
	}
	
	public User getUser(String userId) {
		User user = userRepositry.findOne(new Long(userId));
		return user;
	}
	
	
	public User findUserByEmail(String email) {
		return userRepositry.findUserByEmail(email);
	}
	
	public void clearUserTable() {
		userRepositry.clearUserTable();
	}

}
