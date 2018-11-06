package com.truemoney.walletgw.ecom.model;

import java.math.BigDecimal;

public class WalletGWConfirmOrderBody {

	private BigDecimal balance;
	
	private int attempt_otp_left;

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public int getAttempt_otp_left() {
		return attempt_otp_left;
	}

	public void setAttempt_otp_left(int attempt_otp_left) {
		this.attempt_otp_left = attempt_otp_left;
	}
	
}
