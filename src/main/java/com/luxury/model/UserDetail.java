package com.luxury.model;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.luxury.persistence.model.Product;

public class UserDetail {

	private String userName;
	
	private String dateOfBirth;
	
	private String urlIcon;
	
	private BigDecimal ratePoint;
	
	private ArrayList<Product> product;

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

	public ArrayList<Product> getProduct() {
		return product;
	}

	public void setProduct(ArrayList<Product> product) {
		this.product = product;
	}
}
