package com.truemoney.walletgw.ecom.endpoint.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luxury.model.CreateUserRequest;
import com.luxury.model.CreateUserResponse;
import com.luxury.model.DetailUserResponse;
import com.luxury.model.LoginRequest;
import com.luxury.model.LoginResponse;
import com.luxury.model.Status;
import com.luxury.model.UserDetail;
import com.truemoney.walletgw.common.ErrorMessages;
import com.truemoney.walletgw.common.Utils;
import com.truemoney.walletgw.ecom.endpoint.ILuxuryUserService;
import com.truemoney.walletgw.persistence.dao.ILuxuryUserDao;
import com.truemoney.walletgw.persistence.dao.SysSequenceDAO;
import com.truemoney.walletgw.persistence.model.Product;
import com.truemoney.walletgw.persistence.model.User;

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
		user.setCreationDate(new Date());
		user.setActive(true);
		user.setLastUpdate(new Date());
		user.setUrlIcon(request.getUrlIcon());
		String passWord = Utils.encryptToMD5("PASS"+request.getPassWord());
		user.setPassWord(passWord);
		user.setUserName(request.getUserName());
		user.setDateOfBirth(request.getDateOfBirth());
		user.setRatePoint(BigDecimal.ZERO);
		String token = generateString();
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
	public String generateString() {
		long timeMilise = Utils.getMiliseconds();
		
		long timeRequestId = sysSequenceDAO.getNextSequence("seq_id_entity", 0l);
		
		return String.valueOf(timeRequestId) + timeMilise;
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
		User user = userDao.getDetail(request);
		if(user!=null){
			UserDetail userDetail = new UserDetail(); 
			userDetail.setDateOfBirth(user.getDateOfBirth());
			userDetail.setUrlIcon(user.getUrlIcon());
			userDetail.setRatePoint(user.getRatePoint());
			userDetail.setUserName(user.getUserName());
			ArrayList<Product> listProduct = new ArrayList<Product>(user.getProduct());
			userDetail.setProduct(listProduct);
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