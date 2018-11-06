package com.luxury.real.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WalletGWCreateOrderBody {

	private String security_type;
	
	private String wallet_order_id;
	
	private String verify_token;
	
	private BigDecimal amount;
	
	private BigDecimal fee;
	
	private BigDecimal discount;
	
	private BigDecimal total_amount;
	
	private BigDecimal balance;
	
	@JsonProperty("customer_phone")
	private String customerPhone;

	public String getSecurity_type() {
		return security_type;
	}

	public void setSecurity_type(String security_type) {
		this.security_type = security_type;
	}

	public String getWallet_order_id() {
		return wallet_order_id;
	}

	public void setWallet_order_id(String wallet_order_id) {
		this.wallet_order_id = wallet_order_id;
	}

	public String getVerify_token() {
		return verify_token;
	}

	public void setVerify_token(String verify_token) {
		this.verify_token = verify_token;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(BigDecimal total_amount) {
		this.total_amount = total_amount;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	
}
