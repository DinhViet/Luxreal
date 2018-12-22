package com.luxury.api.controller;

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

import com.luxury.common.ErrorMessages;
import com.luxury.common.LoggingUtils;
import com.luxury.endpoint.ICallTrackerService;
import com.luxury.model.CallTrackerRequest;
import com.luxury.model.LoginRequest;
import com.luxury.model.LoginResponse;
import com.luxury.model.Status;

@Controller
@RequestMapping("api/v1")
public class CallTrackerController {

	private static Logger logger = Logger.getLogger(CallTrackerController.class);
	
	@Autowired
	ICallTrackerService callTrackerService;
	
	@RequestMapping(value = "/call-tracker", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Status callTracker(@RequestBody CallTrackerRequest request,HttpServletRequest httpServletRequest) {
		Status response = null;
		try {
			
			logger.info("call tracker : "+ LoggingUtils.writeObjectAsJson(request));
			
			return callTrackerService.callTracker(request);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			response = new Status();
			response.setRespCode(ErrorMessages.UNKNOW_ERROR.code);
			response.setDescription(ErrorMessages.UNKNOW_ERROR.message);
			return response;
		}
	}
	
	
}
