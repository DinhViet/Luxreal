package com.truemoney.walletgw.ecom.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class WalletInfomation {

	@Size(min=10 , max = 11, message = "WalletId độ dài không hợp lệ")
	@NotBlank(message = "WalletId không được null")
	private String walletId;
	
	@Size(min=1 ,max = 15, message = "securityCode độ dài không hợp lệ")
	@NotBlank(message = "securityCode không được null")
	private String securityCode;
	
	@Size(max = 10, message = "otp độ dài không hợp lệ")
	private String otp;

	public String getWalletId() {
		return walletId;
	}

	public void setWalletId(String walletId) {
		this.walletId = walletId;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}
	
	
}
