package com.luxury.service;

import com.luxury.contract.base.MainResponse;
import com.luxury.request.UpdateTransactionStatusRequest;

public interface PaymentService {

	MainResponse updateTransactionStatus(UpdateTransactionStatusRequest request);

}
