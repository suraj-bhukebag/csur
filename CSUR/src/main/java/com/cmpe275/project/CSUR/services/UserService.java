package com.cmpe275.project.CSUR.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmpe275.project.CSUR.dao.UserRepository;
import com.cmpe275.project.CSUR.model.User;

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
