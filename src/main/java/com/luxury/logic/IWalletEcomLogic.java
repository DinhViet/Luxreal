package com.luxury.logic;

import com.luxury.contract.base.MainResponse;
import com.luxury.real.model.EcomCheckTransactionRequest;
import com.luxury.real.model.EcomCheckTransactionResponse;
import com.luxury.real.model.EcomConfirmOrderRequest;
import com.luxury.real.model.EcomConfirmOrderResponse;
import com.luxury.real.model.EcomLoginRequest;
import com.luxury.real.model.EcomLoginResponse;
import com.luxury.real.model.EcomOrderRequest;
import com.luxury.real.model.EcomOrderResponse;
import com.luxury.real.model.EcomRefundRequest;
import com.luxury.real.model.EcomRefundResponse;
import com.luxury.real.model.EcomResendOtpRequest;
import com.luxury.real.model.EcomResendOtpResponse;
import com.luxury.request.RequestPaymentRequest;

public interface IWalletEcomLogic {

	EcomLoginResponse login(EcomLoginRequest request) throws Exception;
	
	EcomOrderResponse createOrder(EcomOrderRequest request)throws Exception;
	
	EcomConfirmOrderResponse confirmOrder(EcomConfirmOrderRequest request)throws Exception;
	
	EcomCheckTransactionResponse checkTransaction(EcomCheckTransactionRequest request)throws Exception;
	
	EcomResendOtpResponse resendOtp(EcomResendOtpRequest request)throws Exception;
	
	EcomRefundResponse refund(EcomRefundRequest request)throws Exception;
	
	String getId();

	MainResponse requestPayment(RequestPaymentRequest request);
	
}
