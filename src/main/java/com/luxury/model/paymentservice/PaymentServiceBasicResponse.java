package com.luxury.model.paymentservice;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentServiceBasicResponse {
	
	public static String SUCCESS_RESPONSE_CODE = "00";

	@JsonProperty("response_code")
	private String responseCode;
	
	@JsonProperty("response_message")
	private String responseMessage;
	
	private Object data;
	
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
