package com.truemoney.walletgw.ecom.model;

public class WalletGWCheckTransactionResponse {

	private WalletGWStatus status;
	
	private WalletGWCheckTransactionBody data;

	public WalletGWStatus getStatus() {
		return status;
	}

	public void setStatus(WalletGWStatus status) {
		this.status = status;
	}

	public WalletGWCheckTransactionBody getData() {
		return data;
	}

	public void setData(WalletGWCheckTransactionBody data) {
		this.data = data;
	}
	
	
	
}
