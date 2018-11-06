package com.luxury.persistence.dao;

import com.luxury.framework.persistence.dao.GeneratedIdDAO;
import com.luxury.persistence.model.Transactions;
import com.luxury.real.model.TransactionHistoryRequest;

import java.util.List;

public interface ITransactionDAO  extends GeneratedIdDAO<Transactions>{
	/**
	 * Insert transaction
	 * @param trans
	 */
	public void insert(Transactions trans);
	
	public void update(Transactions trans);
	
	public Transactions getTransactionOrderId(String merchantOrderId);
	
	public List<Transactions> getTransaction(TransactionHistoryRequest request);
	
	public int countRecordTransaction(TransactionHistoryRequest request);
	
	public long countRequestId(String requestId);
}
