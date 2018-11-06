package com.truemoney.walletgw.service.api;

public interface IMobiliserExceptionTranslator {
    
    public TranslationResult translate(Throwable paramThrowable, Long paramLong);

    public int getDefaultErrorCode();

    public static class TranslationResult {
	public final int returnCode;
	public final String message;

	public TranslationResult(String message, int returnCode) {
	    this.message = message;
	    this.returnCode = returnCode;
	}
    }
}