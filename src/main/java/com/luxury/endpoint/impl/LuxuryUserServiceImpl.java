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
import com.luxury.model.UpdateUserRequest;
import com.luxury.model.UpdateUserResponse;
import com.luxury.model.UserDetail;
import com.luxury.persistence.dao.ILuxuryUserDao;
import com.luxury.persistence.dao.SysSequenceDAO;
import com.luxury.persistence.model.ImageProduct;
import com.luxury.persistence.model.Product;
import com.luxury.persistence.model.User;

@Service
@Transactional
public class LuxuryUserServiceImpl implements ILuxuryUserService {

	@Autowired
	ILuxuryUserDao userDao;

	@Autowired
	SysSequenceDAO sysSequenceDAO;

	@Override
	public CreateUserResponse createUser(CreateUserRequest request) {
		CreateUserResponse response = new CreateUserResponse();
		long checkUserName = userDao.checkUserName(request.getUserName().trim());

		long checkMail = userDao.checkMail(request.getMail().trim());
		if (checkUserName > 0 || checkMail > 0) {
			response.setRespCode(ErrorMessages.INVALID_USERNAME.code);
			response.setDescription(ErrorMessages.INVALID_USERNAME.message);
			return response;
		}
		User user = new User();
		user.setName(request.getName().trim());
		user.setMail(request.getMail().trim());
		user.setCreationDate(new Date());
		user.setActive(true);
		user.setLastUpdate(new Date());
		user.setUrlIcon(request.getUrlIcon());
		String passWord = Utils.encryptSHA256("PASS" + request.getPassWord().trim());
		user.setPassWord(passWord);
		user.setUserName(request.getUserName());
		user.setDateOfBirth(request.getDateOfBirth());
		user.setRatePoint(BigDecimal.ZERO);
		user.setWebsite(request.getWebsite());
		user.setDescription(request.getDescription());
		user.setPhoneNumber(request.getPhoneNumber());
		String token = Utils.genUId();
		user.setToken(token);
		boolean createUser = userDao.createUser(user);
		if (createUser) {
			response.setRespCode(ErrorMessages.SUCCESS.code);
			response.setDescription(ErrorMessages.SUCCESS.message);
			response.setToken(token);
		} else {
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
		if (request.getUserName().contains("@")) {
			String passWord = Utils.encryptSHA256("PASS" + request.getPassWord());
			request.setPassWord(passWord);
			User user = userDao.loginEmail(request);
			if (user != null) {
				response.setRespCode(ErrorMessages.SUCCESS.code);
				response.setDescription(ErrorMessages.SUCCESS.message);
				response.setToken(user.getToken());
				response.setName(user.getName());
			} else {
				response.setRespCode(ErrorMessages.INVALID_USERNAME_PASSWORD.code);
				response.setDescription(ErrorMessages.INVALID_USERNAME_PASSWORD.message);
			}
		} else {
			String passWord = Utils.encryptSHA256("PASS" + request.getPassWord());
			request.setPassWord(passWord);
			User user = userDao.login(request);
			if (user != null) {
				response.setRespCode(ErrorMessages.SUCCESS.code);
				response.setDescription(ErrorMessages.SUCCESS.message);
				response.setToken(user.getToken());
				response.setName(user.getName());
			} else {
				response.setRespCode(ErrorMessages.INVALID_USERNAME_PASSWORD.code);
				response.setDescription(ErrorMessages.INVALID_USERNAME_PASSWORD.message);
			}
		}
		return response;
	}

	@Override
	public DetailUserResponse getDetail(LoginRequest request) {
		DetailUserResponse response = new DetailUserResponse();
		Status status = new Status();
		if (StringUtils.isEmpty(request.getToken())) {
			status.setRespCode(ErrorMessages.INVALID_PARAM.code);
			status.setDescription(ErrorMessages.INVALID_PARAM.message);
			response.setStatus(status);
			return response;
		}
		User user = userDao.getDetail(request.getToken());
		if (user != null) {
			UserDetail userDetail = new UserDetail();
			userDetail.setDateOfBirth(user.getDateOfBirth());
			userDetail.setUrlIcon(user.getUrlIcon());
			userDetail.setRatePoint(user.getRatePoint());
			userDetail.setUserName(user.getUserName());
			userDetail.setName(user.getName());
			userDetail.setWebsite(user.getWebsite());
			userDetail.setDescription(user.getDescription());
			userDetail.setPhoneNumber(user.getPhoneNumber());
			List<ProductOfUser> listProductJ = new ArrayList<>();
			Set<Product> listProduct = user.getProduct();
			for (Product product : listProduct) {
				ProductOfUser productJ = new ProductOfUser();
				BeanUtils.copyProperties(product, productJ);
				productJ.setProductName(product.getProductName());
				List<Image> listImages = new ArrayList<>();
				Set<ImageProduct> setimage = product.getImageProduct();
				for (ImageProduct imageProduct : setimage) {
					Image image = new Image();
					image.setImage_hd(imageProduct.getUrlImage());
					image.setImage_sd(imageProduct.getUrlImageSD());
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
		} else {
			status.setRespCode(ErrorMessages.INVALID_PARAM.code);
			status.setDescription(ErrorMessages.INVALID_PARAM.message);
			response.setStatus(status);
		}
		return response;
	}

	@Override
	public UpdateUserResponse updateUser(UpdateUserRequest request) {
		UpdateUserResponse response = new UpdateUserResponse();
		if (StringUtils.isEmpty(request.getToken())) {
			response.setRespCode(ErrorMessages.INVALID_PARAM.code);
			response.setDescription(ErrorMessages.INVALID_PARAM.message);
			return response;
		}
		User user = userDao.getDetail(request.getToken());
		if (user != null) {
			if (!StringUtils.isEmpty(request.getName())) {
				user.setName(request.getName());
			}
			if (!StringUtils.isEmpty(request.getUrlIcon())) {
				user.setUrlIcon(request.getUrlIcon());
			}
			if (!StringUtils.isEmpty(request.getPassWord())) {
				String passWord = Utils.encryptSHA256("PASS" + request.getPassWord().trim());
				user.setPassWord(passWord);
			}
			if(!StringUtils.isEmpty(request.getWebsite())){
				user.setWebsite(request.getWebsite());
			}
			if(!StringUtils.isEmpty(request.getPhoneNumber())){
				user.setPhoneNumber(request.getPhoneNumber());
			}
			if(!StringUtils.isEmpty(request.getDescription())){
				user.setDescription(request.getDescription());
			}
			boolean save = userDao.updateUser(user);
			if (save) {
				response.setRespCode(ErrorMessages.SUCCESS.code);
				response.setDescription(ErrorMessages.SUCCESS.message);
				response.setToken(user.getToken());
				response.setName(user.getName());
			} else {
				response.setRespCode(ErrorMessages.UNKNOW_ERROR.code);
				response.setDescription(ErrorMessages.UNKNOW_ERROR.message);
			}
		} else {
			response.setRespCode(ErrorMessages.INVALID_PARAM.code);
			response.setDescription(ErrorMessages.INVALID_PARAM.message);
		}
		return response;
	}

}
