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
import org.springframework.transaction.annotation.Transactional;

import com.luxury.common.Utils;
import com.luxury.framework.persistence.hibernate.dao.GeneratedIdDAOHbnImpl;
import com.luxury.persistence.dao.ITransactionDAO;
import com.luxury.persistence.model.Transactions;
import com.luxury.real.model.TransactionHistoryRequest;

@Repository 
@Transactional
public class TransactionDAOImpl extends GeneratedIdDAOHbnImpl<Transactions> implements ITransactionDAO{

	@Override
	public Class<Transactions> getEntityClass() {
		return Transactions.class;
	}

	@Override
	public void insert(Transactions trans) {
		getSession().save(trans);
	}

	@Override
	public void update(Transactions trans) {
		// TODO Auto-generated method stub
		getSession().saveOrUpdate(trans);
	}

	@Override
	public Transactions getTransactionOrderId(String merchantOrderId) {
		Criteria criteria = null;
		try {
			criteria = getSession().createCriteria(Transactions.class);
			criteria.add(Restrictions.eq("merchantOrderId", merchantOrderId));
			} catch (Exception e) {
				e.printStackTrace();
		}
		return (Transactions) criteria.uniqueResult();
	}

	@Override
	public long countRequestId(String requestId) {
		Query query = getSession().createQuery(
				"select count(*) from Transactions  where requestId =:requestId");
				query.setString("requestId", requestId);
				long count = (long)query.uniqueResult();
		return count;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Transactions> getTransaction(TransactionHistoryRequest request) {
		
		Criteria criteria = null;
		try {
			criteria = getSession().createCriteria(Transactions.class);
			
			 if(!StringUtils.isEmpty(request.getTxtSearch())){
				 Disjunction status = Restrictions.disjunction();
				 status.add(Restrictions.ilike("requestId", request.getTxtSearch().trim(),MatchMode.ANYWHERE));
				 status.add(Restrictions.ilike("walletGWRequestId", request.getTxtSearch().trim(),MatchMode.ANYWHERE));
				 status.add(Restrictions.ilike("merchantOrderId", request.getTxtSearch().trim(),MatchMode.ANYWHERE));
				 status.add(Restrictions.ilike("providerOrderId", request.getTxtSearch().trim(),MatchMode.ANYWHERE));
				 status.add(Restrictions.ilike("walletOrderId", request.getTxtSearch().trim(),MatchMode.ANYWHERE));
				 status.add(Restrictions.ilike("walletId", request.getTxtSearch().trim(),MatchMode.ANYWHERE));
				 criteria.add(status);
			 }
			 if(!StringUtils.isEmpty(request.getMerchant())){
				 criteria.add(Restrictions.ilike("merchant", request.getMerchant(),MatchMode.ANYWHERE));
			 }
			 
			 if(!StringUtils.isEmpty(request.getStatus())){
				 criteria.add(Restrictions.ilike("status", request.getStatus(),MatchMode.ANYWHERE));
			 }
			 
			 if(!StringUtils.isEmpty(request.getFromDate())){
				 criteria.add(Restrictions.ge("requestTime", Utils.getDateFirtDay(request.getFromDate())));
			 }
			 
			 if(!StringUtils.isEmpty(request.getToDate())){
				 criteria.add(Restrictions.le("requestTime",Utils.getDateLastDay(request.getToDate())));
			 }
			
			criteria.addOrder(Order.desc("requestTime"));
			 
			criteria.setFirstResult(request.getPageId());
			criteria.setMaxResults(request.getLimit());
			List<Transactions> listTransaction = criteria.list();
			
			return listTransaction;
			
			} catch (Exception e) {
				e.printStackTrace();
		}
		return null;
	}

	@Override
	public int countRecordTransaction(TransactionHistoryRequest request) {
		Criteria criteria = null;
		try {
			criteria = getSession().createCriteria(Transactions.class);
			
			 if(!StringUtils.isEmpty(request.getTxtSearch())){
				 Disjunction status = Restrictions.disjunction();
				 status.add(Restrictions.ilike("requestId", request.getTxtSearch(),MatchMode.ANYWHERE));
				 status.add(Restrictions.ilike("walletGWRequestId", request.getTxtSearch(),MatchMode.ANYWHERE));
				 status.add(Restrictions.ilike("merchantOrderId", request.getTxtSearch(),MatchMode.ANYWHERE));
				 status.add(Restrictions.ilike("providerOrderId", request.getTxtSearch(),MatchMode.ANYWHERE));
				 status.add(Restrictions.ilike("walletOrderId", request.getTxtSearch(),MatchMode.ANYWHERE));
				 status.add(Restrictions.ilike("walletId", request.getTxtSearch(),MatchMode.ANYWHERE));
				 criteria.add(status);
			 }
			 if(!StringUtils.isEmpty(request.getMerchant())){
				 criteria.add(Restrictions.ilike("merchant", request.getMerchant(),MatchMode.ANYWHERE));
			 }
			 if(!StringUtils.isEmpty(request.getFromDate())){
				 criteria.add(Restrictions.ge("requestTime", Utils.getDateFirtDay(request.getFromDate())));
			 }
			 
			 if(!StringUtils.isEmpty(request.getToDate())){
				 criteria.add(Restrictions.le("requestTime",Utils.getDateLastDay(request.getToDate())));
			 }
			 
			 if(!StringUtils.isEmpty(request.getStatus())){
				 criteria.add(Restrictions.ilike("status", request.getStatus(),MatchMode.ANYWHERE));
			 }
			 int count = criteria.list().size();
			 
			return count;
			} catch (Exception e) {
				e.printStackTrace();
		}
		return 0;
	}
	
}
