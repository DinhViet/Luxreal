package com.truemoney.walletgw.contract.base;

import com.truemoney.walletgw.common.ErrorMessages;

public class MainResponse {
	
	private String respCode = ErrorMessages.SUCCESS.code;
	private String description = ErrorMessages.SUCCESS.message;
	private Object data;
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}