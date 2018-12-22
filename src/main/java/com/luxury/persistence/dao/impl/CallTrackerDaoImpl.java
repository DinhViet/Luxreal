package com.luxury.persistence.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.luxury.framework.persistence.hibernate.dao.GeneratedIdDAOHbnImpl;
import com.luxury.persistence.dao.ICallTrackerDao;
import com.luxury.persistence.model.CallTracker;

@Repository
public class CallTrackerDaoImpl extends GeneratedIdDAOHbnImpl<CallTracker> implements ICallTrackerDao{

	@Override
	public boolean createCallTracker(CallTracker request) {
		try {
			getSession().save(request);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public CallTracker getCallTracker(String phoneNumber,String productId) {
		Criteria criteria = null;
		try {
			criteria = getSession().createCriteria(CallTracker.class);
			criteria.add(Restrictions.eq("phoneNumber", phoneNumber));
			criteria.add(Restrictions.eq("productId", productId));
			} catch (Exception e) {
				e.printStackTrace();
		}
		return (CallTracker) criteria.uniqueResult();
	}

	@Override
	public Class<CallTracker> getEntityClass() {
		// TODO Auto-generated method stub
		return CallTracker.class;
	}

	@Override
	public boolean updateCallTracker(CallTracker request) {
		try {
			getSession().saveOrUpdate(request);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
