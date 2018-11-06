package com.truemoney.walletgw.ecom.model;

public class WalletGWCheckTransactionRequest {

	private String request_id;
	
	private String order_id;
	

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}

}
