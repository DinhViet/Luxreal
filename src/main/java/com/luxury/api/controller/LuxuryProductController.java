package com.luxury.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
import com.luxury.endpoint.ILuxuryProductService;
import com.luxury.model.CreateProductRequest;
import com.luxury.model.DeleteProductRequest;
import com.luxury.model.ResultResponse;
import com.luxury.model.GetListProductResponse;
import com.luxury.model.GetProductRequest;
import com.luxury.model.Status;
import com.luxury.model.UpdateStatusRequest;

@Controller
@RequestMapping("api/v1")
public class LuxuryProductController {

	
	@Autowired
	ILuxuryProductService productService;
	
	@RequestMapping(value = "/create/product", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResultResponse createUser( @Valid @RequestBody CreateProductRequest request, BindingResult bindingResult,HttpServletRequest httpServletRequest) {
		ResultResponse response = null;
		try {
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
				response = new ResultResponse();
				response.setRespCode(ErrorMessages.INVALID_PARAM.code);
				response.setDescription(msgError.toString());
				return response;
			}
			
			return productService.createProduct(request);
			
		} catch (Exception e) {
			e.printStackTrace();
			response = new ResultResponse();
			response.setRespCode(ErrorMessages.UNKNOW_ERROR.code);
			response.setDescription(ErrorMessages.UNKNOW_ERROR.message);
			return response;
		}
	}
	
	@RequestMapping(value = "/get/product", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public GetListProductResponse createUser( @Valid @RequestBody GetProductRequest request, BindingResult bindingResult,HttpServletRequest httpServletRequest) {
		GetListProductResponse response = null;
		try {
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
				response = new GetListProductResponse();
				Status status = new Status();
				status.setRespCode(ErrorMessages.INVALID_PARAM.code);
				status.setDescription(msgError.toString());
				response.setStatus(status);
				return response;
			}
			
			return productService.getListProduct(request);
			
		} catch (Exception e) {
			e.printStackTrace();
			response = new GetListProductResponse();
			Status status = new Status();
			status.setRespCode(ErrorMessages.INVALID_PARAM.code);
			status.setDescription(ErrorMessages.UNKNOW_ERROR.message);
			response.setStatus(status);
			return response;
		}
	}
	
	
	@RequestMapping(value = "/delete/product", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResultResponse deleteProduct( @Valid @RequestBody DeleteProductRequest request, BindingResult bindingResult,HttpServletRequest httpServletRequest) {
		ResultResponse response = null;
		try {
			
			return productService.DeleteProduct(request);
			
		} catch (Exception e) {
			e.printStackTrace();
			response = new ResultResponse();
			response.setRespCode(ErrorMessages.UNKNOW_ERROR.code);
			response.setDescription(ErrorMessages.UNKNOW_ERROR.message);
			return response;
		}
	}
	
	
	@RequestMapping(value = "/update-status", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Status updateStatus(@RequestBody UpdateStatusRequest request,HttpServletRequest httpServletRequest) {
		Status response = null;
		try {
			
			return productService.updateStatus(request);
			
		} catch (Exception e) {
			e.printStackTrace();
			response = new Status();
			response.setRespCode(ErrorMessages.UNKNOW_ERROR.code);
			response.setDescription(ErrorMessages.UNKNOW_ERROR.message);
			return response;
		}
	}
	
	
}
