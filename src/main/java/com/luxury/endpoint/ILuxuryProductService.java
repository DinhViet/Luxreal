package com.luxury.endpoint;

import com.luxury.model.CreateProductRequest;
import com.luxury.model.CreateProductResponse;
import com.luxury.model.GetListProductResponse;
import com.luxury.model.GetProductRequest;

public interface ILuxuryProductService {

	CreateProductResponse createProduct(CreateProductRequest request) throws Exception;
	
	GetListProductResponse getListProduct(GetProductRequest request) throws Exception;
	
	
	
	
}
