package com.luxury.common;
/**
 * 
 * @author 1Pay-05
 * Payment type enum
 */
public enum PaymentType {
	ATM(1,"ATM"),VISA(2,"VISA");
	private int code;
	private String name;
	PaymentType(int code,String name){
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
