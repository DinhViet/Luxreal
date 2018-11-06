package com.truemoney.walletgw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.truemoney.framework.service.monitor.PerformanceMonitoringAdvice;
import com.truemoney.walletgw.service.advice.LoggingServiceAdvice;

@Configuration
@ComponentScan(basePackages = { "com.truemoney"})
public class AopProxyConfig {

	@Autowired
	private PerformanceMonitoringAdvice performanceMonitoringAdvice;
	
	@Autowired
	private LoggingServiceAdvice loggingAdvice;

}