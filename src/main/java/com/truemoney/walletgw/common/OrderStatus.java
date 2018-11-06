package com.truemoney.walletgw.common;
/**
 * 
 * @author 1Pay-05
 * Order status enum
 */
public enum OrderStatus {
	INIT_STATUS(1,"init"),PROCESSING_STATUS(2,"processing"),FINISH_STATUS(3,"finish"),CLOSE_STATUS(4,"close");
	private int code;
	private String name;
	OrderStatus(int code , String name){
		this.code = code;
		this.name = name;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}
}
