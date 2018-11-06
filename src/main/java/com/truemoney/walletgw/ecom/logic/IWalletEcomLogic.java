package com.truemoney.walletgw.ecom.logic;

import com.truemoney.walletgw.contract.base.MainResponse;
import com.truemoney.walletgw.contract.paymentservice.request.RequestPaymentRequest;
import com.truemoney.walletgw.ecom.model.EcomCheckTransactionRequest;
import com.truemoney.walletgw.ecom.model.EcomCheckTransactionResponse;
import com.truemoney.walletgw.ecom.model.EcomConfirmOrderRequest;
import com.truemoney.walletgw.ecom.model.EcomConfirmOrderResponse;
import com.truemoney.walletgw.ecom.model.EcomLoginRequest;
import com.truemoney.walletgw.ecom.model.EcomLoginResponse;
import com.truemoney.walletgw.ecom.model.EcomOrderRequest;
import com.truemoney.walletgw.ecom.model.EcomOrderResponse;
import com.truemoney.walletgw.ecom.model.EcomRefundRequest;
import com.truemoney.walletgw.ecom.model.EcomRefundResponse;
import com.truemoney.walletgw.ecom.model.EcomResendOtpRequest;
import com.truemoney.walletgw.ecom.model.EcomResendOtpResponse;

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
