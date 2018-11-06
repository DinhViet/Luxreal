package com.luxury.persistence.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.luxury.common.Utils;
import com.luxury.framework.persistence.hibernate.dao.GeneratedIdDAOHbnImpl;
import com.luxury.persistence.dao.IMerchantProfileDAO;
import com.luxury.persistence.model.MerchantProfile;
import com.luxury.real.model.MerchantProfileRequest;

@Repository
public class MerchantProfileDAOImpl extends GeneratedIdDAOHbnImpl<MerchantProfile> implements IMerchantProfileDAO{

	@Override
	public Class<MerchantProfile> getEntityClass() {
		// TODO Auto-generated method stub
		return MerchantProfile.class;
	}

	@Override
	public void insert(MerchantProfile profile) {
		// TODO Auto-generated method stub
		getSession().save(profile);
	}

	@Override
	public void update(MerchantProfile profile) {
		// TODO Auto-generated method stub
		getSession().saveOrUpdate(profile);
	}

	@Override
	public MerchantProfile getMerchantProfile(String accesskey) {
		Criteria criteria = null;
		try {
			criteria = getSession().createCriteria(MerchantProfile.class);
			criteria.add(Restrictions.eq("accesskey", accesskey));
			} catch (Exception e) {
				e.printStackTrace();
		}
		return (MerchantProfile) criteria.uniqueResult();
	}

	@Override
	public long checkCodeMerchant(String merchantCode) {
		Query query = getSession().createQuery(
				"select count(*) from MerchantProfile  where merchantCode =:merchantCode");
				query.setString("merchantCode", merchantCode);
				long count = (long)query.uniqueResult();
		return count;
	}

	@Override
	public List<MerchantProfile> getlistMerchantProfile(MerchantProfileRequest request) {
		Criteria criteria = null;
		try {
			criteria = getSession().createCriteria(MerchantProfile.class);
			
			 if(!StringUtils.isEmpty(request.getTxtSearch())){
				 Disjunction status = Restrictions.disjunction();
				 status.add(Restrictions.ilike("name", request.getTxtSearch(),MatchMode.ANYWHERE));
				 status.add(Restrictions.ilike("merchantCode", request.getTxtSearch(),MatchMode.ANYWHERE));
				 status.add(Restrictions.ilike("accesskey", request.getTxtSearch(),MatchMode.ANYWHERE));
				 status.add(Restrictions.ilike("secretkey", request.getTxtSearch(),MatchMode.ANYWHERE));
				 status.add(Restrictions.ilike("description", request.getTxtSearch(),MatchMode.ANYWHERE));
				 criteria.add(status);
			 }
			 
			 if(!StringUtils.isEmpty(request.getStatus())){
				 criteria.add(Restrictions.ilike("status", request.getStatus(),MatchMode.ANYWHERE));
			 }
			 
			 if(!StringUtils.isEmpty(request.getFromDate())){
				 criteria.add(Restrictions.ge("creationDate", Utils.getDateFirtDay(request.getFromDate())));
			 }
			 
			 if(!StringUtils.isEmpty(request.getToDate())){
				 criteria.add(Restrictions.le("creationDate",Utils.getDateLastDay(request.getToDate())));
			 }
			
			criteria.addOrder(Order.desc("creationDate"));
			 
			criteria.setFirstResult(request.getPageId());
			criteria.setMaxResults(request.getLimit());
			List<MerchantProfile> listProfile = criteria.list();
			
			return listProfile;
			
			} catch (Exception e) {
				e.printStackTrace();
		}
		return null;
	}

	@Override
	public int countRecord(MerchantProfileRequest request) {
		Criteria criteria = null;
		try {
			criteria = getSession().createCriteria(MerchantProfile.class);
			
			 if(!StringUtils.isEmpty(request.getTxtSearch())){
				 Disjunction status = Restrictions.disjunction();
				 status.add(Restrictions.ilike("name", request.getTxtSearch(),MatchMode.ANYWHERE));
				 status.add(Restrictions.ilike("merchantCode", request.getTxtSearch(),MatchMode.ANYWHERE));
				 status.add(Restrictions.ilike("accesskey", request.getTxtSearch(),MatchMode.ANYWHERE));
				 status.add(Restrictions.ilike("secretkey", request.getTxtSearch(),MatchMode.ANYWHERE));
				 status.add(Restrictions.ilike("description", request.getTxtSearch(),MatchMode.ANYWHERE));
				 criteria.add(status);
			 }
			 
			 if(!StringUtils.isEmpty(request.getStatus())){
				 criteria.add(Restrictions.ilike("status", request.getStatus(),MatchMode.ANYWHERE));
			 }
			 
			 if(!StringUtils.isEmpty(request.getFromDate())){
				 criteria.add(Restrictions.ge("creationDate", Utils.getDateFirtDay(request.getFromDate())));
			 }
			 
			 if(!StringUtils.isEmpty(request.getToDate())){
				 criteria.add(Restrictions.le("creationDate",Utils.getDateLastDay(request.getToDate())));
			 }
			
			criteria.addOrder(Order.desc("creationDate"));
			 
			criteria.setFirstResult(request.getPageId());
			criteria.setMaxResults(request.getLimit());
			
			return criteria.list().size();
			
			} catch (Exception e) {
				e.printStackTrace();
		}
		return 0;
	}
	

}
