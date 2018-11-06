package com.truemoney.walletgw.ecom.model;

public class WalletGWLoginResponse {

	private WalletGWStatus status;
	
	private WalletGWLoginBody data;
	
	private String requestId;

	public WalletGWStatus getStatus() {
		return status;
	}

	public void setStatus(WalletGWStatus status) {
		this.status = status;
	}

	public WalletGWLoginBody getData() {
		return data;
	}

	public void setData(WalletGWLoginBody data) {
		this.data = data;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	
	
	
}
