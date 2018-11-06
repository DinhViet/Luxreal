package com.truemoney.walletgw.ecom.logic;

import java.util.List;

import com.truemoney.walletgw.ecom.model.MerchantProfileRequest;
import com.truemoney.walletgw.persistence.model.MerchantProfile;

public interface IMerchantProfileLogic {

	public MerchantProfile getMerchantProfile(String accesskey);
	
	public void  CreateMerchant(MerchantProfile request);
	
	public long checkCodeMerchant(String merchantCode);
	
	public List<MerchantProfile> getlistMerchantProfile(MerchantProfileRequest request);
	
	public int countRecord(MerchantProfileRequest request);
	
}
