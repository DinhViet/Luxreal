package com.truemoney.walletgw.persistence.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.truemoney.walletgw.persistence.dao.SysSequenceDAO;
import com.truemoney.walletgw.persistence.model.SysSequence;

import com.truemoney.framework.persistence.hibernate.dao.GeneratedIdDAOHbnImpl;

@Repository
@Transactional
public class SysSequenceDaoHbnImpl extends GeneratedIdDAOHbnImpl<SysSequence> implements SysSequenceDAO {

	@Override
	public Class<SysSequence> getEntityClass() {
		return SysSequence.class;
	}

	@Override
	public Long getNextSequence(String sequence, Long actor) {
		Criteria criteria = getSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("name", sequence));
		SysSequence counter = (SysSequence) criteria.uniqueResult();
		if (counter == null) {
			counter = new SysSequence();
			counter.setName(sequence);
			counter.setValue(0L);
		}
		counter.setValue(counter.getValue() + 1L);
		saveOrUpdate(counter, actor);
		return counter.getValue();
	}
}
