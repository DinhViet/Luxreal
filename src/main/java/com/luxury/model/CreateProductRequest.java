package com.luxury.model;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class CreateProductRequest {

	@Size(max = 255, message = "token độ dài không hợp lệ")
	@NotBlank(message = "token không được null")
	private String token;
	
	@Size(max = 32, message = "productName độ dài không hợp lệ")
	@NotBlank(message = "productName không được null")
	private String productName;
	
	private BigDecimal amount;
	
	@Size(max = 12, message = "category độ dài không hợp lệ")
	@NotBlank(message = "category không được null")
	private String category;
	
	@Size(max = 64, message = "location độ dài không hợp lệ")
	@NotBlank(message = "location không được null")
	private String location;
	
	@Size(max = 255, message = "description độ dài không hợp lệ!")
	@NotBlank(message = "description không được null")
	private String description;
	
	@Size(max = 6, message = "currency độ dài không hợp lệ!")
	@NotBlank(message = "currency không được null")
	private String currency;
	
	private List<Image> images;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}
	
}
