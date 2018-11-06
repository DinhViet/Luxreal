package com.truemoney.walletgw.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.truemoney.walletgw.common.ErrorMessages;
import com.truemoney.walletgw.common.LoggingUtils;
import com.truemoney.walletgw.contract.base.MainResponse;
import com.truemoney.walletgw.contract.paymentservice.request.RequestPaymentRequest;
import com.truemoney.walletgw.ecom.endpoint.IWalletEcomEndpoint;
import com.truemoney.walletgw.ecom.model.CreateMerchantRequest;
import com.truemoney.walletgw.ecom.model.CreateMerchantResponse;
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

@Controller
@RequestMapping("api/v1")
public class WalletEcomRestController {

	private static Logger logger = Logger.getLogger(WalletEcomRestController.class);

	@Autowired
	IWalletEcomEndpoint walletEnpoint;
	
	@RequestMapping(value = "/ecom/request-payment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public MainResponse requestPayment(@Valid @RequestBody RequestPaymentRequest request, HttpServletRequest httpServletRequest) throws Exception {
		MainResponse response = new MainResponse();
		try {
			logger.info("Request login from merchant  : "+ LoggingUtils.writeObjectAsJson(request));
			response = walletEnpoint.requestPayment(request);
			logger.info("Response login from backend  : "+ LoggingUtils.writeObjectAsJson(response));
			
			return response;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			response.setRespCode(ErrorMessages.UNKNOW_ERROR.code);
			response.setDescription(ErrorMessages.UNKNOW_ERROR.message);
			return response;
		}
	}
	
	@RequestMapping(value = "/ecom/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public EcomLoginResponse login( @Valid @RequestBody EcomLoginRequest request, BindingResult bindingResult,HttpServletRequest httpServletRequest) {
		EcomLoginResponse response = null;
		try {
			
			logger.info("Request login from merchant  : "+ LoggingUtils.writeObjectAsJson(request));
			
			StringBuffer msgError = new StringBuffer();
			if(bindingResult.hasErrors()){
				
				Object object = bindingResult.getFieldError();
				FieldError fieldError = (FieldError) object;
				msgError.append(fieldError.getDefaultMessage());
				
				/*for (Object object : bindingResult.getAllErrors()) {
				    if (object instanceof FieldError) {
				     FieldError fieldError = (FieldError) object;
				     msgError.append(fieldError.getDefaultMessage()+",");
				    }
				}*/
				response = new EcomLoginResponse();
				response.setRespCode(ErrorMessages.INVALID_PARAM.code);
				response.setDescription(msgError.toString());
				
				logger.info("Response login from backend  : "+ LoggingUtils.writeObjectAsJson(response));
				return response;
			}
			
			response = walletEnpoint.login(request);
			
			logger.info("Response login from backend  : "+ LoggingUtils.writeObjectAsJson(response));
			
			return response;
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			response = new EcomLoginResponse();
			response.setRespCode(ErrorMessages.UNKNOW_ERROR.code);
			response.setDescription(ErrorMessages.UNKNOW_ERROR.message);
			return response;
		}
	}
	
	@RequestMapping(value = "/ecom/create-order", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public EcomOrderResponse CreateOrder(@Valid @RequestBody EcomOrderRequest request, BindingResult bindingResult,HttpServletRequest httpServletRequest) {
		EcomOrderResponse response = null;
		try {
			
			logger.info("Request create order from merchant  : "+ LoggingUtils.writeObjectAsJson(request));
			
			StringBuffer msgError = new StringBuffer();
			if(bindingResult.hasErrors()){
				Object object = bindingResult.getFieldError();
				FieldError fieldError = (FieldError) object;
				msgError.append(fieldError.getDefaultMessage());
				response = new EcomOrderResponse();
				response.setRespCode(ErrorMessages.INVALID_PARAM.code);
				response.setDescription(msgError.toString());
				
				logger.info("Response create order from backend  : "+ LoggingUtils.writeObjectAsJson(response));
				return response;
			}
			
			response = walletEnpoint.createOrder(request);
			
			logger.info("Response create order from backend  : "+ LoggingUtils.writeObjectAsJson(response));
			
			return response;
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			response = new EcomOrderResponse();
			response.setRespCode(ErrorMessages.UNKNOW_ERROR.code);
			response.setDescription(ErrorMessages.UNKNOW_ERROR.message);
			return response;
		}
	}
	
	@RequestMapping(value = "/ecom/confirm-order", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public EcomConfirmOrderResponse ConfirmOrder(@Valid @RequestBody EcomConfirmOrderRequest request, BindingResult bindingResult,HttpServletRequest httpServletRequest) {
		EcomConfirmOrderResponse response = null;
		try {
			
			logger.info("Request confirm order from merchant  : "+ LoggingUtils.writeObjectAsJson(request));
			
			StringBuffer msgError = new StringBuffer();
			if(bindingResult.hasErrors()){
				Object object = bindingResult.getFieldError();
				FieldError fieldError = (FieldError) object;
				msgError.append(fieldError.getDefaultMessage());
				
				response = new EcomConfirmOrderResponse();
				response.setRespCode(ErrorMessages.INVALID_PARAM.code);
				response.setDescription(msgError.toString());
				logger.info("Response confirm order from backend  : "+ LoggingUtils.writeObjectAsJson(response));
				return response;
			}
			
			response = walletEnpoint.confirmOrder(request);
			
			logger.info("Response confirm order from backend  : "+ LoggingUtils.writeObjectAsJson(response));
			
			return response;
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			response = new EcomConfirmOrderResponse();
			response.setRespCode(ErrorMessages.UNKNOW_ERROR.code);
			response.setDescription(ErrorMessages.UNKNOW_ERROR.message);
			return response;
		}
	}
	
	
	@RequestMapping(value = "/ecom/check-transaction", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public EcomCheckTransactionResponse CheckTransaction(@Valid @RequestBody EcomCheckTransactionRequest request,BindingResult bindingResult,HttpServletRequest httpServletRequest) {
		EcomCheckTransactionResponse response = null;
		try {
			
			logger.info("Request check transsaction from merchant  : "+ LoggingUtils.writeObjectAsJson(request));
			
			StringBuffer msgError = new StringBuffer();
			if(bindingResult.hasErrors()){
				Object object = bindingResult.getFieldError();
				FieldError fieldError = (FieldError) object;
				msgError.append(fieldError.getDefaultMessage());
				response = new EcomCheckTransactionResponse();
				response.setRespCode(ErrorMessages.INVALID_PARAM.code);
				response.setDescription(msgError.toString());
				logger.info("Response check transsaction from backend  : "+ LoggingUtils.writeObjectAsJson(response));
				return response;
			}
			
			response = walletEnpoint.checkTransaction(request);
			
			logger.info("Response check transsaction from backend  : "+ LoggingUtils.writeObjectAsJson(response));
			
			return response;
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			response = new EcomCheckTransactionResponse();
			response.setRespCode(ErrorMessages.UNKNOW_ERROR.code);
			response.setDescription(ErrorMessages.UNKNOW_ERROR.message);
			return response;
		}
	}
	
	
	@RequestMapping(value = "/ecom/resend-otp", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public EcomResendOtpResponse resendOtp(@Valid @RequestBody EcomResendOtpRequest request,BindingResult bindingResult,HttpServletRequest httpServletRequest) {
		EcomResendOtpResponse response = null;
		try {
			
			logger.info("Request resend OTP from merchant  : "+ LoggingUtils.writeObjectAsJson(request));
			
			StringBuffer msgError = new StringBuffer();
			if(bindingResult.hasErrors()){
				Object object = bindingResult.getFieldError();
				FieldError fieldError = (FieldError) object;
				msgError.append(fieldError.getDefaultMessage());
				
				response = new EcomResendOtpResponse();
				response.setRespCode(ErrorMessages.INVALID_PARAM.code);
				response.setDescription(msgError.toString());
				logger.info("Response resend OTP to merchant  : "+ LoggingUtils.writeObjectAsJson(response));
				return response;
			}
			
			response = walletEnpoint.resendOtp(request);
			
			logger.info("Response resend OTP to merchant  : "+ LoggingUtils.writeObjectAsJson(response));
			
			return response;
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			response = new EcomResendOtpResponse();
			response.setRespCode(ErrorMessages.UNKNOW_ERROR.code);
			response.setDescription(ErrorMessages.UNKNOW_ERROR.message);
			return response;
		}
	}
	
	
	
	@RequestMapping(value = "/ecom/refund", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public EcomRefundResponse refund(@Valid @RequestBody EcomRefundRequest request,BindingResult bindingResult,HttpServletRequest httpServletRequest) {
		EcomRefundResponse response = null;
		try {
			
			logger.info("Refund request from merchant  : "+ LoggingUtils.writeObjectAsJson(request));
			
			StringBuffer msgError = new StringBuffer();
			if(bindingResult.hasErrors()){
				Object object = bindingResult.getFieldError();
				FieldError fieldError = (FieldError) object;
				msgError.append(fieldError.getDefaultMessage());
				response = new EcomRefundResponse();
				response.setRespCode(ErrorMessages.INVALID_PARAM.code);
				response.setDescription(msgError.toString());
				logger.info("Response refund from backend  : "+ LoggingUtils.writeObjectAsJson(response));
				return response;
			}
			
			response = walletEnpoint.refund(request);
			
			logger.info("Response refund from backend  : "+ LoggingUtils.writeObjectAsJson(response));
			
			return response;
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			response = new EcomRefundResponse();
			response.setRespCode(ErrorMessages.UNKNOW_ERROR.code);
			response.setDescription(ErrorMessages.UNKNOW_ERROR.message);
			return response;
		}
	}
	
	
		
	
	@RequestMapping(value = "/ecom/create-merchant", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CreateMerchantResponse createMerchant(@RequestBody CreateMerchantRequest request, HttpServletRequest httpServletRequest) {
		CreateMerchantResponse response = null;
		try {
			
			logger.info("Create Merchant  : "+ LoggingUtils.writeObjectAsJson(request));
			
			response = walletEnpoint.createMerchant(request);
			
			logger.info("Response create merchant  : "+ LoggingUtils.writeObjectAsJson(response));
			
			return response;
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
}
