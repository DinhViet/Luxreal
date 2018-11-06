package com.truemoney.walletgw.ecom.model;

public class WalletGWConfirmOrderRequest {

	private String request_id;
	
	private String access_token;
	
	private String wallet_order_id;
	
	private String security_code;
	
	private String verify_token;

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
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

	public String getSecurity_code() {
		return security_code;
	}

	public void setSecurity_code(String security_code) {
		this.security_code = security_code;
	}

	public String getVerify_token() {
		return verify_token;
	}

	public void setVerify_token(String verify_token) {
		this.verify_token = verify_token;
	}
	
	
	
}
