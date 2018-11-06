package com.luxury.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;


public class LoggingUtils {

	private static final Map<String, Integer> MASKED_FIELDS; //field name - number of characters to display
	private static final List<String> HIDE_FIELDS;
	static {
		MASKED_FIELDS = new HashMap<String, Integer>(); 
		HIDE_FIELDS = new ArrayList<String>();
		
		MASKED_FIELDS.put("custmer_Id", 4);
		MASKED_FIELDS.put("secretkey", 4);
		MASKED_FIELDS.put("accesskey", 4);
		MASKED_FIELDS.put("fullName", 4);
		MASKED_FIELDS.put("cardIssueDate", 4);
		MASKED_FIELDS.put("customerId", 4);
		MASKED_FIELDS.put("customer_Name", 4);
		MASKED_FIELDS.put("customerName", 4);
		MASKED_FIELDS.put("subscriptionId", 4);
		MASKED_FIELDS.put("cardNumber", 4);
		MASKED_FIELDS.put("card_Number", 4);
		MASKED_FIELDS.put("accountNumber", 4);
		MASKED_FIELDS.put("ssn", 4);
		MASKED_FIELDS.put("user", 4);
		MASKED_FIELDS.put("info", 4);
		MASKED_FIELDS.put("securityCode", 4);
		MASKED_FIELDS.put("customerInfo", 4);
		MASKED_FIELDS.put("merchant_Id", 4);
		
		HIDE_FIELDS.add("otp");
		HIDE_FIELDS.add("securityToken");
		HIDE_FIELDS.add("OTP");
		HIDE_FIELDS.add("password");
		HIDE_FIELDS.add("security_code");
		HIDE_FIELDS.add("login_id");
		HIDE_FIELDS.add("access_token");
		HIDE_FIELDS.add("securityCode");
		HIDE_FIELDS.add("token");
	}
	
	public static String writeObjectAsJson(Object object) {
		
		if(object!=null){
			
			JSONObject json = new JSONObject(object);
			for (String field : HIDE_FIELDS) {
				Object val = null;
				try {
					val = json.get(field);
				} catch (Exception e){
				}
				if (val!=null && !StringUtils.isBlank(val.toString())) {
					json.put(field, "****");
				}
			}
			
			Iterator it = MASKED_FIELDS.entrySet().iterator();
			while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
		        Object val = null;
		        String field = pair.getKey().toString();
		        int number = Integer.parseInt(pair.getValue().toString());
		        try {
		        	val = json.get(pair.getKey().toString());
		        } catch (Exception e){}
		        String masked = "****";
		        if (val!=null && !StringUtils.isBlank(val.toString())) {
		        	if (val.toString().length()>number) {
		        		masked = masked +val.toString().substring(val.toString().length()-number);
		        	}
		        	json.put(field, masked);
				}
		        
		    }
			
			return json.toString();
	}else 
		return "NULL";
	}
}
