package com.luxury.api.controller;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.luxury.common.ErrorMessages;
import com.luxury.common.LoggingUtils;
import com.luxury.contract.base.MainResponse;
import com.luxury.request.UpdateTransactionStatusRequest;
import com.luxury.service.PaymentService;

@Controller
@RequestMapping("api/v1")
public class PaymentServiceRestController {

	private static Logger logger = Logger
			.getLogger(WalletEcomRestController.class);
	

	@Autowired
	PaymentService paymentService;
	
	
	@RequestMapping(value = "/transactions/status", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public MainResponse login( @Valid @RequestBody UpdateTransactionStatusRequest request, HttpServletRequest httpServletRequest) throws Exception {
		MainResponse response = null;
		try {
			
			logger.info("Request login from merchant  : "+ LoggingUtils.writeObjectAsJson(request));
			
			response = paymentService.updateTransactionStatus(request);
			
			logger.info("Response login from backend  : "+ LoggingUtils.writeObjectAsJson(response));
			
			return response;
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			response = new MainResponse();
			response.setRespCode(ErrorMessages.UNKNOW_ERROR.code);
			response.setDescription(ErrorMessages.UNKNOW_ERROR.message);
			return response;
		}
	} 
}