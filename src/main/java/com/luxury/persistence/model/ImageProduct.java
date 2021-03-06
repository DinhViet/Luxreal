package com.luxury.persistence.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.luxury.framework.persistence.model.GeneratedIdEntry;

@Entity
@Table(name = "image_product")
@AttributeOverride(name = "id", column = @Column(name = "image_product_id") )
public class ImageProduct extends GeneratedIdEntry{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "url_image", length = 255)
	private String urlImage;
	
	@Column(name = "url_image_sd", length = 255)
	private String urlImageSD;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "produc_id", nullable = false)
	private Product product;


	public String getUrlImage() {
		return urlImage;
	}


	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public String getUrlImageSD() {
		return urlImageSD;
	}


	public void setUrlImageSD(String urlImageSD) {
		this.urlImageSD = urlImageSD;
	}
	
}
