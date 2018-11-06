package com.luxury.real.model;

import java.util.List;

public class MerchantProfileResponse {

	int pageSize;
	
	int totalRecord;
	
	int displaylength;
	
	String responseCode;
	
	String responseMessage;
	
	List<MerchantProfileForm> listMerchant;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getDisplaylength() {
		return displaylength;
	}

	public void setDisplaylength(int displaylength) {
		this.displaylength = displaylength;
	}

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

	public List<MerchantProfileForm> getListMerchant() {
		return listMerchant;
	}

	public void setListMerchant(List<MerchantProfileForm> listMerchant) {
		this.listMerchant = listMerchant;
	}
	
	
	
	
}
