package com.cmpe275.project.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cmpe275.project.model.User;
	
	public interface UserRepository extends CrudRepository<User, Long> {
	
		@Query(value = "SELECT * FROM user WHERE email = ?1", nativeQuery = true)
		public User findUserByEmail(@Param("email") String email);
		
	}

