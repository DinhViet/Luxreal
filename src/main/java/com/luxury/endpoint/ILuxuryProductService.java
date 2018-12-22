package com.luxury.endpoint;

import com.luxury.model.CreateProductRequest;
import com.luxury.model.DeleteProductRequest;
import com.luxury.model.ResultResponse;
import com.luxury.model.Status;
import com.luxury.model.UpdateStatusRequest;
import com.luxury.model.GetListProductResponse;
import com.luxury.model.GetProductRequest;

public interface ILuxuryProductService {

	ResultResponse createProduct(CreateProductRequest request) throws Exception;
	
	GetListProductResponse getListProduct(GetProductRequest request) throws Exception;
	
	ResultResponse DeleteProduct(DeleteProductRequest request)throws Exception;
	
	Status updateStatus(UpdateStatusRequest request)throws Exception;
	
	
}
