package com.truemoney.walletgw.common;

public enum ErrorMessages {
    //
	SUCCESS("00", "SUCCESS"),
	ACCESSKEY_NOT_EXIT("11", "Sai accesskey"),
	SIGNATURE_FAIL("12", "Sai chữ ký điện tử"),
	ORDERID_IS_NOT_FOUNT("13", "OrderId không tồn tại"),
	TIMEOUT("14", "Lỗi kết nối tới nhà cung cấp"),
	INVALID_PARAM("02", "Invalid token"),
	UNKNOW_ERROR("99", "Có lỗi xảy ra"),
	INVALID_AMOUNT("16", "Số tiền không hợp lệ"),
	INVALID_SERVICE("08", "ServiceId không hợp lệ"),
	INVALID_USERNAME_PASSWORD("01","UserName or PassWord không hợp lệ"),
	INVALID_USERNAME("03","UserName đã tồn tại"),
	ORDER_IS_NOT_FOUND("13", "OrderId không tồn tại");

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