package com.truemoney.walletgw.persistence.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.truemoney.framework.persistence.model.GeneratedIdEntry;

@Entity
@Table(name = "user")
@AttributeOverride(name = "id", column = @Column(name = "user_id") )
public class User extends GeneratedIdEntry{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "user_name", length = 32)
	private String userName;
	
	@Column(name = "name", length = 32)
	private String name;
	
	@Column(name = "pass_word", length = 255)
	private String passWord;
	
	@Column(name = "token", length = 255)
	private String token;
	
	@Column(name = "date_of_birth", length = 32)
	private String dateOfBirth;
	
	@Column(name = "url_icon", length = 500)
	private String urlIcon;
	
	@Column(name = "rate_point")
	private BigDecimal ratePoint;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="users")
	@Cascade(value = CascadeType.ALL)
	private Set<Product> product = new HashSet<Product>();

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

	public Set<Product> getProduct() {
		return product;
	}

	public void setProduct(Set<Product> product) {
		this.product = product;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
