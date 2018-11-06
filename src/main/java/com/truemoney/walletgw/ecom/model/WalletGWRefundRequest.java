package com.truemoney.walletgw.ecom.model;

public class WalletGWRefundRequest {

	private String request_id;
	
	private String payment_request_id;
	
	private String payment_order_id;

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}

	public String getPayment_request_id() {
		return payment_request_id;
	}

	public void setPayment_request_id(String payment_request_id) {
		this.payment_request_id = payment_request_id;
	}

	public String getPayment_order_id() {
		return payment_order_id;
	}

	public void setPayment_order_id(String payment_order_id) {
		this.payment_order_id = payment_order_id;
	}
	
	
	
}
