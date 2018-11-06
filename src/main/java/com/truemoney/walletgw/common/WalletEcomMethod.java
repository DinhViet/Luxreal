package com.truemoney.walletgw.common;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truemoney.walletgw.persistence.dao.SysSequenceDAO;

@Service
@Transactional
public class WalletEcomMethod {

	@Autowired
    SysSequenceDAO sysSequenceDAO;
	
	public String getId(){
		long timeMilise = Utils.getMiliseconds();
		
		String timeRequestId = Utils.generateAccountCode();//sysSequenceDAO.getNextSequence("seq_id_entity", 0l);
		
		return String.valueOf(timeMilise) + timeRequestId;
	}
	
}
