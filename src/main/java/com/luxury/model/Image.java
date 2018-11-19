package com.luxury.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class Image {

	@Size(max = 255, message = "image sd độ dài không hợp lệ!")
	@NotBlank(message = "image sd không được null")
	private String image_sd;
	
	@Size(max = 255, message = "image hd độ dài không hợp lệ!")
	@NotBlank(message = "image hd không được null")
	private String image_hd;

	public String getImage_sd() {
		return image_sd;
	}

	public void setImage_sd(String image_sd) {
		this.image_sd = image_sd;
	}

	public String getImage_hd() {
		return image_hd;
	}

	public void setImage_hd(String image_hd) {
		this.image_hd = image_hd;
	}
	
	
	
	
}
