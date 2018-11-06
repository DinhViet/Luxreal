package com.luxury.operation.service;

import com.luxury.real.model.CreateMerchantRequest;
import com.luxury.real.model.CreateMerchantResponse;
import com.luxury.real.model.MerchantProfileRequest;
import com.luxury.real.model.MerchantProfileResponse;

public interface IMerchantProfileService {

	CreateMerchantResponse createMerchant(CreateMerchantRequest request)throws Exception;
	
	MerchantProfileResponse getListMerchantProfile(MerchantProfileRequest request);
}
