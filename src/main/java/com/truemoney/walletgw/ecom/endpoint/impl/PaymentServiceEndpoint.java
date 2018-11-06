package com.truemoney.walletgw.ecom.endpoint.impl;

import org.springframework.stereotype.Service;

import com.truemoney.walletgw.SharedConstants;
import com.truemoney.walletgw.common.CommonUtil;
import com.truemoney.walletgw.ecom.endpoint.IPaymentServiceEndpoint;
import com.truemoney.walletgw.ecom.model.paymentservice.PaymentServiceBasicResponse;
import com.truemoney.walletgw.ecom.model.paymentservice.request.PSUpdateTransStatusRequest;

@Service
public class PaymentServiceEndpoint implements IPaymentServiceEndpoint {

	private String PS_UPDATE_STATUS_URI = SharedConstants.PAYMENT_SERVICE_UPDATE_STATUS_URI;
	private String PS_BASE_REQUEST_URL = SharedConstants.PAYMENT_SERVICE_BASE_REQUEST_URL;
	
	@Override
	public PaymentServiceBasicResponse updateTransactionStatus(PSUpdateTransStatusRequest request) {
		return CommonUtil.sendRequest(PS_BASE_REQUEST_URL + PS_UPDATE_STATUS_URI, request, PaymentServiceBasicResponse.class);
	}

}
