package com.luxury.endpoint;

import com.luxury.model.CreateUserRequest;
import com.luxury.model.CreateUserResponse;
import com.luxury.model.DetailUserResponse;
import com.luxury.model.LoginRequest;
import com.luxury.model.LoginResponse;
import com.luxury.model.UpdateUserRequest;
import com.luxury.model.UpdateUserResponse;

public interface ILuxuryUserService {

	CreateUserResponse createUser(CreateUserRequest request);
	
	CreateUserResponse updateUser(CreateUserRequest request);
	
	LoginResponse login(LoginRequest request);
	
	DetailUserResponse getDetail(LoginRequest request);
	
	UpdateUserResponse updateUser(UpdateUserRequest request);
	
	
}
