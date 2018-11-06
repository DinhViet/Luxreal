package com.luxury.common;

import org.apache.commons.lang3.RandomStringUtils;


public class GeneratorUtil {

	private static String CHARACTERS = "0123456789abcdefghijklmnopqrstuvwxyz";

	public static String generateSalt() {
		return RandomStringUtils.random(32, CHARACTERS);
	}

	public static String generateAccessKey() {
		return RandomStringUtils.random(20, CHARACTERS);
	}

	public static String generateSecret() {
		return RandomStringUtils.random(32, CHARACTERS);
	}

	public static String getAccountHashedPassword(String password, String salt) {
		String hashedPassword = Utils.encryptMD5(password + salt);
		return hashedPassword;
	}
	
	public static String generateProductCode() {
		String pcode = "APP_" + RandomStringUtils.random(12, CHARACTERS);
		return pcode;
	}
	
	
}