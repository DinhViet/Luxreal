package com.luxury.endpoint.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luxury.application.SharedConstants;
import com.luxury.common.ErrorMessages;
import com.luxury.common.GenerateKey;
import com.luxury.common.GeneratorUtil;
import com.luxury.common.Utils;
import com.luxury.contract.base.MainResponse;
import com.luxury.endpoint.IWalletEcomEndpoint;
import com.luxury.logic.IMerchantProfileLogic;
import com.luxury.logic.IWalletEcomLogic;
import com.luxury.persistence.model.MerchantProfile;
import com.luxury.real.model.CreateMerchantRequest;
import com.luxury.real.model.CreateMerchantResponse;
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
import com.luxury.real.model.WalletInfomation;
import com.luxury.request.RequestPaymentRequest;


@Service
public class WalletEcomEndpoint implements IWalletEcomEndpoint{

	private static Logger logger = Logger
			.getLogger(WalletEcomEndpoint.class);
	
	@Autowired
	IMerchantProfileLogic profileLogic;
	
	@Autowired
	IWalletEcomLogic walletEcomLogic;
	
	@Override
	public EcomLoginResponse login(EcomLoginRequest request) throws Exception {
		// TODO Auto-generated method stub
		
		EcomLoginResponse response = null;
		WalletInfomation customerInfo = request.getCustomerInfo();
		if(customerInfo!=null){
			if(StringUtils.isEmpty(customerInfo.getSecurityCode()) || StringUtils.isEmpty(customerInfo.getWalletId())){
				response = new EcomLoginResponse();
				response.setRespCode(ErrorMessages.INVALID_PARAM.code);
				response.setDescription(ErrorMessages.INVALID_PARAM.message);
				return response;
			}
		}
		if(!SharedConstants.VERIFY.equalsIgnoreCase(request.getType()) && !SharedConstants.CONFIRM.equalsIgnoreCase(request.getType())){
			response = new EcomLoginResponse();
			response.setRespCode(ErrorMessages.INVALID_PARAM.code);
			response.setDescription("Type không hợp lệ");
			return response;
		}
		if(!"TRUEMONEY".equals(request.getServiceId())){
			response = new EcomLoginResponse();
			response.setRespCode(ErrorMessages.INVALID_SERVICE.code);
			response.setDescription(ErrorMessages.INVALID_SERVICE.message);
			return response;
		}
		
		if(StringUtils.isEmpty(request.getAccesskey()) || StringUtils.isEmpty(request.getSignature()) || StringUtils.isEmpty(request.getType()) || StringUtils.isEmpty(request.getServiceId()) || StringUtils.isEmpty(request.getRequestId())){
			response = new EcomLoginResponse();
			response.setRespCode(ErrorMessages.INVALID_PARAM.code);
			response.setDescription(ErrorMessages.INVALID_PARAM.message);
			return response;
		}
		
		if(SharedConstants.CONFIRM.equalsIgnoreCase(request.getType())){
			if(StringUtils.isEmpty(request.getLoginId())){
				response = new EcomLoginResponse();
				response.setRespCode(ErrorMessages.INVALID_PARAM.code);
				response.setDescription(ErrorMessages.INVALID_PARAM.message);
				return response;
			}
			if(request.getLoginId().length() >60){
				response = new EcomLoginResponse();
				response.setRespCode(ErrorMessages.INVALID_PARAM.code);
				response.setDescription("LoginId độ dài không hợp lệ");
				return response;
			}
		}
		
		MerchantProfile profile = profileLogic.getMerchantProfile(request.getAccesskey());
		if(profile==null){
			response = new EcomLoginResponse();
			response.setRespCode(ErrorMessages.ACCESSKEY_NOT_EXIT.code);
			response.setDescription(ErrorMessages.ACCESSKEY_NOT_EXIT.message);
			return response;
		}
		
		GenerateKey genKey = new GenerateKey();
		
		String urlParameters = "access_key="+request.getAccesskey()+"&wallet_id="+customerInfo.getWalletId()+"&request_id="+request.getRequestId()+"&service_id="+request.getServiceId();
		logger.info("Sign Data Login= " + urlParameters);
		
		String signatureCheck = genKey.generateSignatures(request.getAccesskey(),urlParameters, profile.getSecretkey());
		
		if(!signatureCheck.equals(request.getSignature())){
			response = new EcomLoginResponse();
			response.setRespCode(ErrorMessages.SIGNATURE_FAIL.code);
			response.setDescription(ErrorMessages.SIGNATURE_FAIL.message);
			return response;
		}
		
		return walletEcomLogic.login(request);
	}

	@Override
	public EcomOrderResponse createOrder(EcomOrderRequest request) throws Exception {
		
		EcomOrderResponse response = null;
		
		if(StringUtils.isEmpty(request.getAccesskey()) ||StringUtils.isEmpty(request.getServiceId())|| StringUtils.isEmpty(request.getSignature()) || StringUtils.isEmpty(request.getRequestId()) || StringUtils.isEmpty(request.getToken())
          || request.getAmount() ==null){
			response = new EcomOrderResponse();
			response.setRespCode(ErrorMessages.INVALID_PARAM.code);
			response.setDescription(ErrorMessages.INVALID_PARAM.message);
			return response;
		}
		
		if(!"TRUEMONEY".equals(request.getServiceId())){
			response = new EcomOrderResponse();
			response.setRespCode(ErrorMessages.INVALID_SERVICE.code);
			response.setDescription(ErrorMessages.INVALID_SERVICE.message);
			return response;
		}
		
		MerchantProfile profile = profileLogic.getMerchantProfile(request.getAccesskey());
		if(profile==null){
			response = new EcomOrderResponse();
			response.setRespCode(ErrorMessages.ACCESSKEY_NOT_EXIT.code);
			response.setDescription(ErrorMessages.ACCESSKEY_NOT_EXIT.message);
			return response;
		}
		GenerateKey genKey = new GenerateKey();
		
		String urlParameters = "access_key="+request.getAccesskey()+"&token="+request.getToken()+"&amount="+request.getAmount()+"&request_id="+request.getRequestId();
		logger.info("Sign Data Create Order= " + urlParameters);
		String signatureCheck = genKey.generateSignatures(request.getAccesskey(),urlParameters, profile.getSecretkey());
		
		if(!signatureCheck.equals(request.getSignature())){
			response = new EcomOrderResponse();
			response.setRespCode(ErrorMessages.SIGNATURE_FAIL.code);
			response.setDescription(ErrorMessages.SIGNATURE_FAIL.message);
			return response;
		}
		
		request.setMerchantCode(profile.getMerchantCode());
		
		return walletEcomLogic.createOrder(request);
	}

	@Override
	public EcomConfirmOrderResponse confirmOrder(EcomConfirmOrderRequest request) throws Exception {
		// TODO Auto-generated method stub
		EcomConfirmOrderResponse response = null;
		if(StringUtils.isEmpty(request.getAccesskey()) || StringUtils.isEmpty(request.getOrderId()) || StringUtils.isEmpty(request.getRequestId()) || StringUtils.isEmpty(request.getSecurityCode()) ||StringUtils.isEmpty(request.getSecurityToken()) ||StringUtils.isEmpty(request.getToken()) || StringUtils.isEmpty(request.getSecurityCode())){
			response = new EcomConfirmOrderResponse();
			response.setRespCode(ErrorMessages.INVALID_PARAM.code);
			response.setDescription(ErrorMessages.INVALID_PARAM.message);
			return response;
		}
		
		MerchantProfile profile = profileLogic.getMerchantProfile(request.getAccesskey());
		if(profile==null){
			response = new EcomConfirmOrderResponse();
			response.setRespCode(ErrorMessages.ACCESSKEY_NOT_EXIT.code);
			response.setDescription(ErrorMessages.ACCESSKEY_NOT_EXIT.message);
			return response;
		}
		
		GenerateKey genKey = new GenerateKey();
		
		String urlParameters = "access_key="+request.getAccesskey()+"&token="+request.getToken()+"&order_id="+request.getOrderId();
		logger.info("Sign Data confirm Order= " + urlParameters);
		String signatureCheck = genKey.generateSignatures(request.getAccesskey(),urlParameters, profile.getSecretkey());
		
		if(!signatureCheck.equals(request.getSignature())){
			response = new EcomConfirmOrderResponse();
			response.setRespCode(ErrorMessages.SIGNATURE_FAIL.code);
			response.setDescription(ErrorMessages.SIGNATURE_FAIL.message);
			return response;
		}
		
		
		return walletEcomLogic.confirmOrder(request);
	}

	@Override
	public EcomCheckTransactionResponse checkTransaction(EcomCheckTransactionRequest request) throws Exception {
		// TODO Auto-generated method stub
		EcomCheckTransactionResponse response = null;
		
		if(StringUtils.isEmpty(request.getAccesskey()) || StringUtils.isEmpty(request.getOrderId())){
			response = new EcomCheckTransactionResponse();
			response.setRespCode(ErrorMessages.INVALID_PARAM.code);
			response.setDescription(ErrorMessages.INVALID_PARAM.message);
			return response;
		}
		
		MerchantProfile profile = profileLogic.getMerchantProfile(request.getAccesskey());
		if(profile==null){
			response = new EcomCheckTransactionResponse();
			response.setRespCode(ErrorMessages.ACCESSKEY_NOT_EXIT.code);
			response.setDescription(ErrorMessages.ACCESSKEY_NOT_EXIT.message);
			return response;
		}
		
		GenerateKey genKey = new GenerateKey();
		
		String urlParameters = "access_key="+request.getAccesskey()+"&order_id="+request.getOrderId();
		
		String signatureCheck = genKey.generateSignatures(request.getAccesskey(),urlParameters, profile.getSecretkey());
		
		if(!signatureCheck.equals(request.getSignature())){
			response = new EcomCheckTransactionResponse();
			response.setRespCode(ErrorMessages.SIGNATURE_FAIL.code);
			response.setDescription(ErrorMessages.SIGNATURE_FAIL.message);
			return response;
		}
		
		return walletEcomLogic.checkTransaction(request);
	}

	@Override
	public CreateMerchantResponse createMerchant(CreateMerchantRequest request) {
		
		CreateMerchantResponse response = new CreateMerchantResponse();
		
		if(StringUtils.isEmpty(request.getCode()) || StringUtils.isEmpty(request.getName())){
			response.setRespCode(ErrorMessages.INVALID_PARAM.code);
			response.setDescription(ErrorMessages.INVALID_PARAM.message);
			return response;
		}
		
		long checkCode = profileLogic.checkCodeMerchant(request.getCode());
		if(checkCode > 0){
			response.setRespCode(ErrorMessages.INVALID_PARAM.code);
			response.setDescription(ErrorMessages.INVALID_PARAM.message);
			return response;
		}
		
		MerchantProfile  profile = new MerchantProfile();
		profile.setName(request.getName());
		profile.setMerchantCode(request.getCode());
		profile.setCreationDate(Utils.getDateTimeZone7(new Date()));
		profile.setLastUpdate(Utils.getDateTimeZone7(new Date()));
		profile.setAccesskey(GeneratorUtil.generateAccessKey());
		profile.setSecretkey(GeneratorUtil.generateSecret());
		profile.setStatus("ACTIVE");
		profileLogic.CreateMerchant(profile);
		
		BeanUtils.copyProperties(profile, response);
		response.setRespCode(ErrorMessages.SUCCESS.code);
		response.setDescription(ErrorMessages.SUCCESS.message);
		
		return response;
	}

	@Override
	public EcomResendOtpResponse resendOtp(EcomResendOtpRequest request) throws Exception {
		EcomResendOtpResponse response = null;
		
		if(StringUtils.isEmpty(request.getAccesskey()) || StringUtils.isEmpty(request.getOrderId()) || StringUtils.isEmpty(request.getSecurityToken()) || StringUtils.isEmpty(request.getSignature()) || StringUtils.isEmpty(request.getToken())){
			response = new EcomResendOtpResponse();
			response.setRespCode(ErrorMessages.INVALID_PARAM.code);
			response.setDescription(ErrorMessages.INVALID_PARAM.message);
			return response;
		}
		MerchantProfile profile = profileLogic.getMerchantProfile(request.getAccesskey());
		if(profile==null){
			response = new EcomResendOtpResponse();
			response.setRespCode(ErrorMessages.ACCESSKEY_NOT_EXIT.code);
			response.setDescription(ErrorMessages.ACCESSKEY_NOT_EXIT.message);
			return response;
		}
		
		GenerateKey genKey = new GenerateKey();
		
		String urlParameters = "access_key="+request.getAccesskey()+"&order_id="+request.getOrderId()+"&token="+request.getToken();
		
		String signatureCheck = genKey.generateSignatures(request.getAccesskey(),urlParameters, profile.getSecretkey());
		
		if(!signatureCheck.equals(request.getSignature())){
			response = new EcomResendOtpResponse();
			response.setRespCode(ErrorMessages.SIGNATURE_FAIL.code);
			response.setDescription(ErrorMessages.SIGNATURE_FAIL.message);
			return response;
		}
		
		return walletEcomLogic.resendOtp(request);
	}

	@Override
	public EcomRefundResponse refund(EcomRefundRequest request) throws Exception {
		EcomRefundResponse response = null;
		
		if(StringUtils.isEmpty(request.getAccesskey()) || StringUtils.isEmpty(request.getOrderId())){
			response = new EcomRefundResponse();
			response.setRespCode(ErrorMessages.INVALID_PARAM.code);
			response.setDescription(ErrorMessages.INVALID_PARAM.message);
			return response;
		}
		
		MerchantProfile profile = profileLogic.getMerchantProfile(request.getAccesskey());
		if(profile==null){
			response = new EcomRefundResponse();
			response.setRespCode(ErrorMessages.ACCESSKEY_NOT_EXIT.code);
			response.setDescription(ErrorMessages.ACCESSKEY_NOT_EXIT.message);
			return response;
		}
		
		GenerateKey genKey = new GenerateKey();
		
		String urlParameters = "access_key="+request.getAccesskey()+"&order_id="+request.getOrderId();
		
		String signatureCheck = genKey.generateSignatures(request.getAccesskey(),urlParameters, profile.getSecretkey());
		
		if(!signatureCheck.equals(request.getSignature())){
			response = new EcomRefundResponse();
			response.setRespCode(ErrorMessages.SIGNATURE_FAIL.code);
			response.setDescription(ErrorMessages.SIGNATURE_FAIL.message);
			return response;
		}
		
		return walletEcomLogic.refund(request);
	}

	@Override
	public MainResponse requestPayment(RequestPaymentRequest request) {
		MainResponse response = new MainResponse();		
//		if(StringUtils.isEmpty(request.getAccesskey()) ||StringUtils.isEmpty(request.getServiceId())|| StringUtils.isEmpty(request.getSignature()) || StringUtils.isEmpty(request.getRequestId())
//          || request.getAmount() ==null){
//			response.setRespCode(ErrorMessages.INVALID_PARAM.code);
//			response.setDescription(ErrorMessages.INVALID_PARAM.message);
//			return response;
//		}
//		
//		if(!"TRUEMONEY".equals(request.getServiceId())){
//			response.setRespCode(ErrorMessages.INVALID_SERVICE.code);
//			response.setDescription(ErrorMessages.INVALID_SERVICE.message);
//			return response;
//		}
//		
//		MerchantProfile profile = profileLogic.getMerchantProfile(request.getAccesskey());
//		if(profile==null){
//			response.setRespCode(ErrorMessages.ACCESSKEY_NOT_EXIT.code);
//			response.setDescription(ErrorMessages.ACCESSKEY_NOT_EXIT.message);
//			return response;
//		}
//		GenerateKey genKey = new GenerateKey();
//		
//		String urlParameters = "access_key="+request.getAccesskey()+"&amount="+request.getAmount()+"&request_id="+request.getRequestId();
//		logger.info("Sign Data Create Order= " + urlParameters);
//		String signatureCheck = genKey.generateSignatures(request.getAccesskey(),urlParameters, profile.getSecretkey());
//		
//		if(!signatureCheck.equals(request.getSignature())){
//			response.setRespCode(ErrorMessages.SIGNATURE_FAIL.code);
//			response.setDescription(ErrorMessages.SIGNATURE_FAIL.message);
//			return response;
//		}
//		
//		request.setMerchantCode(profile.getMerchantCode());
		
		return walletEcomLogic.requestPayment(request);
	}

}
