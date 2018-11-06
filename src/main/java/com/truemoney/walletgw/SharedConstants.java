package com.truemoney.walletgw;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SharedConstants {

	
	
	public static final String VERIFY = "VERIFY";
	public static final String CONFIRM = "CONFIRM";
	public static final String INIT = "INIT";
	
	
	 public static String WALLETGW_SERVER_BASE_REQUEST_URL = "";
	 
	  @Value("${param.ref.walletgw.connector.base.url}")
	  public void setServerBaseUrl(String value) {
		  WALLETGW_SERVER_BASE_REQUEST_URL = value;
	  }
	  
	  public static String PAYMENT_SERVICE_BASE_REQUEST_URL = "";
		 
	  @Value("${param.ref.payment.service.base.url}")
	  public void setPaymentServiceBaseUrl(String value) {
		  PAYMENT_SERVICE_BASE_REQUEST_URL = value;
	  }
	 
	  public static String WALLETGW_LOGIN_URI = "";
		 
	  @Value("${param.walletgw.login}")
	  public void setLoginUri(String value) {
		  WALLETGW_LOGIN_URI = value;
	  }
	  
	  
	  public static String WALLETGW_CREATE_ORDER_URI = "";
		 
	  @Value("${param.walletgw.create.order}")
	  public void setCreateOrder(String value) {
		  WALLETGW_CREATE_ORDER_URI = value;
	  }
	  
	  
	  public static String WALLETGW_CONFIRM_ORDER_URI = "";
		 
	  @Value("${param.walletgw.confirm.order}")
	  public void setConfirmOrder(String value) {
		  WALLETGW_CONFIRM_ORDER_URI = value;
	  }
	  
	  
	  public static String WALLETGW_CHECK_TRANSACTION_ORDER_URI = "";
		 
	  @Value("${param.walletgw.check.status}")
	  public void setCheckTransaction(String value) {
		  WALLETGW_CHECK_TRANSACTION_ORDER_URI = value;
	  }
	  
	  
	  public static String WALLETGW_RESEND_OTP_URI = "";
		 
	  @Value("${param.walletgw.resend.otp}")
	  public void setResendOtp(String value) {
		  WALLETGW_RESEND_OTP_URI = value;
	  }
	  
	  
	  public static String WALLETGW_REFUND_URI = "";
		 
	  @Value("${param.walletgw.refund}")
	  public void setRefund(String value) {
		  WALLETGW_REFUND_URI = value;
	  }
	  
	  
	  public static String WALLETGW_CONNECTOR_USER_NAME = "";
		 
	  @Value("${walletgw.connector.username}")
	  public void setUserName(String value) {
		  WALLETGW_CONNECTOR_USER_NAME = value;
	  }
	  
	  public static String WALLETGW_CONNECTOR_PASSWORD = "";
		 
	  @Value("${walletgw.connector.password}")
	  public void setPassword(String value) {
		  WALLETGW_CONNECTOR_PASSWORD = value;
	  }
	  
	  public static String PAYMENT_SERVICE_UPDATE_STATUS_URI = "";
		 
	  @Value("${param.ref.payment.service.update.status}")
	  public void setUpdateStatusUri(String value) {
		  PAYMENT_SERVICE_UPDATE_STATUS_URI = value;
	  }
	  
}
