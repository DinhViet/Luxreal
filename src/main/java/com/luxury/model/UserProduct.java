package com.luxury.model;

import java.math.BigDecimal;

public class UserProduct {

	private BigDecimal ratePoint;
	
	private String userName;
	
	private String urlIconUser;
	
	private String name;

	public BigDecimal getRatePoint() {
		return ratePoint;
	}

	public void setRatePoint(BigDecimal ratePoint) {
		this.ratePoint = ratePoint;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUrlIconUser() {
		return urlIconUser;
	}

	public void setUrlIconUser(String urlIconUser) {
		this.urlIconUser = urlIconUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
