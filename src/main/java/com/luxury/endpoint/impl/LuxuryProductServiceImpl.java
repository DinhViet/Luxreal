package com.luxury.endpoint.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luxury.common.ErrorMessages;
import com.luxury.endpoint.ILuxuryProductService;
import com.luxury.model.CreateProductRequest;
import com.luxury.model.DeleteProductRequest;
import com.luxury.model.GetListProductResponse;
import com.luxury.model.GetProductRequest;
import com.luxury.model.Image;
import com.luxury.model.ProductJson;
import com.luxury.model.ResultResponse;
import com.luxury.model.Status;
import com.luxury.model.UpdateStatusRequest;
import com.luxury.model.UserProduct;
import com.luxury.persistence.dao.ILuxuryProductDao;
import com.luxury.persistence.dao.ILuxuryUserDao;
import com.luxury.persistence.model.ImageProduct;
import com.luxury.persistence.model.Product;
import com.luxury.persistence.model.User;

@Service
@Transactional
public class LuxuryProductServiceImpl implements ILuxuryProductService{

	@Autowired
	ILuxuryUserDao userDao;
	@Autowired
	ILuxuryProductDao productDao;
	
	@Override
	public ResultResponse createProduct(CreateProductRequest request) throws Exception {
		ResultResponse response = new ResultResponse();
		
		User user = userDao.getDetail(request.getToken());
		if(user!=null){
			Product product = new Product();
			product.setActive(true);
			product.setBrandName(request.getBrandName());
			product.setAmount(request.getAmount());
			product.setCategory(request.getCategory());
			product.setCreationDate(new Date());
			product.setTag(request.getTag());
			product.setLastUpdate(new Date());
			product.setCurrency(request.getCurrency());
			product.setDescription(request.getDescription());
			product.setProductName(request.getProductName());
			product.setUsers(user);
			product.setLocation(request.getLocation());
			product.setStatus(Product.STATUS_SELL);
			Set<ImageProduct> setImage = new HashSet<>();
			List<Image> images = request.getImages();
			for (Image image : images) {
				ImageProduct imProduct = new ImageProduct();
				imProduct.setCreationDate(new Date());
				imProduct.setActive(true);
				imProduct.setLastUpdate(new Date());
				imProduct.setProduct(product);
				imProduct.setUrlImage(image.getImage_hd());
				imProduct.setUrlImageSD(image.getImage_sd());
				setImage.add(imProduct);
			}
			product.setImageProduct(setImage);
		 	boolean save =  productDao.createProduct(product);
		 	if(save){
		 		response.setRespCode(ErrorMessages.SUCCESS.code);
		 		response.setDescription(ErrorMessages.SUCCESS.message);
		 	}else{
		 		response.setRespCode(ErrorMessages.UNKNOW_ERROR.code);
		 		response.setDescription(ErrorMessages.UNKNOW_ERROR.message);
		 	}
		}else{
			response.setRespCode(ErrorMessages.INVALID_PARAM.code);
			response.setDescription(ErrorMessages.INVALID_PARAM.message);
		}
		
		return response;
	}

	@Override
	public GetListProductResponse getListProduct(GetProductRequest request) throws Exception {
		GetListProductResponse response = new GetListProductResponse();
		Status status = new Status();
		status.setRespCode(ErrorMessages.SUCCESS.code);
		status.setDescription(ErrorMessages.SUCCESS.message);
		List<ProductJson> list = new ArrayList<>();
		if(request.getLimit()==0){
			request.setLimit(50);
		}
		List<Product> listProduct = productDao.getListProduct(request);
		for (Product product : listProduct) {
			List<Image> listImages = new ArrayList<>();
			Set<ImageProduct> setimage = product.getImageProduct();
			for (ImageProduct imageProduct : setimage) {
				Image image =  new Image();
				image.setImage_hd(imageProduct.getUrlImage());
				image.setImage_sd(imageProduct.getUrlImageSD());
				listImages.add(image);
			}
			ProductJson productJson = new ProductJson();
			productJson.setImages(listImages);
			BeanUtils.copyProperties(product, productJson);
			productJson.setProductName(product.getProductName());
			productJson.setProductId(""+product.getId());
			productJson.setStatus(product.getStatus());
			UserProduct user = new UserProduct();
			User users = product.getUsers();
			user.setName(users.getName());
			user.setRatePoint(users.getRatePoint());
			user.setUrlIconUser(users.getUrlIcon());
			user.setUserName(users.getUserName());
			productJson.setUser(user);
			list.add(productJson);
		}
		response.setProducts(list);
		response.setStatus(status);
		return response;
	}

	@Override
	public ResultResponse DeleteProduct(DeleteProductRequest request) throws Exception {
		ResultResponse response  = new ResultResponse();
		if(StringUtils.isEmpty(request.getProductId()) ||  StringUtils.isEmpty(request.getToken())){
			response.setRespCode(ErrorMessages.INVALID_PARAM.code);
			response.setDescription(ErrorMessages.INVALID_PARAM.message);
			return response;
		}
		User user = userDao.getDetail(request.getToken());
		if(user!=null){
			boolean delete = productDao.deleteProduct(request);
			if(delete){
				response.setRespCode(ErrorMessages.SUCCESS.code);
				response.setDescription(ErrorMessages.SUCCESS.message);
			}else{
				response.setRespCode(ErrorMessages.UNKNOW_ERROR.code);
				response.setDescription(ErrorMessages.UNKNOW_ERROR.message);
			}
		}else{
			response.setRespCode(ErrorMessages.INVALID_TOKEN.code);
			response.setDescription(ErrorMessages.INVALID_TOKEN.message);
			return response;
		}
		
		return response;
	}

	@Override
	public Status updateStatus(UpdateStatusRequest request) throws Exception {
		Status status = new Status();
		if(StringUtils.isEmpty(request.getProductId()) || StringUtils.isEmpty(request.getToken()) || StringUtils.isEmpty(request.getStatus())){
			status.setRespCode(ErrorMessages.INVALID_PARAM.code);
			status.setDescription(ErrorMessages.INVALID_PARAM.message);
			return status;
		}
		
		User user = userDao.getDetail(request.getToken());
		if(user!=null){
			
			Product product = productDao.getProductById(request.getProductId());
			if(product!=null){
				product.setStatus(request.getStatus());
				productDao.updateProduct(product);
				status.setRespCode(ErrorMessages.SUCCESS.code);
				status.setDescription(ErrorMessages.SUCCESS.message);
			}else{
				status.setRespCode(ErrorMessages.INVALID_PARAM.code);
				status.setDescription(ErrorMessages.INVALID_PARAM.message);
				return status;
			}
		}else{
			status.setRespCode(ErrorMessages.INVALID_TOKEN.code);
			status.setDescription(ErrorMessages.INVALID_TOKEN.message);
			return status;
		}
		return status;
	}
}
