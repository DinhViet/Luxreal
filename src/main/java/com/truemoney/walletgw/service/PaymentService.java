package com.truemoney.walletgw.service;

import com.truemoney.walletgw.contract.base.MainResponse;
import com.truemoney.walletgw.contract.paymentservice.request.UpdateTransactionStatusRequest;

public interface PaymentService {

	MainResponse updateTransactionStatus(UpdateTransactionStatusRequest request);

}
