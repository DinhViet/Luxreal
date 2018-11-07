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
import com.luxury.endpoint.ILuxuryUserService;
import com.luxury.model.CreateUserRequest;
import com.luxury.model.CreateUserResponse;
import com.luxury.model.DetailUserResponse;
import com.luxury.model.LoginRequest;
import com.luxury.model.LoginResponse;
import com.luxury.model.Status;

@Controller
@RequestMapping("api/v1")
public class LuxuryCustomerController {

	private static Logger logger = Logger.getLogger(LuxuryCustomerController.class);
	
	@Autowired
	ILuxuryUserService userService;
	
	@RequestMapping(value = "/create/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CreateUserResponse createUser( @Valid @RequestBody CreateUserRequest request, BindingResult bindingResult,HttpServletRequest httpServletRequest) {
		CreateUserResponse response = null;
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
				response = new CreateUserResponse();
				response.setRespCode(ErrorMessages.INVALID_PARAM.code);
				response.setDescription(msgError.toString());
				
				logger.info("Response login from backend  : "+ LoggingUtils.writeObjectAsJson(response));
				return response;
			}
			
			response = userService.createUser(request);
			
			logger.info("Response login from backend  : "+ LoggingUtils.writeObjectAsJson(response));
			
			return response;
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			response = new CreateUserResponse();
			response.setRespCode(ErrorMessages.UNKNOW_ERROR.code);
			response.setDescription(ErrorMessages.UNKNOW_ERROR.message);
			return response;
		}
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public LoginResponse login( @Valid @RequestBody LoginRequest request, BindingResult bindingResult,HttpServletRequest httpServletRequest) {
		LoginResponse response = null;
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
				response = new LoginResponse();
				response.setRespCode(ErrorMessages.INVALID_PARAM.code);
				response.setDescription(msgError.toString());
				
				logger.info("Response login from backend  : "+ LoggingUtils.writeObjectAsJson(response));
				return response;
			}
			
			response = userService.login(request);
			
			logger.info("Response login from backend  : "+ LoggingUtils.writeObjectAsJson(response));
			
			return response;
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			response = new LoginResponse();
			response.setRespCode(ErrorMessages.UNKNOW_ERROR.code);
			response.setDescription(ErrorMessages.UNKNOW_ERROR.message);
			return response;
		}
	}
	
	@RequestMapping(value = "/getDetailUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public DetailUserResponse getUser(@RequestBody LoginRequest request,HttpServletRequest httpServletRequest) {
		DetailUserResponse response = null;
		try {
			
			logger.info("Request login from merchant  : "+ LoggingUtils.writeObjectAsJson(request));
			
			response = userService.getDetail(request);
			
			logger.info("Response login from backend  : "+ LoggingUtils.writeObjectAsJson(response));
			
			return response;
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			response = new DetailUserResponse();
			Status status = new Status();
			status.setRespCode(ErrorMessages.UNKNOW_ERROR.code);
			status.setDescription(ErrorMessages.UNKNOW_ERROR.message);
			response.setStatus(status);;
			return response;
		}
	}
	
	
}
