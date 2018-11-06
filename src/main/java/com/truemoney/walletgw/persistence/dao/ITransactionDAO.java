package com.truemoney.walletgw.persistence.dao;

import com.truemoney.walletgw.ecom.model.TransactionHistoryRequest;
import com.truemoney.walletgw.persistence.model.Transactions;

import java.util.List;

import com.truemoney.framework.persistence.dao.GeneratedIdDAO;

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