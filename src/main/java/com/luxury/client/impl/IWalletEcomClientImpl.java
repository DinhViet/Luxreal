package com.luxury.client.impl;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.luxury.application.SharedConstants;
import com.luxury.client.IWalletEcomClient;
import com.luxury.common.BasicAuthInterceptor;

@Service
public class IWalletEcomClientImpl implements IWalletEcomClient {

	private final Logger LOGGER = Logger.getLogger(getClass());
	
	
	public HttpClient getHttpClient() throws NoSuchAlgorithmException, KeyManagementException {
	    SSLContext sslContext = SSLContexts.custom().build();
	    SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext,
	            new String[]{"TLSv1.2", "TLSv1.1", "TLSv1"}, null, SSLConnectionSocketFactory.getDefaultHostnameVerifier());
	    return HttpClients.custom()
	            .setSSLSocketFactory(sslConnectionSocketFactory)
	            .build();
	}

	@Override
	public <T, K> T postToHTTPSAddress(String url, K req, Class<T> type) throws Exception {
		
			SSLClientHttpRequestFactory ssl = new SSLClientHttpRequestFactory();
			ssl.setReadTimeout(60000);
			ssl.setConnectTimeout(60000);
			RestTemplate restTemplate = new RestTemplate(ssl);
			
			try {
			
				String user = SharedConstants.WALLETGW_CONNECTOR_USER_NAME;
				String password = SharedConstants.WALLETGW_CONNECTOR_PASSWORD;
				
				final List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
			       interceptors.add( new BasicAuthInterceptor(user, password) );
		 
			    restTemplate.setInterceptors(interceptors);
				
				HttpHeaders headers = new HttpHeaders();
				headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
				headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
				
				LOGGER.info("Request URL: " + url);
				HttpEntity<K> request = new HttpEntity<K>(req, headers);
				
				T obj = restTemplate.postForObject(url, request, type);
				return obj;

			} catch (Exception e) {
				LOGGER.error(e);
			}
			
			return null;
	}


}
