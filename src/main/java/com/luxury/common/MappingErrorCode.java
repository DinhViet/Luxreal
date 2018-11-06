package com.luxury.common;

public enum MappingErrorCode {
	SUCCESS("00","00"),
	 NOT_FOUND_ORDERID("02","02"),
	 OTP_OVER("03","03"),
	 OTP_EXPRIE("04","04"),
	 INVALID_OTP("05","05"),
	 INVALID_USER_PASSWORD("06","06"),
	 INVALID_BLANCE("07","07"),
	 OTP_OVER_SEND("09","09"),
	 INVALID_ORDERID_BACKEND("13","10"),
	 TIMEOUT("14","14"),
	 INVALID_PARAM_REQUEST("15","15"),
	 ORDER_NOT_REFUND("16","17"),
	 
	 ERROR_CONFIG("10","18"),
	 OVER_LOGIN("11","19"),
	 ORDER_SUCCESS("12","20"),
	 ORDER_REFUND("17","21"),
	 ORDER_FAIL("18","22"),
	 PAYMNET_KYC("19","23"),
	 LIMIT_AMOUNT_DAY("20","24"),
	 LIMIT_AMOUNT_MONTH("21","25"),
	 LIMIT_TRANSACTION_DAY("22","26"),
	 INVALID_AMOUNT("23","27"),
	 TRANSACTION_EXPRIE("24","28"),
	 CANT_SEND_("25","29"),
	 REFUND_EXPRIE("26","30"),
	 
	 INVALID_TOKEN("98","10"),
	 UNKONW_ERROR("99","99"),
	 ;
	 private String code;
	 private String type;
	 
	 private MappingErrorCode(String code,String type) {
	  this.code = code;
	  this.type = type;
	 }
	 
	 public static String getTypeByCode(String code) {
	    String type= "99";
	    for (MappingErrorCode item : MappingErrorCode.values()) {
	        if (item.code.equals(code)) {
	          type = item.type;
	          break;
	        }
	      }
	    return type;
	   }
}
