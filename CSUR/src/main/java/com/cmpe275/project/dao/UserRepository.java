package com.cmpe275.project.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.cmpe275.project.model.User;
	
	public interface UserRepository extends CrudRepository<User, Long> {
	
		@Query(value = "SELECT * FROM user WHERE email = ?1", nativeQuery = true)
		public User findUserByEmail(@Param("email") String email);
		
		@Transactional
		@Modifying(clearAutomatically = true)	
		@Query(value = "DELETE FROM user WHERE ID > 0", nativeQuery = true)
		public void clearUserTable();
		
	}

