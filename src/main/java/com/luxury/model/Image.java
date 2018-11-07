package com.luxury.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class Image {

	@Size(max = 255, message = "image độ dài không hợp lệ!")
	@NotBlank(message = "image không được null")
	private String image;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
}
