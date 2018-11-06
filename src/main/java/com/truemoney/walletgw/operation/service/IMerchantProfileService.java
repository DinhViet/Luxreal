package com.truemoney.walletgw.operation.service;

import com.truemoney.walletgw.ecom.model.CreateMerchantRequest;
import com.truemoney.walletgw.ecom.model.CreateMerchantResponse;
import com.truemoney.walletgw.ecom.model.MerchantProfileRequest;
import com.truemoney.walletgw.ecom.model.MerchantProfileResponse;

public interface IMerchantProfileService {

	CreateMerchantResponse createMerchant(CreateMerchantRequest request)throws Exception;
	
	MerchantProfileResponse getListMerchantProfile(MerchantProfileRequest request);
}
