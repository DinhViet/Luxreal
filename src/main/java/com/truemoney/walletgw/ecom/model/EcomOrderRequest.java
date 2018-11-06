package com.truemoney.walletgw.ecom.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class EcomOrderRequest {

	@Size(max = 20, message = "requestId độ dài không hợp lệ")
	@NotBlank(message = "requestId không được null")
	private String requestId;
	
	@Size(max = 255, message = "token độ dài không hợp lệ")
	@NotBlank(message = "token không được null")
	private String token;
	
	private BigDecimal amount;
	
	@Size(max = 30, message = "merchantName độ dài không hợp lệ")
	@NotBlank(message = "merchantName không được null")
	private String merchantName;
	
	private String merchantCode;
	
	@Size(max = 20, message = "serviceId độ dài không hợp lệ")
	@NotBlank(message = "serviceId không được null")
	private String serviceId;
	
	@Size(max = 30, message = "accesskey độ dài không hợp lệ")
	@NotBlank(message = "accesskey không được null")
	private String accesskey;
	
	@NotBlank(message = "signature không được null")
	private String signature;
	
	@NotBlank(message = "merchantId không được null")
	private String merchantId;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
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

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	
}
