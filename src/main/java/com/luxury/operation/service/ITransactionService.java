package com.luxury.operation.service;

import com.luxury.real.model.TransactionHistoryRequest;
import com.luxury.real.model.TransactionResponse;

public interface ITransactionService {

	TransactionResponse getListTransaction(TransactionHistoryRequest request);
	
}
