package com.luxuryservice.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.luxury.common.Utils;

@Service
public class LoggingServiceAdvice implements MethodInterceptor, InitializingBean {
	private static final Logger LOG = Logger.getLogger(LoggingServiceAdvice.class);
	
	@Override
	public void afterPropertiesSet() throws Exception {
		
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object[] arguments = invocation.getArguments();
		Object request;
		if ((arguments != null) && (arguments.length > 0)) {
		    request = arguments[0];
		    LOG.info("Invoke Method: " +  invocation.getThis().getClass().getSimpleName() + "."+ invocation.getMethod().getName() + "("+ request.getClass().getName() +")");
		    LOG.info("Request Data: ");
		    LOG.info(Utils.objectToJson(request));
		} else {
		    request = null;
		    LOG.info("Request is null!");
		}
		
		Object result = null;
		try {
		     result = invocation.proceed();
		     if (result != null) {
		    	 LOG.info("Response Type: " + result.getClass().getName());
		    	 LOG.info("Response Data: ");
				 LOG.info(Utils.objectToJson(result));
		     } else {
		    	 LOG.info("Response is null!");
		     }
		} catch (Throwable e) {
			LOG.error(e);
		}
		 
  	  	return result;
	}

}
