package com.truemoney.walletgw.client;

public interface IWalletEcomClient {
	
	public <T, K> T postToHTTPSAddress(String url, K req, Class<T> type) throws Exception;
	
}
