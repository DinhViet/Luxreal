package com.luxury.persistence.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.luxury.framework.persistence.model.GeneratedIdEntry;

@Entity
@Table(name = "transactions")
public class Transactions extends GeneratedIdEntry {

	/**
	 * 
	 */
	public static final String STATUS_STATRT = "INITITE";
	public static final String STATUS_FAIL = "FAIL";
	public static final String STATUS_SUCCESS = "SUCCESS";
	public static final String STATUS_TIME_OUT = "TIMEOUT";
	public static final String STATUS_REFUND = "REFUND";
	
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "request_id")
	private String requestId;
	
	@Column(name = "walletgw_request_id")
	private String walletGWRequestId;

	@Column(name = "merchant")
	private String merchant;
	
	@Column(name = "agent_name")
	private String agentName;

	@Column(name = "merchant_order_id")
	private String merchantOrderId;

	@Column(name = "provider_order_id")
	private String providerOrderId;
	
	@Column(name = "wallet_order_id")
	private String walletOrderId;

	@Column(name = "wallet_id")
	private String walletId;
	
	@Column(name = "respCode")
	private String respCode;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "amount", precision = 15, scale = 2)
	private BigDecimal amount = BigDecimal.ZERO;
	
	@Column(name = "service_id", length = 50)
	private String serviceId;
	
	@Column(name = "request_time")
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date requestTime;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "status")
	private String status;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
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

	public String getWalletId() {
		return walletId;
	}

	public void setWalletId(String walletId) {
		this.walletId = walletId;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getWalletOrderId() {
		return walletOrderId;
	}

	public void setWalletOrderId(String walletOrderId) {
		this.walletOrderId = walletOrderId;
	}

	public String getWalletGWRequestId() {
		return walletGWRequestId;
	}

	public void setWalletGWRequestId(String walletGWRequestId) {
		this.walletGWRequestId = walletGWRequestId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}
