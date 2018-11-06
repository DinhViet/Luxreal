package com.luxury.common;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.luxury.client.impl.SSLClientHttpRequestFactory;


public class CommonUtil {

	private static Logger logger = Logger.getLogger(CommonUtil.class);

	public static <T, K> T sendRequest(String url, K req, Class<T> type) {
		try {
			logger.info("request url : " + url);
			SSLClientHttpRequestFactory ssl = new SSLClientHttpRequestFactory();
			RestTemplate restTemplate = new RestTemplate(ssl);
			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
			headers.add("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
			HttpEntity<K> request = new HttpEntity<K>(req, headers);
			T obj = restTemplate.postForObject(url, request, type);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public static <T, K> T sendGetRequest(String url,Class<T> type) {
		try {
			logger.info("request url : " + url);
			SSLClientHttpRequestFactory ssl = new SSLClientHttpRequestFactory();
			RestTemplate restTemplate = new RestTemplate(ssl);
			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
			headers.add("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
			T obj = restTemplate.getForObject(url, type);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}



}