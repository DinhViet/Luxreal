package com.luxury.real.model;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionHistoryForm {

	private long id;
	
	private String requestId;
	
	private String walletGWRequestId;

	private String merchant;
	
	private String agentName;

	private String merchantOrderId;

	private String providerOrderId;
	
	private String walletOrderId;

	private String walletId;
	
	private String respCode;
	
	private String description;
	
	private BigDecimal amount = BigDecimal.ZERO;
	
	private String serviceId;
	
	private Date requestTime;
	
	private String type;
	
	private String status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getWalletGWRequestId() {
		return walletGWRequestId;
	}

	public void setWalletGWRequestId(String walletGWRequestId) {
		this.walletGWRequestId = walletGWRequestId;
	}

	public String getMerchant() {
		return merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getMerchantOrderId() {
		return merchantOrderId;
	}

	public void setMerchantOrderId(String merchantOrderId) {
		this.merchantOrderId = merchantOrderId;
	}

	public String getProviderOrderId() {
		return providerOrderId;
	}

	public void setProviderOrderId(String providerOrderId) {
		this.providerOrderId = providerOrderId;
	}

	public String getWalletOrderId() {
		return walletOrderId;
	}

	public void setWalletOrderId(String walletOrderId) {
		this.walletOrderId = walletOrderId;
	}

	public String getWalletId() {
		return walletId;
	}

	public void setWalletId(String walletId) {
		this.walletId = walletId;
	}

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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public Date getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
