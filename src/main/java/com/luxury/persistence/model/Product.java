package com.luxury.persistence.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.luxury.framework.persistence.model.GeneratedIdEntry;

@Entity
@Table(name = "product")
@AttributeOverride(name = "id", column = @Column(name = "produc_id") )
public class Product extends GeneratedIdEntry{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String STATUS_SELL = "SELL";
	public static final String STATUS_REJECT = "REJECT";
	
	@Column(name = "product_code", length = 32)
	private String productCode;
	
	@Column(name = "product_name", length = 32)
	private String productName;
	
	@Column(name = "amount")
	private BigDecimal amount = BigDecimal.ZERO;
	
	@Column(name = "category", length = 12)
	private String category;
	
	@Column(name = "location", length = 64)
	private String location;
	
	@Column(name = "description", length = 255)
	private String description;
	
	@Column(name = "status", length = 10)
	private String status;
	
	@Column(name = "currency", length = 10)
	private String currency;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User users;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="product")
	@Cascade(value = CascadeType.ALL)
	private Set<ImageProduct> imageProduct = new HashSet<ImageProduct>();
	
	
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	public Set<ImageProduct> getImageProduct() {
		return imageProduct;
	}

	public void setImageProduct(Set<ImageProduct> imageProduct) {
		this.imageProduct = imageProduct;
	}
	
	
	

}
