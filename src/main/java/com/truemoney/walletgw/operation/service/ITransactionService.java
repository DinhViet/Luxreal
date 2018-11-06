package com.truemoney.walletgw.operation.service;

import com.truemoney.walletgw.ecom.model.TransactionHistoryRequest;
import com.truemoney.walletgw.ecom.model.TransactionResponse;

public interface ITransactionService {

	TransactionResponse getListTransaction(TransactionHistoryRequest request);
	
}
