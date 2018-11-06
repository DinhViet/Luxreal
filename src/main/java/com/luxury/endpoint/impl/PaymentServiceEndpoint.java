package com.luxury.endpoint.impl;

import org.springframework.stereotype.Service;

import com.luxury.application.SharedConstants;
import com.luxury.common.CommonUtil;
import com.luxury.endpoint.IPaymentServiceEndpoint;
import com.luxury.model.paymentservice.PaymentServiceBasicResponse;
import com.luxury.paymentservice.request.PSUpdateTransStatusRequest;

@Service
public class PaymentServiceEndpoint implements IPaymentServiceEndpoint {

	private String PS_UPDATE_STATUS_URI = SharedConstants.PAYMENT_SERVICE_UPDATE_STATUS_URI;
	private String PS_BASE_REQUEST_URL = SharedConstants.PAYMENT_SERVICE_BASE_REQUEST_URL;
	
	@Override
	public PaymentServiceBasicResponse updateTransactionStatus(PSUpdateTransStatusRequest request) {
		return CommonUtil.sendRequest(PS_BASE_REQUEST_URL + PS_UPDATE_STATUS_URI, request, PaymentServiceBasicResponse.class);
	}

}
