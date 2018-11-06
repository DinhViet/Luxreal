package com.truemoney.walletgw.ecom.model;

import java.math.BigDecimal;

public class WalletGWLoginBody {

	
	private String login_id;
	
	private String access_token;
	
	private int expired_in;
	
	private String phone_number;
	
	private String full_name;
	
	private BigDecimal balance;
	
	private boolean otp;

	public String getLogin_id() {
		return login_id;
	}

	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public int getExpired_in() {
		return expired_in;
	}

	public void setExpired_in(int expired_in) {
		this.expired_in = expired_in;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public boolean isOtp() {
		return otp;
	}

	public void setOtp(boolean otp) {
		this.otp = otp;
	}
	
}
