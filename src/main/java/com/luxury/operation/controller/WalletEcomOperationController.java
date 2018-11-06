package com.luxury.operation.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.NotAuthorizedException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luxury.common.LoggingUtils;
import com.luxury.operation.service.IMerchantProfileService;
import com.luxury.operation.service.ITransactionService;
import com.luxury.real.model.CreateMerchantRequest;
import com.luxury.real.model.CreateMerchantResponse;
import com.luxury.real.model.MerchantProfileRequest;
import com.luxury.real.model.MerchantProfileResponse;
import com.luxury.real.model.TransactionHistoryRequest;
import com.luxury.real.model.TransactionResponse;

@RestController
@RequestMapping("/api/v1/operation")
public class WalletEcomOperationController {

	
	@Autowired
	ITransactionService transactionService;
	
	@Autowired
	IMerchantProfileService profileService;
	
	private static Logger logger = Logger
			.getLogger(WalletEcomOperationController.class);
	private final ObjectMapper MAPPER = new ObjectMapper();
	
	
	@RequestMapping(value = "/transaction-history", method = {
			RequestMethod.GET, RequestMethod.POST }, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public TransactionResponse getListTransactionHistory(
			@RequestBody TransactionHistoryRequest request) throws NotAuthorizedException, JsonProcessingException {
		TransactionResponse listTransaction = null;
		
		logger.info("Request = " +MAPPER.writeValueAsString(request));
		try {
			listTransaction = transactionService.getListTransaction(request);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return listTransaction;
	}
	
	@RequestMapping(value = "/create-merchant", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CreateMerchantResponse createMerchant(@RequestBody CreateMerchantRequest request, HttpServletRequest httpServletRequest) {
		CreateMerchantResponse response = null;
		try {
			
			logger.info("Create Merchant  : "+ LoggingUtils.writeObjectAsJson(request));
			
			response = profileService.createMerchant(request);
			
			logger.info("Response create merchant  : "+ LoggingUtils.writeObjectAsJson(response));
			
			return response;
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	
	@RequestMapping(value = "/list-merchant", method = {
			RequestMethod.GET, RequestMethod.POST }, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public MerchantProfileResponse getListMerchant(
			@RequestBody MerchantProfileRequest request) throws NotAuthorizedException, JsonProcessingException {
		MerchantProfileResponse listMerchant = null;
		
		logger.info("Request = " +MAPPER.writeValueAsString(request));
		try {
			listMerchant = profileService.getListMerchantProfile(request);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return listMerchant;
	}
	
	
}
