package com.luxury.persistence.dao;

import com.luxury.model.LoginRequest;
import com.luxury.persistence.model.User;

public interface ILuxuryUserDao {

	 boolean createUser(User user);
	
	 boolean updateUser(User user);
	 
	 long checkUserName(String userName);
	 
	 long checkMail(String mail);
	
	 User login(LoginRequest request);
	 
	 User loginEmail(LoginRequest request);
	 
	 User getDetail(String token);
	 
	 User getDetailbyUserName(String userName);
	 
	 User getDetail(String token,String passWord);
	 
}
