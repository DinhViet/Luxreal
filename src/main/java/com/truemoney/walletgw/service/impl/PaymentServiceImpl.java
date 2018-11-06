package com.truemoney.walletgw.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.truemoney.walletgw.common.ErrorMessages;
import com.truemoney.walletgw.contract.base.MainResponse;
import com.truemoney.walletgw.contract.paymentservice.request.UpdateTransactionStatusRequest;
import com.truemoney.walletgw.ecom.endpoint.IPaymentServiceEndpoint;
import com.truemoney.walletgw.ecom.model.paymentservice.PaymentServiceBasicResponse;
import com.truemoney.walletgw.ecom.model.paymentservice.request.PSUpdateTransStatusRequest;
import com.truemoney.walletgw.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	IPaymentServiceEndpoint paymentServiceEndpoint;
	
	@Override
	public MainResponse updateTransactionStatus(UpdateTransactionStatusRequest request) {
		MainResponse response = new MainResponse();
		
		PSUpdateTransStatusRequest psRequest = new PSUpdateTransStatusRequest();
		
		//verify signature
		
		//call payment service to update transaction status
		String signature = request.getSignature();
		psRequest.setToken(request.getToken());
		psRequest.setSignature(signature);
		psRequest.setStatus(request.getStatus());
		psRequest.setTransRef(request.getTransRef());
		PaymentServiceBasicResponse psResponse = paymentServiceEndpoint.updateTransactionStatus(psRequest);
		if(!PaymentServiceBasicResponse.SUCCESS_RESPONSE_CODE.equals(psResponse.getResponseCode())) {
			response.setRespCode(ErrorMessages.UNKNOW_ERROR.code);
			response.setDescription(ErrorMessages.UNKNOW_ERROR.message);
			return response;
		}
		response.setRespCode(ErrorMessages.SUCCESS.code);
		response.setDescription(ErrorMessages.SUCCESS.message);
		response.setData("");
		return response;
	}

}
