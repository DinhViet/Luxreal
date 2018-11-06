package com.luxury.operation.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luxury.common.ErrorMessages;
import com.luxury.operation.service.ITransactionService;
import com.luxury.persistence.dao.ITransactionDAO;
import com.luxury.persistence.model.Transactions;
import com.luxury.real.model.TransactionHistoryForm;
import com.luxury.real.model.TransactionHistoryRequest;
import com.luxury.real.model.TransactionResponse;

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
