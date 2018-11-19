package com.luxury.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class CreateUserRequest {

	@Size(max = 32, message = "userName độ dài không hợp lệ")
	@NotBlank(message = "userName không được null")
	private String userName;
	
	@Size(max = 32, message = "userName độ dài không hợp lệ")
	@NotBlank(message = "name không được null")
	private String name;
	
	@Size(max = 16, message = "passWord độ dài không hợp lệ")
	@NotBlank(message = "passWord không được null")
	private String passWord;
	
	@Size(max = 255, message = "token độ dài không hợp lệ")
	private String token;
	
	@Size(max = 32, message = "dateOfBirth độ dài không hợp lệ")
	private String dateOfBirth;
	
	@Size(max = 255, message = "urlIcon độ dài không hợp lệ")
	private String urlIcon;

	@Size(max = 32, message = "mail độ dài không hợp lệ")
	@NotBlank(message = "mail không được null")
	private String mail;
	
	@Size(max = 255, message = "website độ dài không hợp lệ")
	private String website;
	
	@Size(max = 255, message = "description độ dài không hợp lệ")
	private String description;
	
	@Size(max = 15, message = "phoneNumber độ dài không hợp lệ")
	private String phoneNumber;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
