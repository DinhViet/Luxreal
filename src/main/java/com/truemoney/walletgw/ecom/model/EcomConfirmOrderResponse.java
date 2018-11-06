package com.truemoney.walletgw.ecom.model;

import java.math.BigDecimal;

public class EcomConfirmOrderResponse {

	private String respCode;
	
	private String description;
	
	private String orderId;
	
	private BigDecimal balance;
	
	private int restOtp;

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

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public int getRestOtp() {
		return restOtp;
	}

	public void setRestOtp(int restOtp) {
		this.restOtp = restOtp;
	}
	
	
	
}
