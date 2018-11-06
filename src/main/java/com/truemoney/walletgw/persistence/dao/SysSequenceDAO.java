package com.truemoney.walletgw.persistence.dao;

import com.truemoney.walletgw.persistence.model.SysSequence;

import com.truemoney.framework.persistence.dao.GeneratedIdDAO;

public interface SysSequenceDAO extends GeneratedIdDAO<SysSequence> {
	Long getNextSequence(String sequence, Long actor);
}
