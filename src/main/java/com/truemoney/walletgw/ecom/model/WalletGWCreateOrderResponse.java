package com.truemoney.walletgw.ecom.model;

public class WalletGWCreateOrderResponse {

	private WalletGWStatus status;
	
	private WalletGWCreateOrderBody data;
	
	private String requestId;

	public WalletGWStatus getStatus() {
		return status;
	}

	public void setStatus(WalletGWStatus status) {
		this.status = status;
	}

	public WalletGWCreateOrderBody getData() {
		return data;
	}

	public void setData(WalletGWCreateOrderBody data) {
		this.data = data;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	
	
	
}
