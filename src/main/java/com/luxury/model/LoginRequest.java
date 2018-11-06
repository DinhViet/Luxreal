package com.luxury.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class LoginRequest {
	
	private String token;

	@Size(max = 32, message = "userName độ dài không hợp lệ")
	@NotBlank(message = "userName không được null")
	private String userName;
	
	@Size(max = 16, message = "passWord độ dài không hợp lệ")
	@NotBlank(message = "passWord không được null")
	private String passWord;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
