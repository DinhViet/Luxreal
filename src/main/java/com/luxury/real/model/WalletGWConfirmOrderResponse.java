package com.luxury.real.model;

public class WalletGWConfirmOrderResponse {

	private WalletGWStatus status;
	
	private WalletGWConfirmOrderBody data;
	
	private String requestId;

	public WalletGWStatus getStatus() {
		return status;
	}

	public void setStatus(WalletGWStatus status) {
		this.status = status;
	}

	public WalletGWConfirmOrderBody getData() {
		return data;
	}

	public void setData(WalletGWConfirmOrderBody data) {
		this.data = data;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	
	
}
