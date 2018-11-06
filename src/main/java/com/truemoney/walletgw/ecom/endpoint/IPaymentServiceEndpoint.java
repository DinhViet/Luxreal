package com.truemoney.walletgw.ecom.endpoint;

import com.truemoney.walletgw.ecom.model.paymentservice.PaymentServiceBasicResponse;
import com.truemoney.walletgw.ecom.model.paymentservice.request.PSUpdateTransStatusRequest;

public interface IPaymentServiceEndpoint {

	PaymentServiceBasicResponse updateTransactionStatus(PSUpdateTransStatusRequest request);

}
