package com.luxury.persistence.dao;

import com.luxury.model.LoginRequest;
import com.luxury.persistence.model.User;

public interface ILuxuryUserDao {

	 boolean createUser(User user);
	
	 boolean updateUser(User user);
	 
	 long checkUserName(String userName);
	
	 User login(LoginRequest request);
	 
	 User getDetail(LoginRequest request);
	
}
