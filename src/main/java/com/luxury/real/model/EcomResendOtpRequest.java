package com.luxury.real.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class EcomResendOtpRequest {

	@Size(max = 64, message = "orderId độ dài không hợp lệ")
	@NotBlank(message = "orderId không được null")
	private String orderId;
	
	@Size(max = 30, message = "accesskey độ dài không hợp lệ")
	@NotBlank(message = "accesskey không được null")
	private String accesskey;
	
	@NotBlank(message = "signature không được null")
	private String signature;
	
	@Size(max = 255, message = "token độ dài không hợp lệ")
	@NotBlank(message = "token không được null")
	private String token;
	
	
	@Size(max = 255, message = "securityToken độ dài không hợp lệ")
	@NotBlank(message = "securityToken không được null")
	private String securityToken;
	

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSecurityToken() {
		return securityToken;
	}

	public void setSecurityToken(String securityToken) {
		this.securityToken = securityToken;
	}
	
	
	
}
