package com.truemoney.walletgw.ecom.logic.impl;

import org.apache.log4j.Logger;

import com.truemoney.walletgw.SharedConstants;
import com.truemoney.walletgw.client.IWalletEcomClient;
import com.truemoney.walletgw.common.ErrorMessages;
import com.truemoney.walletgw.common.LoggingUtils;
import com.truemoney.walletgw.common.Utils;
import com.truemoney.walletgw.ecom.logic.IPaymentCheckService;
import com.truemoney.walletgw.ecom.model.WalletGWCheckTransactionBody;
import com.truemoney.walletgw.ecom.model.WalletGWCheckTransactionRequest;
import com.truemoney.walletgw.ecom.model.WalletGWCheckTransactionResponse;
import com.truemoney.walletgw.ecom.model.WalletGWStatus;
import com.truemoney.walletgw.persistence.dao.ITransactionDAO;
import com.truemoney.walletgw.persistence.model.Transactions;

public class PaymentOrderCheckService implements Runnable,IPaymentCheckService{
	
	private static Logger logger = Logger
			.getLogger(PaymentOrderCheckService.class);
	
	private String orderId;
	
	private String requestId;
	
	private String merchantOrderId;
	
	
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	private IWalletEcomClient client;
	
	private	ITransactionDAO transactionDao;
	
	@Override
	public void run() {

		CheckTransaction();
		
	}
	public String getOrderId() {
		return orderId;
	}

	public ITransactionDAO getTransactionDao() {
		return transactionDao;
	}
	public void setTransactionDao(ITransactionDAO transactionDao) {
		this.transactionDao = transactionDao;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public IWalletEcomClient getClient() {
		return client;
	}
	public void setClient(IWalletEcomClient client) {
		this.client = client;
	}
	
	public String getMerchantOrderId() {
		return merchantOrderId;
	}
	public void setMerchantOrderId(String merchantOrderId) {
		this.merchantOrderId = merchantOrderId;
	}
	public void CheckTransaction(){
		try {
			
			WalletGWCheckTransactionRequest requestGW = new WalletGWCheckTransactionRequest();  
			requestGW.setOrder_id(orderId);
			requestGW.setRequest_id(requestId);
			
			logger.info("Request check timeout to walletgw  : "+ LoggingUtils.writeObjectAsJson(requestGW));
			
			WalletGWCheckTransactionResponse responseGW = client.postToHTTPSAddress(SharedConstants.WALLETGW_SERVER_BASE_REQUEST_URL+SharedConstants.WALLETGW_CHECK_TRANSACTION_ORDER_URI, requestGW, WalletGWCheckTransactionResponse.class);
			
			logger.info("Response check timeout  from walletgw  : "+ LoggingUtils.writeObjectAsJson(responseGW));
			
			if(responseGW!=null){
				WalletGWStatus status = responseGW.getStatus();
				
				if(status!=null){
					if(ErrorMessages.SUCCESS.code.equals(status.getCode())){
						WalletGWCheckTransactionBody data = responseGW.getData();
						if(data!=null){
							if("EXECUTE".equalsIgnoreCase(data.getStatus())){
								Transactions transaction = transactionDao.getTransactionOrderId(merchantOrderId);
								if(transaction!=null){
									transaction.setStatus(Transactions.STATUS_SUCCESS);
									transactionDao.update(transaction);
								}
							}
						}
						
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	

}
