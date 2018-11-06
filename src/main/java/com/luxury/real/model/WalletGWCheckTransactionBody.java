package com.luxury.real.model;

import java.math.BigDecimal;

public class WalletGWCheckTransactionBody {

	private String status;
	
	private String wallet_order_id;
	
	private BigDecimal balance;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWallet_order_id() {
		return wallet_order_id;
	}

	public void setWallet_order_id(String wallet_order_id) {
		this.wallet_order_id = wallet_order_id;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	
	
}
