package com.truemoney.walletgw.ecom.model.paymentservice.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PSUpdateTransStatusRequest {

	private String token;
	private String signature;
	
	@JsonProperty("trans_ref")
	private String transRef;
	private String status;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getTransRef() {
		return transRef;
	}
	public void setTransRef(String transRef) {
		this.transRef = transRef;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
