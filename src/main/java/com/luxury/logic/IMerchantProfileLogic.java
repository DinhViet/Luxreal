package com.luxury.logic;

import java.util.List;

import com.luxury.persistence.model.MerchantProfile;
import com.luxury.real.model.MerchantProfileRequest;

public interface IMerchantProfileLogic {

	public MerchantProfile getMerchantProfile(String accesskey);
	
	public void  CreateMerchant(MerchantProfile request);
	
	public long checkCodeMerchant(String merchantCode);
	
	public List<MerchantProfile> getlistMerchantProfile(MerchantProfileRequest request);
	
	public int countRecord(MerchantProfileRequest request);
	
}
