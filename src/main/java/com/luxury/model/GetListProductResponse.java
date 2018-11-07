package com.luxury.model;

import java.util.List;

public class GetListProductResponse {

	private Status status;
	
	private List<ProductJson> products;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<ProductJson> getProducts() {
		return products;
	}

	public void setProducts(List<ProductJson> products) {
		this.products = products;
	}
	
}
