package com.luxury.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.luxury.framework.service.monitor.PerformanceMonitoringAdvice;
import com.luxuryservice.advice.LoggingServiceAdvice;

@Configuration
@ComponentScan(basePackages = { "com.truemoney"})
public class AopProxyConfig {

	@Autowired
	private PerformanceMonitoringAdvice performanceMonitoringAdvice;
	
	@Autowired
	private LoggingServiceAdvice loggingAdvice;

}