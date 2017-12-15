package com.cmpe275.project.csur.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmpe275.project.csur.dao.UserRepository;
import com.cmpe275.project.csur.model.User;

@Service
@Transactional
public class UserService {

	@Autowired
	UserRepository userRepositry;

	public User createUser(User user) {
		User createdUser = userRepositry.save(user);
		return createdUser;
	}

	public User getUser(String userId) {
		User user = userRepositry.findOne(new Long(userId));
		return user;
	}
	
	
	public User findUserByEmail(String email) {
		return userRepositry.findUserByEmail(email);
	}

}
