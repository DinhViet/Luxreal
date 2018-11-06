package com.truemoney.walletgw;
import org.apache.log4j.Logger;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.truemoney.walletgw.common.ErrorMessages;
import com.truemoney.walletgw.common.LoggingUtils;
import com.truemoney.walletgw.contract.base.MainResponse;


@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    private static Logger logger = Logger.getLogger(GlobalDefaultExceptionHandler.class);

    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    @ResponseBody
    public MainResponse processValidationError(MethodArgumentNotValidException e) {
    	MainResponse response = new MainResponse();
        BindingResult bindingResult = e.getBindingResult();
        
//        StringBuffer msgError = new StringBuffer();
//		if (result.hasErrors()) {
//			Object object = result.getFieldError();
//			FieldError fieldError = (FieldError) object;
//			if ("Size".equals(fieldError.getCode())) {
//				msgError.append(ErrorMessage.INVALID_LENGTH.message.replace("{fieldName}",
//						Utils.camelCaseToUnderscore(fieldError.getField())));
//			} else if ("NotNull".equals(fieldError.getCode())) {
//				msgError.append(ErrorMessage.NOT_NULL.message.replace("{fieldName}",
//						Utils.camelCaseToUnderscore(fieldError.getField())));
//			}
//		}
//        Status status = new Status();
//        status.setCode(ErrorMessage.INVALID_PARAM.code);
//        status.setMessage(msgError.toString());
//        logger.info(Utils.objectToJson(status));
//        response.setStatus(status);
//        return response;
        
        StringBuffer msgError = new StringBuffer();
		if(bindingResult.hasErrors()){
			Object object = bindingResult.getFieldError();
			FieldError fieldError = (FieldError) object;
			msgError.append(fieldError.getDefaultMessage());
			response.setRespCode(ErrorMessages.INVALID_PARAM.code);
			response.setDescription(msgError.toString());
			
			logger.info("Response create order from backend  : "+ LoggingUtils.writeObjectAsJson(response));
			return response;
		}
		
		return response;
    }
}