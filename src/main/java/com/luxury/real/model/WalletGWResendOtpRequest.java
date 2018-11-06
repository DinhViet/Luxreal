package com.luxury.real.model;

public class WalletGWResendOtpRequest {

	private String wallet_order_id;
	
	private String verify_token;
	
	private String access_token;
	
	private String request_id;

	public String getVerify_token() {
		return verify_token;
	}

	public void setVerify_token(String verify_token) {
		this.verify_token = verify_token;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getWallet_order_id() {
		return wallet_order_id;
	}

	public void setWallet_order_id(String wallet_order_id) {
		this.wallet_order_id = wallet_order_id;
	}

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}
	
	
	
	
}
