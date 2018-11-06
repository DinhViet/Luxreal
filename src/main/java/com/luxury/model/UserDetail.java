package com.luxury.model;

import java.math.BigDecimal;
import java.util.List;

import com.luxury.persistence.model.Product;

public class UserDetail {

	private String userName;
	
	private String passWord;
	
	private String token;
	
	private String dateOfBirth;
	
	private String urlIcon;
	
	private BigDecimal ratePoint;
	
	private List<Product> product;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}
	
}
