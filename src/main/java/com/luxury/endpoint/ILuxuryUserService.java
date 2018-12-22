package com.luxury.endpoint;

import com.luxury.model.CreateUserRequest;
import com.luxury.model.CreateUserResponse;
import com.luxury.model.DetailUserResponse;
import com.luxury.model.LoginRequest;
import com.luxury.model.LoginResponse;
import com.luxury.model.ResetPassWordRequest;
import com.luxury.model.ResetPassWordResponse;
import com.luxury.model.UpdatePassWordRequest;
import com.luxury.model.UpdateUserRequest;
import com.luxury.model.UpdateUserResponse;

public interface ILuxuryUserService {

	CreateUserResponse createUser(CreateUserRequest request);
	
	CreateUserResponse updateUser(CreateUserRequest request);
	
	LoginResponse login(LoginRequest request);
	
	DetailUserResponse getDetail(LoginRequest request);
	
	DetailUserResponse getOtherUser(LoginRequest request);
	
	UpdateUserResponse updateUser(UpdateUserRequest request);
	
	UpdateUserResponse updatePassWord(UpdatePassWordRequest request);
	
	ResetPassWordResponse resetPassWord(ResetPassWordRequest request) ;
	
	
}
