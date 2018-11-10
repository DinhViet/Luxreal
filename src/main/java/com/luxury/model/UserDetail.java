package com.luxury.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UserDetail {

	private String userName;
	
	private String name;
	
	private String dateOfBirth;
	
	private String urlIcon;
	
	private BigDecimal ratePoint;
	
	private List<ProductOfUser> products;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getUrlIcon() {
		return urlIcon;
	}

	public void setUrlIcon(String urlIcon) {
		this.urlIcon = urlIcon;
	}

	public BigDecimal getRatePoint() {
		return ratePoint;
	}

	public void setRatePoint(BigDecimal ratePoint) {
		this.ratePoint = ratePoint;
	}

	public List<ProductOfUser> getProducts() {
		return products;
	}

	public void setProducts(List<ProductOfUser> products) {
		this.products = products;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



}
