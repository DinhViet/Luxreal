package com.truemoney.walletgw.ecom.model;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class EcomLoginRequest {
	
	@Valid
	private WalletInfomation customerInfo;
	
	@Size(max = 20, message = "requestId độ dài không hợp lệ")
	@NotBlank(message = "requestId không được null")
	private String requestId;
	
	private String loginId;
	
	@Size(max = 10, message = "type độ dài không hợp lệ")
	@NotBlank(message = "type không được null")
	private String type;
	
	@Size(max = 10, message = "serviceId độ dài không hợp lệ")
	@NotBlank(message = "serviceId không được null")
	private String serviceId;
	
	@Size(max = 30, message = "accesskey độ dài không hợp lệ")
	@NotBlank(message = "accesskey không được null")
	private String accesskey;
	
	@NotBlank (message = "signature không được null")
	private String signature;

	public WalletInfomation getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(WalletInfomation customerInfo) {
		this.customerInfo = customerInfo;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
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
	
	
	
	
}
