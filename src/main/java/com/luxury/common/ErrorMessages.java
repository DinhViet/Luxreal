package com.luxury.common;

public enum ErrorMessages {
    //
	SUCCESS("00", "SUCCESS"),
	SIGNATURE_FAIL("12", "Sai chữ ký điện tử"),
	ORDERID_IS_NOT_FOUNT("13", "OrderId không tồn tại"),
	TIMEOUT("14", "Lỗi kết nối tới nhà cung cấp"),
	INVALID_PARAM("02", "Invalid param request"),
	UNKNOW_ERROR("99", "Có lỗi xảy ra"),
	INVALID_AMOUNT("16", "Số tiền không hợp lệ"),
	INVALID_TOKEN("05", "Token không hợp lệ"),
	INVALID_SERVICE("08", "ServiceId không hợp lệ"),
	INVALID_USERNAME_PASSWORD("01","UserName or PassWord không hợp lệ"),
	INVALID_TOKEN_OR_PASSWORD("04","Token or password không hợp lệ"),
	INVALID_USERNAME_EMAIL("06","UserName or Mail không hợp lệ"),
	INVALID_USERNAME("03","UserName or Mail đã tồn tại");

    public String message;
    public String code;

    ErrorMessages(String code, String message) {
	this.code = code;
	this.message = message;
    }

    public static ErrorMessages parse(String code) {
	ErrorMessages status = null; // Default
	for (ErrorMessages item : ErrorMessages.values()) {
	    if (item.code == code) {
		status = item;
		break;
	    }
	}
	return status;
    }

}
