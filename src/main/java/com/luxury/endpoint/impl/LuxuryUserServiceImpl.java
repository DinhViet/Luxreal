package com.luxury.endpoint.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luxury.common.ErrorMessages;
import com.luxury.common.Utils;
import com.luxury.endpoint.ILuxuryUserService;
import com.luxury.model.CreateUserRequest;
import com.luxury.model.CreateUserResponse;
import com.luxury.model.DetailUserResponse;
import com.luxury.model.Image;
import com.luxury.model.LoginRequest;
import com.luxury.model.LoginResponse;
import com.luxury.model.ProductOfUser;
import com.luxury.model.Status;
import com.luxury.model.UserDetail;
import com.luxury.persistence.dao.ILuxuryUserDao;
import com.luxury.persistence.dao.SysSequenceDAO;
import com.luxury.persistence.model.ImageProduct;
import com.luxury.persistence.model.Product;
import com.luxury.persistence.model.User;

@Service
@Transactional
public class LuxuryUserServiceImpl implements ILuxuryUserService{

	@Autowired
	ILuxuryUserDao userDao;
	
	@Autowired
    SysSequenceDAO sysSequenceDAO;
	
	@Override
	public CreateUserResponse createUser(CreateUserRequest request) {
		CreateUserResponse response = new CreateUserResponse();
		long checkUserName = userDao.checkUserName(request.getUserName());
		if(checkUserName > 0){
			response.setRespCode(ErrorMessages.INVALID_USERNAME.code);
			response.setDescription(ErrorMessages.INVALID_USERNAME.message);
			return response;
		}
		User user = new User();
		user.setName(request.getName());
		user.setMail(request.getMail());
		user.setCreationDate(new Date());
		user.setActive(true);
		user.setLastUpdate(new Date());
		user.setUrlIcon(request.getUrlIcon());
		String passWord = Utils.encryptToMD5("PASS"+request.getPassWord());
		user.setPassWord(passWord);
		user.setUserName(request.getUserName());
		user.setDateOfBirth(request.getDateOfBirth());
		user.setRatePoint(BigDecimal.ZERO);
		String token = Utils.genUId();
		user.setToken(token);
		boolean createUser = userDao.createUser(user);
		if(createUser){
			response.setRespCode(ErrorMessages.SUCCESS.code);
			response.setDescription(ErrorMessages.SUCCESS.message);
			response.setToken(token);
		}else{
			response.setRespCode(ErrorMessages.UNKNOW_ERROR.code);
			response.setDescription(ErrorMessages.UNKNOW_ERROR.message);
		}
		return response;
	}

	@Override
	public CreateUserResponse updateUser(CreateUserRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LoginResponse login(LoginRequest request) {
		LoginResponse response = new LoginResponse();
		String passWord = Utils.encryptToMD5("PASS"+request.getPassWord());
		request.setPassWord(passWord);
		User user = userDao.login(request);
		if(user!=null){
			response.setRespCode(ErrorMessages.SUCCESS.code);
			response.setDescription(ErrorMessages.SUCCESS.message);
			response.setToken(user.getToken());
			response.setName(user.getName());
		}else{
			response.setRespCode(ErrorMessages.INVALID_USERNAME_PASSWORD.code);
			response.setDescription(ErrorMessages.INVALID_USERNAME_PASSWORD.message);
		}
		return response;
	}

	@Override
	public DetailUserResponse getDetail(LoginRequest request) {
		DetailUserResponse response = new DetailUserResponse();
		Status status = new Status();
		if(StringUtils.isEmpty(request.getToken())){
			status.setRespCode(ErrorMessages.INVALID_PARAM.code);
			status.setDescription(ErrorMessages.INVALID_PARAM.message);
			response.setStatus(status);
			return response;
		}
		User user = userDao.getDetail(request.getToken());
		if(user!=null){
			UserDetail userDetail = new UserDetail(); 
			userDetail.setDateOfBirth(user.getDateOfBirth());
			userDetail.setUrlIcon(user.getUrlIcon());
			userDetail.setRatePoint(user.getRatePoint());
			userDetail.setUserName(user.getUserName());
			userDetail.setName(user.getName());
			List<ProductOfUser> listProductJ = new ArrayList<>();
			Set<Product> listProduct = user.getProduct();
			for (Product product : listProduct) {
				ProductOfUser productJ = new ProductOfUser();
				BeanUtils.copyProperties(product, productJ);
				productJ.setProductName(product.getProductName());
				List<Image> listImages = new ArrayList<>();
				Set<ImageProduct> setimage = product.getImageProduct();
				for (ImageProduct imageProduct : setimage) {
					Image image =  new Image();
					image.setImage(imageProduct.getUrlImage());
					listImages.add(image);
				}
				productJ.setImages(listImages);
				listProductJ.add(productJ);
			}
			userDetail.setProducts(listProductJ);
			status.setRespCode(ErrorMessages.SUCCESS.code);
			status.setDescription(ErrorMessages.SUCCESS.message);
			response.setStatus(status);
			response.setUser(userDetail);
		}else{
			status.setRespCode(ErrorMessages.INVALID_PARAM.code);
			status.setDescription(ErrorMessages.INVALID_PARAM.message);
			response.setStatus(status);
		}
		return response;
	}

}
