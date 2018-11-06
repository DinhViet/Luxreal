package com.truemoney.walletgw.operation.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truemoney.walletgw.common.ErrorMessages;
import com.truemoney.walletgw.ecom.model.TransactionHistoryForm;
import com.truemoney.walletgw.ecom.model.TransactionHistoryRequest;
import com.truemoney.walletgw.ecom.model.TransactionResponse;
import com.truemoney.walletgw.operation.service.ITransactionService;
import com.truemoney.walletgw.persistence.dao.ITransactionDAO;
import com.truemoney.walletgw.persistence.model.Transactions;

@Service
public class TransactionService implements ITransactionService{

	@Autowired
	ITransactionDAO transactionDao;
	
	@Override
	public TransactionResponse getListTransaction(TransactionHistoryRequest request) {
		// TODO Auto-generated method stub
		TransactionResponse response = new TransactionResponse();
		List<Transactions>  listTransaction = transactionDao.getTransaction(request);
		int count = transactionDao.countRecordTransaction(request);
		List<TransactionHistoryForm> listTransactionResponse = new ArrayList<>();
		for (Transactions transactions : listTransaction) {
			TransactionHistoryForm trans = new TransactionHistoryForm();
			BeanUtils.copyProperties(transactions, trans);
			listTransactionResponse.add(trans);
		}
		response.setListTransaction(listTransactionResponse);
		response.setPageSize(request.getPageId());
		response.setResponseCode(ErrorMessages.SUCCESS.code);
		response.setResponseMessage(ErrorMessages.SUCCESS.message);
		response.setTotalRecord(count);
		return response;
	}

}
