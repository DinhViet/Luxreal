package com.truemoney.walletgw.ecom.logic.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import com.truemoney.walletgw.SharedConstants;
import com.truemoney.walletgw.client.IWalletEcomClient;
import com.truemoney.walletgw.common.ErrorMessages;
import com.truemoney.walletgw.common.LoggingUtils;
import com.truemoney.walletgw.common.MappingErrorCode;
import com.truemoney.walletgw.common.Utils;
import com.truemoney.walletgw.contract.base.MainResponse;
import com.truemoney.walletgw.contract.paymentservice.request.RequestPaymentRequest;
import com.truemoney.walletgw.ecom.logic.IWalletEcomLogic;
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
import com.truemoney.walletgw.ecom.model.WalletGWCheckTransactionBody;
import com.truemoney.walletgw.ecom.model.WalletGWCheckTransactionRequest;
import com.truemoney.walletgw.ecom.model.WalletGWCheckTransactionResponse;
import com.truemoney.walletgw.ecom.model.WalletGWConfirmOrderBody;
import com.truemoney.walletgw.ecom.model.WalletGWConfirmOrderRequest;
import com.truemoney.walletgw.ecom.model.WalletGWConfirmOrderResponse;
import com.truemoney.walletgw.ecom.model.WalletGWCreateOrderBody;
import com.truemoney.walletgw.ecom.model.WalletGWCreateOrderRequest;
import com.truemoney.walletgw.ecom.model.WalletGWCreateOrderResponse;
import com.truemoney.walletgw.ecom.model.WalletGWLoginBody;
import com.truemoney.walletgw.ecom.model.WalletGWLoginRequest;
import com.truemoney.walletgw.ecom.model.WalletGWLoginResponse;
import com.truemoney.walletgw.ecom.model.WalletGWRefundRequest;
import com.truemoney.walletgw.ecom.model.WalletGWRefundResponse;
import com.truemoney.walletgw.ecom.model.WalletGWResendOtpRequest;
import com.truemoney.walletgw.ecom.model.WalletGWResendOtpResponse;
import com.truemoney.walletgw.ecom.model.WalletGWStatus;
import com.truemoney.walletgw.ecom.model.WalletInfomation;
import com.truemoney.walletgw.persistence.dao.ITransactionDAO;
import com.truemoney.walletgw.persistence.dao.SysSequenceDAO;
import com.truemoney.walletgw.persistence.model.Transactions;

@Service
@Transactional
public class WalletEcomLogic implements IWalletEcomLogic{

	private static Logger logger = Logger.getLogger(WalletEcomLogic.class);
	
	@Autowired
	IWalletEcomClient client;
	
	@Autowired
	ITransactionDAO transactionDao;
	
	@Autowired
    SysSequenceDAO sysSequenceDAO;
	
	@Autowired
	private ThreadPoolTaskScheduler scheduler;
	
	@Override
	public EcomLoginResponse login(EcomLoginRequest request) throws Exception {
		EcomLoginResponse response = new EcomLoginResponse();
		WalletGWLoginRequest requestGW = new WalletGWLoginRequest();
		String requestId = getId();
		requestGW.setLogin_id(request.getLoginId());
		requestGW.setRequest_id(requestId);
		if(SharedConstants.VERIFY.equalsIgnoreCase(request.getType()))
			requestGW.setType("INIT");
		else requestGW.setType(request.getType());
		
		if("TRUEMONEY".equalsIgnoreCase(request.getServiceId()))
			requestGW.setWallet_type("vn-wallet");
		
		WalletInfomation customerInfo = request.getCustomerInfo();
		if(customerInfo!=null){
			requestGW.setSecurity_code(customerInfo.getOtp());
			requestGW.setPassword(customerInfo.getSecurityCode());
			requestGW.setWallet_id(customerInfo.getWalletId());
		}
		
		logger.info("Request login to walletgw  : "+ LoggingUtils.writeObjectAsJson(requestGW));
		
		WalletGWLoginResponse responseGW = client.postToHTTPSAddress(SharedConstants.WALLETGW_SERVER_BASE_REQUEST_URL+SharedConstants.WALLETGW_LOGIN_URI, requestGW, WalletGWLoginResponse.class);
		if(response!=null) requestGW.setRequest_id(requestId);
		logger.info("Response login from walletgw  : "+ LoggingUtils.writeObjectAsJson(responseGW));
		
		if(responseGW!=null){
			WalletGWStatus status = responseGW.getStatus();
			WalletGWLoginBody data = responseGW.getData();
			if(status!=null){
				response.setRespCode(MappingErrorCode.getTypeByCode(status.getCode()));
				response.setDescription(status.getMessage());
				if(data!=null){
					response.setBalance(data.getBalance());
					response.setExpire(data.getExpired_in());
					response.setFullName(data.getFull_name());
					response.setLoginId(data.getLogin_id());
					response.setOtp(data.isOtp());
					response.setWalletId(data.getPhone_number());
					response.setToken(data.getAccess_token());
				}
			}else{
				response.setRespCode(ErrorMessages.TIMEOUT.code);
				response.setDescription(ErrorMessages.TIMEOUT.message);
			}
		}else{
			response.setRespCode(ErrorMessages.TIMEOUT.code);
			response.setDescription(ErrorMessages.TIMEOUT.message);
		}
		
		return response;
	}

	@Override
	public EcomOrderResponse createOrder(EcomOrderRequest request) throws Exception {

		EcomOrderResponse response = new EcomOrderResponse();
		Transactions transaction = new Transactions();
		
		String providerOrderId = getId();
		String merchantOrderId = getId();
		String walletgwId = getId();
		
		transaction.setAgentName(request.getMerchantName());
		transaction.setAmount(request.getAmount());
		transaction.setCreationDate(Utils.getDateTimeZone7(new Date()));
		transaction.setMerchant(request.getMerchantCode());
		transaction.setType(SharedConstants.VERIFY);
		
		transaction.setWalletGWRequestId(walletgwId);
		transaction.setProviderOrderId(providerOrderId);
		transaction.setRequestId(request.getRequestId());
		transaction.setRequestTime(Utils.getDateTimeZone7(new Date()));
		transaction.setServiceId(request.getServiceId());
		
		
		WalletGWCreateOrderRequest requestGW = new WalletGWCreateOrderRequest(); 
		requestGW.setAccess_token(request.getToken());
		requestGW.setAmount(request.getAmount());
		requestGW.setMerchant_name(request.getMerchantName());
		requestGW.setMerchant_order_id(providerOrderId);
		requestGW.setRequest_id(walletgwId);
		requestGW.setMerchant_code(request.getMerchantId());
		
		logger.info("Request ecom to walletgw  : "+ LoggingUtils.writeObjectAsJson(requestGW));
		
		WalletGWCreateOrderResponse responseGW = client.postToHTTPSAddress(SharedConstants.WALLETGW_SERVER_BASE_REQUEST_URL+SharedConstants.WALLETGW_CREATE_ORDER_URI, requestGW, WalletGWCreateOrderResponse.class);
		if(responseGW!=null) responseGW.setRequestId(walletgwId);
		logger.info("Response ecom from walletgw  : "+ LoggingUtils.writeObjectAsJson(responseGW));
		
		if(responseGW==null){
			transaction.setStatus(Transactions.STATUS_TIME_OUT);
			transactionDao.insert(transaction);
			
			response = new EcomOrderResponse();
			response.setRespCode(ErrorMessages.TIMEOUT.code);
			response.setDescription(ErrorMessages.TIMEOUT.message);
			
			return response;
		}
		
		WalletGWStatus status = responseGW.getStatus();
		
		if(status!=null){
			if(ErrorMessages.SUCCESS.code.equals(status.getCode()))
			{
				transaction.setStatus(Transactions.STATUS_STATRT);
				WalletGWCreateOrderBody data = responseGW.getData();
				
				response.setRespCode(MappingErrorCode.getTypeByCode(status.getCode()));
				response.setDescription(status.getMessage());
				
				if(data!=null){
					
					response.setAmount(data.getAmount());
					response.setBalance(data.getBalance());
					response.setDiscount(data.getDiscount());
					response.setFee(data.getFee());
					response.setSecurityToken(data.getVerify_token());
					response.setSecurityType(data.getSecurity_type());
					response.setTotalAmount(data.getTotal_amount());
					
					response.setOrderId(merchantOrderId+"|"+data.getWallet_order_id());
					response.setCustomerPhone(data.getCustomerPhone());
					
					transaction.setWalletOrderId(data.getWallet_order_id());
					transaction.setMerchantOrderId(merchantOrderId+"|"+data.getWallet_order_id());
				}
			}else{
				response.setRespCode(MappingErrorCode.getTypeByCode(status.getCode()));
				response.setDescription(status.getMessage());
				
				transaction.setStatus(Transactions.STATUS_FAIL);
			}
			transactionDao.insert(transaction);
		}else{
			transaction.setStatus(Transactions.STATUS_TIME_OUT);
			transactionDao.insert(transaction);
			
			response = new EcomOrderResponse();
			response.setRespCode(ErrorMessages.TIMEOUT.code);
			response.setDescription(ErrorMessages.TIMEOUT.message);
		}
		
		return response;
	}

	@Override
	public EcomConfirmOrderResponse confirmOrder(EcomConfirmOrderRequest request) throws Exception {
		// TODO Auto-generated method stub
		
		EcomConfirmOrderResponse response = new EcomConfirmOrderResponse();
		
		Transactions transaction = transactionDao.getTransactionOrderId(request.getOrderId());
		if(transaction!=null){
			String requestId = getId();
			transaction.setType(SharedConstants.CONFIRM);
			WalletGWConfirmOrderRequest requestGW = new WalletGWConfirmOrderRequest();
			requestGW.setAccess_token(request.getToken());
			requestGW.setSecurity_code(request.getSecurityCode());
			requestGW.setVerify_token(request.getSecurityToken());
			requestGW.setWallet_order_id(transaction.getWalletOrderId());
			requestGW.setRequest_id(requestId);
			
			logger.info("Request confirm order to walletgw  : "+ LoggingUtils.writeObjectAsJson(requestGW));
			
			WalletGWConfirmOrderResponse responseGW = client.postToHTTPSAddress(SharedConstants.WALLETGW_SERVER_BASE_REQUEST_URL+SharedConstants.WALLETGW_CONFIRM_ORDER_URI, requestGW, WalletGWConfirmOrderResponse.class);
			if(responseGW==null){
				PaymentOrderCheckService checkOrder = new PaymentOrderCheckService();
				checkOrder.setMerchantOrderId(transaction.getMerchantOrderId());
				checkOrder.setRequestId(transaction.getWalletGWRequestId());
				checkOrder.setTransactionDao(transactionDao);
				checkOrder.setClient(client);
				checkOrder.setOrderId(transaction.getWalletOrderId());
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(new Date());
				cal.add(Calendar.MINUTE, 3);
				
				scheduler.schedule(checkOrder, cal.getTime());
			}
			
			if(responseGW!=null) responseGW.setRequestId(requestId);
			logger.info("Response confirm order from walletgw  : "+ LoggingUtils.writeObjectAsJson(responseGW));
			
			if(responseGW!=null){
				WalletGWStatus status = responseGW.getStatus();
				WalletGWConfirmOrderBody data = responseGW.getData();
				if(status!=null){
					if(ErrorMessages.SUCCESS.code.equals(status.getCode())){
						transaction.setStatus(Transactions.STATUS_SUCCESS);
					}else{
						transaction.setStatus(Transactions.STATUS_FAIL);
					}
					transaction.setType(SharedConstants.CONFIRM);
					transaction.setRespCode(status.getCode());
					transaction.setDescription(status.getMessage());
					transactionDao.update(transaction);
					
					response.setRespCode(MappingErrorCode.getTypeByCode(status.getCode()));
					response.setDescription(status.getMessage());
					response.setOrderId(request.getOrderId());
					if(data!=null){
						response.setBalance(data.getBalance());
						response.setRestOtp(data.getAttempt_otp_left());
					}
				}else{
					
					transaction.setType(SharedConstants.CONFIRM);
					transaction.setStatus(Transactions.STATUS_TIME_OUT);
					transaction.setRespCode(ErrorMessages.TIMEOUT.code);
					transaction.setDescription(ErrorMessages.TIMEOUT.message);
					transactionDao.update(transaction);
					
					response.setRespCode(ErrorMessages.TIMEOUT.code);
					response.setDescription(ErrorMessages.TIMEOUT.message);
					response.setOrderId(request.getOrderId());
					return response;
				}
			}else{
				transaction.setStatus(Transactions.STATUS_TIME_OUT);
				transaction.setRespCode(ErrorMessages.TIMEOUT.code);
				transaction.setDescription(ErrorMessages.TIMEOUT.message);
				transactionDao.update(transaction);
				
				response.setOrderId(request.getOrderId());
				response.setRespCode(ErrorMessages.TIMEOUT.code);
				response.setDescription(ErrorMessages.TIMEOUT.message);
				
				return response;
			}
		}else{
			response.setRespCode(ErrorMessages.ORDER_IS_NOT_FOUND.code);
			response.setDescription(ErrorMessages.ORDER_IS_NOT_FOUND.message);
		}
		return response;
	}

	@Override
	public EcomCheckTransactionResponse checkTransaction(EcomCheckTransactionRequest request) throws Exception {
		EcomCheckTransactionResponse response = new EcomCheckTransactionResponse();
		
		Transactions transaction = transactionDao.getTransactionOrderId(request.getOrderId());
		
		if(transaction!=null){
			if(Transactions.STATUS_SUCCESS.equalsIgnoreCase(transaction.getStatus())){
				response.setRespCode(ErrorMessages.SUCCESS.code);
				response.setDescription(ErrorMessages.SUCCESS.message);
				response.setOrderId(request.getOrderId());
				response.setStatus(transaction.getStatus());
			}else{
				WalletGWCheckTransactionRequest requestGW = new WalletGWCheckTransactionRequest();  
				requestGW.setOrder_id(transaction.getWalletOrderId());
				requestGW.setRequest_id(transaction.getWalletGWRequestId());
				logger.info("Request check transaction order to walletgw  : "+ LoggingUtils.writeObjectAsJson(requestGW));
				
				WalletGWCheckTransactionResponse responseGW = client.postToHTTPSAddress(SharedConstants.WALLETGW_SERVER_BASE_REQUEST_URL+SharedConstants.WALLETGW_CHECK_TRANSACTION_ORDER_URI, requestGW, WalletGWCheckTransactionResponse.class);
				
				logger.info("Response check transaction order from walletgw  : "+ LoggingUtils.writeObjectAsJson(responseGW));
				
				if(responseGW!=null){
					WalletGWStatus status = responseGW.getStatus();
					WalletGWCheckTransactionBody data = responseGW.getData();
					if(status!=null){
						response.setRespCode(MappingErrorCode.getTypeByCode(status.getCode()));
						response.setDescription(status.getMessage());
						if(data!=null){
							if("CREATE".equalsIgnoreCase(data.getStatus())){
								response.setStatus(Transactions.STATUS_STATRT);
							}else if("EXECUTE".equalsIgnoreCase(data.getStatus())){
								response.setStatus(Transactions.STATUS_SUCCESS);
								transaction.setStatus(Transactions.STATUS_SUCCESS);
								transactionDao.update(transaction);
							}else if("CANCEL".equalsIgnoreCase(data.getStatus())){
								response.setStatus(Transactions.STATUS_REFUND);
								transaction.setStatus(Transactions.STATUS_REFUND);
								transactionDao.update(transaction);
							}else {
								response.setStatus(data.getStatus());
							}
						}
					}else{
						response.setRespCode(ErrorMessages.TIMEOUT.code);
						response.setDescription(ErrorMessages.TIMEOUT.message);
					}
				}else{
					response.setRespCode(ErrorMessages.TIMEOUT.code);
					response.setDescription(ErrorMessages.TIMEOUT.message);
				}
			}
		}else{
			response.setRespCode(ErrorMessages.ORDER_IS_NOT_FOUND.code);
			response.setDescription(ErrorMessages.ORDER_IS_NOT_FOUND.message);
		}
		return response;
	}


	@Override
	public EcomResendOtpResponse resendOtp(EcomResendOtpRequest request) throws Exception {

		EcomResendOtpResponse response = new EcomResendOtpResponse();
		
		Transactions transaction = transactionDao.getTransactionOrderId(request.getOrderId());
		
		if(transaction!=null){
			
			WalletGWResendOtpRequest requestGW = new WalletGWResendOtpRequest();
			requestGW.setAccess_token(request.getToken());
			requestGW.setVerify_token(request.getSecurityToken());
			requestGW.setWallet_order_id(transaction.getWalletOrderId());
			requestGW.setRequest_id(getId());
			logger.info("Request resend otp to walletgw : "+ LoggingUtils.writeObjectAsJson(requestGW));
			
			WalletGWResendOtpResponse responseGW = client.postToHTTPSAddress(SharedConstants.WALLETGW_SERVER_BASE_REQUEST_URL+SharedConstants.WALLETGW_RESEND_OTP_URI, requestGW, WalletGWResendOtpResponse.class);
			
			logger.info("Response resend otp from walletgw : "+ LoggingUtils.writeObjectAsJson(responseGW));
			
			if(responseGW!=null){
				WalletGWStatus status = responseGW.getStatus();
				if(status!=null){
					response.setRespCode(MappingErrorCode.getTypeByCode(status.getCode()));
					response.setDescription(status.getMessage());
				}else{
					response.setRespCode(ErrorMessages.TIMEOUT.code);
					response.setDescription(ErrorMessages.TIMEOUT.message);
				}
			}else{
				response.setRespCode(ErrorMessages.TIMEOUT.code);
				response.setDescription(ErrorMessages.TIMEOUT.message);
			}
		}else{
			response.setRespCode(ErrorMessages.ORDER_IS_NOT_FOUND.code);
			response.setDescription(ErrorMessages.ORDER_IS_NOT_FOUND.message);
		}
		
		return response;
	}
	
	
	@Override
	public String getId() {
		
		long timeMilise = Utils.getMiliseconds();
		
		long timeRequestId = sysSequenceDAO.getNextSequence("seq_id_entity", 0l);
		
		return String.valueOf(timeRequestId) + timeMilise;
	}

	@Override
	public EcomRefundResponse refund(EcomRefundRequest request) throws Exception {
		EcomRefundResponse response = new EcomRefundResponse();
		
		Transactions transaction = transactionDao.getTransactionOrderId(request.getOrderId());
		
		if(transaction!=null){
			WalletGWRefundRequest requestGW = new WalletGWRefundRequest(); 
			requestGW.setRequest_id(getId());
			requestGW.setPayment_order_id(transaction.getWalletOrderId());
			requestGW.setPayment_request_id(transaction.getWalletGWRequestId());
			
			logger.info("Request refund to conntor : "+ LoggingUtils.writeObjectAsJson(requestGW));
			WalletGWRefundResponse responseGW = client.postToHTTPSAddress(SharedConstants.WALLETGW_SERVER_BASE_REQUEST_URL+SharedConstants.WALLETGW_REFUND_URI, requestGW, WalletGWRefundResponse.class);
			logger.info("Response refund from conntor : "+ LoggingUtils.writeObjectAsJson(responseGW));
			
			if(responseGW==null){
				PaymentOrderCheckRefundService checkRefund = new PaymentOrderCheckRefundService();
				checkRefund.setMerchantOrderId(transaction.getMerchantOrderId());
				checkRefund.setRequestId(transaction.getWalletGWRequestId());
				checkRefund.setTransactionDao(transactionDao);
				checkRefund.setClient(client);
				checkRefund.setOrderId(transaction.getWalletOrderId());
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(new Date());
				cal.add(Calendar.MINUTE, 3);
				
				scheduler.schedule(checkRefund, cal.getTime());
			}
			
			
			if(responseGW!=null){
				WalletGWStatus status = responseGW.getStatus();
				if(status!=null){
					if(ErrorMessages.SUCCESS.code.equalsIgnoreCase(status.getCode())){
						transaction.setStatus(Transactions.STATUS_REFUND);
						transactionDao.update(transaction);
					}
					response.setRespCode(MappingErrorCode.getTypeByCode(status.getCode()));
					response.setDescription(status.getMessage());
				}else{
					response.setRespCode(ErrorMessages.TIMEOUT.code);
					response.setDescription(ErrorMessages.TIMEOUT.message);
				}
			}else{
				response.setRespCode(ErrorMessages.TIMEOUT.code);
				response.setDescription(ErrorMessages.TIMEOUT.message);
			}
		}else{
			response.setRespCode(ErrorMessages.ORDER_IS_NOT_FOUND.code);
			response.setDescription(ErrorMessages.ORDER_IS_NOT_FOUND.message);
		}
		
		return response;
	}

	@Override
	public MainResponse requestPayment(RequestPaymentRequest request) {
		MainResponse response = new MainResponse();
		
		Transactions transaction = new Transactions();
		
		String providerOrderId = getId();
		String merchantOrderId = getId();
		String walletgwId = getId();
		
		transaction.setAgentName(request.getMerchantName());
		transaction.setAmount(request.getAmount());
		transaction.setCreationDate(Utils.getDateTimeZone7(new Date()));
		transaction.setMerchant(request.getMerchantCode());
		transaction.setType(SharedConstants.INIT);
		
		transaction.setWalletGWRequestId(walletgwId);
		transaction.setProviderOrderId(providerOrderId);
		transaction.setRequestId(request.getRequestId());
		transaction.setRequestTime(Utils.getDateTimeZone7(new Date()));
		transaction.setServiceId(request.getServiceId());
		transaction.setStatus(Transactions.STATUS_STATRT);
		transaction.setMerchantOrderId(merchantOrderId);
		transactionDao.insert(transaction);
		
		Map<String, String> resData = new HashMap<String, String>();
		resData.put("merchant_order_id", merchantOrderId);
		response.setData(resData);
		return response;
	}

}
