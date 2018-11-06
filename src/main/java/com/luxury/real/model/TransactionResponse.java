package com.luxury.real.model;

import java.util.List;

public class TransactionResponse {

	int pageSize;
	
	int totalRecord;
	
	int displaylength;
	
	String responseCode;
	
	String responseMessage;
	
	List<TransactionHistoryForm> listTransaction;

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

	public List<TransactionHistoryForm> getListTransaction() {
		return listTransaction;
	}

	public void setListTransaction(List<TransactionHistoryForm> listTransaction) {
		this.listTransaction = listTransaction;
	}
	
	
	
	
}
