package com.luxury.operation.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luxury.common.ErrorMessages;
import com.luxury.common.GeneratorUtil;
import com.luxury.common.Utils;
import com.luxury.logic.IMerchantProfileLogic;
import com.luxury.operation.service.IMerchantProfileService;
import com.luxury.persistence.model.MerchantProfile;
import com.luxury.real.model.CreateMerchantRequest;
import com.luxury.real.model.CreateMerchantResponse;
import com.luxury.real.model.MerchantProfileForm;
import com.luxury.real.model.MerchantProfileRequest;
import com.luxury.real.model.MerchantProfileResponse;

@Service
public class MerchantProfileService implements IMerchantProfileService{

	@Autowired
	IMerchantProfileLogic profileLogic;
	
	@Override
	public CreateMerchantResponse createMerchant(CreateMerchantRequest request) throws Exception {
		CreateMerchantResponse response = new CreateMerchantResponse();
		
		if(StringUtils.isEmpty(request.getCode()) || StringUtils.isEmpty(request.getName())){
			response.setRespCode(ErrorMessages.INVALID_PARAM.code);
			response.setDescription(ErrorMessages.INVALID_PARAM.message);
			return response;
		}
		
		long checkCode = profileLogic.checkCodeMerchant(request.getCode());
		if(checkCode > 0){
			response.setRespCode(ErrorMessages.INVALID_PARAM.code);
			response.setDescription(ErrorMessages.INVALID_PARAM.message);
			return response;
		}
		
		MerchantProfile  profile = new MerchantProfile();
		profile.setName(request.getName());
		profile.setMerchantCode(request.getCode());
		profile.setCreationDate(Utils.getDateTimeZone7(new Date()));
		profile.setLastUpdate(Utils.getDateTimeZone7(new Date()));
		profile.setAccesskey(GeneratorUtil.generateAccessKey());
		profile.setSecretkey(GeneratorUtil.generateSecret());
		profile.setStatus("ACTIVE");
		profileLogic.CreateMerchant(profile);
		
		BeanUtils.copyProperties(profile, response);
		response.setRespCode(ErrorMessages.SUCCESS.code);
		response.setDescription(ErrorMessages.SUCCESS.message);
		
		return response;
	}

	@Override
	public MerchantProfileResponse getListMerchantProfile(MerchantProfileRequest request) {
		MerchantProfileResponse response = new MerchantProfileResponse();
		List<MerchantProfile>  listProfile = profileLogic.getlistMerchantProfile(request);
		List<MerchantProfileForm> listProfileResponse = new ArrayList<>();
		for (MerchantProfile merchantProfile : listProfile) {
			MerchantProfileForm profile = new MerchantProfileForm();
			BeanUtils.copyProperties(merchantProfile, profile);
			listProfileResponse.add(profile);
		}
		int count = profileLogic.countRecord(request);
		
		response.setListMerchant(listProfileResponse);
		response.setPageSize(request.getPageId());
		response.setResponseCode(ErrorMessages.SUCCESS.code);
		response.setResponseMessage(ErrorMessages.SUCCESS.message);
		response.setTotalRecord(count);
		return response;
	}

}
