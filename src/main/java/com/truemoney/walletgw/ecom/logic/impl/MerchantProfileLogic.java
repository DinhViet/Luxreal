package com.truemoney.walletgw.ecom.logic.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truemoney.walletgw.ecom.logic.IMerchantProfileLogic;
import com.truemoney.walletgw.ecom.model.MerchantProfileRequest;
import com.truemoney.walletgw.persistence.dao.IMerchantProfileDAO;
import com.truemoney.walletgw.persistence.model.MerchantProfile;

@Transactional
@Service
public class MerchantProfileLogic implements IMerchantProfileLogic{

	@Autowired
	IMerchantProfileDAO profileDao;
	
	@Override
	public MerchantProfile getMerchantProfile(String accesskey) {
		// TODO Auto-generated method stub
		return profileDao.getMerchantProfile(accesskey);
	}

	@Override
	public void CreateMerchant(MerchantProfile request) {
		// TODO Auto-generated method stub
		profileDao.insert(request);
	}

	@Override
	public long checkCodeMerchant(String merchantCode) {
		// TODO Auto-generated method stub
		return profileDao.checkCodeMerchant(merchantCode);
	}

	@Override
	public List<MerchantProfile> getlistMerchantProfile(MerchantProfileRequest request) {
		// TODO Auto-generated method stub
		return profileDao.getlistMerchantProfile(request);
	}

	@Override
	public int countRecord(MerchantProfileRequest request) {
		// TODO Auto-generated method stub
		return profileDao.countRecord(request);
	}

}
