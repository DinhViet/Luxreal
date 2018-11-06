package com.luxury.real.model;

public class WalletGWLoginRequest {

	private String request_id;
	
	private String wallet_id;
	
	private String password;
	
	private String wallet_type;
	
	private String security_code;
	
	private String login_id;
	
	private String type;

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}

	public String getWallet_id() {
		return wallet_id;
	}

	public void setWallet_id(String wallet_id) {
		this.wallet_id = wallet_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getWallet_type() {
		return wallet_type;
	}

	public void setWallet_type(String wallet_type) {
		this.wallet_type = wallet_type;
	}

	public String getSecurity_code() {
		return security_code;
	}

	public void setSecurity_code(String security_code) {
		this.security_code = security_code;
	}

	public String getLogin_id() {
		return login_id;
	}

	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
