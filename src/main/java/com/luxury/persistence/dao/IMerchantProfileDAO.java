package com.luxury.persistence.dao;

import java.util.List;

import com.luxury.framework.persistence.dao.GeneratedIdDAO;
import com.luxury.persistence.model.MerchantProfile;
import com.luxury.real.model.MerchantProfileRequest;

public interface IMerchantProfileDAO extends GeneratedIdDAO<MerchantProfile>{

	public void insert(MerchantProfile profile);
	
	public void update(MerchantProfile profile);
	
	public MerchantProfile getMerchantProfile(String accesskey);
	
	public long checkCodeMerchant(String merchantCode);
	
	public List<MerchantProfile> getlistMerchantProfile(MerchantProfileRequest request);
	
	public int countRecord(MerchantProfileRequest request);
	
	
}
