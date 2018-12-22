package com.luxury.persistence.dao;

import java.util.List;

import com.luxury.model.DeleteProductRequest;
import com.luxury.model.GetProductRequest;
import com.luxury.persistence.model.Product;

public interface ILuxuryProductDao {

	boolean createProduct(Product product);
	
	List<Product> getListProduct(GetProductRequest request);
	
	boolean deleteProduct(DeleteProductRequest request);
	
	Product getProductById(String productId);
	
	boolean updateProduct(Product product);
	
}
