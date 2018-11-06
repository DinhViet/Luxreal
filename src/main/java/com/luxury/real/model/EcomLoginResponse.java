package com.luxury.real.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class EcomLoginResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String respCode;
	
	private String description;
	
	private String loginId;
	
	private boolean otp;
	
	private String token;
	
	private int expire;
	
	private String fullName;
	
	private String walletId;
	
	private BigDecimal balance;

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isOtp() {
		return otp;
	}

	public void setOtp(boolean otp) {
		this.otp = otp;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getExpire() {
		return expire;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getWalletId() {
		return walletId;
	}

	public void setWalletId(String walletId) {
		this.walletId = walletId;
	}
	
}
