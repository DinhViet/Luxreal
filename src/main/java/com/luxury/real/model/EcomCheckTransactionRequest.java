package com.luxury.real.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class EcomCheckTransactionRequest {

	@Size(max = 64, message = "orderId độ dài không hợp lệ")
	@NotBlank(message = "orderId không được null")
	private String orderId;
	
	@Size(max = 30, message = "accesskey độ dài không hợp lệ")
	@NotBlank(message = "accesskey không được null")
	private String accesskey;
	
	@NotBlank(message = "signature không được null")
	private String signature;

	public String getAccesskey() {
		return accesskey;
	}

	public void setAccesskey(String accesskey) {
		this.accesskey = accesskey;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	
	
}