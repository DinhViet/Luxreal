package com.truemoney.walletgw.ecom.endpoint;

import com.luxury.model.CreateUserRequest;
import com.luxury.model.CreateUserResponse;
import com.luxury.model.DetailUserResponse;
import com.luxury.model.LoginRequest;
import com.luxury.model.LoginResponse;

public interface ILuxuryUserService {

	CreateUserResponse createUser(CreateUserRequest request);
	
	CreateUserResponse updateUser(CreateUserRequest request);
	
	String generateString();
	
	LoginResponse login(LoginRequest request);
	
	DetailUserResponse getDetail(LoginRequest request);
	
}
