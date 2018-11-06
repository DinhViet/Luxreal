package com.truemoney.walletgw.persistence.dao;

import java.util.List;

import com.truemoney.framework.persistence.dao.GeneratedIdDAO;
import com.truemoney.walletgw.ecom.model.MerchantProfileRequest;
import com.truemoney.walletgw.persistence.model.MerchantProfile;

public interface IMerchantProfileDAO extends GeneratedIdDAO<MerchantProfile>{

	public void insert(MerchantProfile profile);
	
	public void update(MerchantProfile profile);
	
	public MerchantProfile getMerchantProfile(String accesskey);
	
	public long checkCodeMerchant(String merchantCode);
	
	public List<MerchantProfile> getlistMerchantProfile(MerchantProfileRequest request);
	
	public int countRecord(MerchantProfileRequest request);
	
	
}
