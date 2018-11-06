package com.truemoney.walletgw.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.truemoney.framework.persistence.model.GeneratedIdEntry;

@Entity
@Table(name = "merchant_profile")
public class MerchantProfile extends GeneratedIdEntry{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "merchant_code")
	private String merchantCode;

	@Column(name = "access_key")
	private String accesskey;
	
	@Column(name = "secret_key")
	private String secretkey ;
	
	@Column(name = "status")
	private String status ;
	
	@Column(name = "description")
	private String description;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getAccesskey() {
		return accesskey;
	}

	public void setAccesskey(String accesskey) {
		this.accesskey = accesskey;
	}

	public String getSecretkey() {
		return secretkey;
	}

	public void setSecretkey(String secretkey) {
		this.secretkey = secretkey;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	
	
}
