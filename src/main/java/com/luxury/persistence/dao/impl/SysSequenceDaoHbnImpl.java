package com.luxury.persistence.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luxury.framework.persistence.hibernate.dao.GeneratedIdDAOHbnImpl;
import com.luxury.persistence.dao.SysSequenceDAO;
import com.luxury.persistence.model.SysSequence;

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
