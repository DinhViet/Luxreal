package com.truemoney.walletgw.common;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class GenerateKey {

	public GenerateKey() {
		
	}
	
	public String generateSignature(String access_key, String msisdn,String content, String secret) {
		String urlParameters = "";
		String signature = "";
		if ((access_key != null)
				&& (msisdn != null) && (content != null)
				&& (secret != null)) {
			urlParameters = "access_key="+access_key+"&msisdn="+msisdn
					+"&content="+content;
			signature = hmacDigest(urlParameters, secret, "HmacSHA256");
			System.out.println("Signature:"+signature);
		}
		return signature;
	}
	
	public String generateSignature(String access_key, String amount ,String orderId, String requestId,String returnUrl,String secretkey) {
		String urlParameters = "";
		String signature = "";
		if ((access_key != null)
				&& (amount != null) && (orderId != null)
				&& (requestId != null) && (returnUrl!=null)) {
			
			 urlParameters = "access_key="+access_key+"&amount="+amount+"&order_id="+orderId+"&request_id="+requestId+"&return_url="+returnUrl;
			 
			signature = hmacDigest(urlParameters, secretkey, "HmacSHA256");
			System.out.println("Signature:"+signature);
		}
		return signature;
	}
	
	
	public String generateSignatures(String access_key, String urlParameters,String secretkey) {
			String signature = "";
			 
			signature = hmacDigest(urlParameters, secretkey, "HmacSHA256");
			
			System.out.println("Signature:"+signature);
		return signature;
	}
	
	
	
	public String generateSignature(String access_key,String content,String secret) {
		String urlParameters = "";
		String signature = "";
		
			urlParameters = "access_key="+access_key+"&content="+content;
			
			signature = hmacDigest(urlParameters, secret, "HmacSHA256");
			
			System.out.println("Signature:"+signature);
			
		return signature;
	}
	
	public static String hmacDigest(String msg, String keyString, String algo) {
		String digest = "";
		try {
			if (keyString != null && keyString.length() > 0) {
				SecretKeySpec key = new SecretKeySpec(
						(keyString).getBytes("UTF-8"), algo);
				Mac mac = Mac.getInstance(algo);
				mac.init(key);
				byte[] bytes = mac.doFinal(msg.getBytes("ASCII"));
				StringBuffer hash = new StringBuffer();
				for (int i = 0; i < bytes.length; i++) {
					String hex = Integer.toHexString(0xFF & bytes[i]);
					if (hex.length() == 1) {
						hash.append('0');
					}
					hash.append(hex);
				}
				digest = hash.toString();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		return digest;
	}
	
	public static String getOrderId(String order){
		Date date = new Date();
		long time = date.getTime();	
		String orderid = order.trim()+time;
		return orderid;
	}
	
	public static void main(String[] args){
		GenerateKey gen = new GenerateKey();
		
		String urlParameters = "access_key="+"k8gc3a2v7onwp7zszwn5"+"&token ="+"435436547542365754xvfdsgdfsyhry4e"+"&amount="+"100000"+"&request_id="+"3q5346436893235346";
		
		String signatureCheck = gen.generateSignatures("k8gc3a2v7onwp7zszwn5",urlParameters, "krnqbhfwwmin4ucys0743ikfp9kg0332");
		
		System.out.println("signatureDFG = " +signatureCheck);
	}

}
