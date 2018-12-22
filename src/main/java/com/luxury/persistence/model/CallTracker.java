package com.luxury.persistence.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.luxury.framework.persistence.model.GeneratedIdEntry;

@Entity
@Table(name = "calltracker")
@AttributeOverride(name = "id", column = @Column(name = "calltracker_id") )
public class CallTracker extends GeneratedIdEntry{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "token", length = 255)
	private String token;
	
	@Column(name = "phone_number", length = 15)
	private String phoneNumber;
	
	@Column(name = "number_call", length = 15)
	private Integer numberCall;

	@Column(name = "product_id", length = 32)
	private String productId;
	
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getNumberCall() {
		return numberCall;
	}

	public void setNumberCall(Integer numberCall) {
		this.numberCall = numberCall;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}


}
