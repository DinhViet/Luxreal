package com.luxury.endpoint;

import com.luxury.model.paymentservice.PaymentServiceBasicResponse;
import com.luxury.paymentservice.request.PSUpdateTransStatusRequest;

public interface IPaymentServiceEndpoint {

	PaymentServiceBasicResponse updateTransactionStatus(PSUpdateTransStatusRequest request);

}
