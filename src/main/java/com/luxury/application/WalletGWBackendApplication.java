package com.luxury.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableAutoConfiguration
@ComponentScan({"com.luxury"})
@SpringBootApplication
public class WalletGWBackendApplication {

	public static void main(String[] args) {
    	SpringApplication.run(WalletGWBackendApplication.class, args);
    }
    
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
      return new PropertySourcesPlaceholderConfigurer();
    }

}
