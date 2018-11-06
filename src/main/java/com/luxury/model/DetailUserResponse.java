package com.luxury.model;

public class DetailUserResponse {

	private Status status;
	
	private UserDetail user;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public UserDetail getUser() {
		return user;
	}

	public void setUser(UserDetail user) {
		this.user = user;
	}
	
}
