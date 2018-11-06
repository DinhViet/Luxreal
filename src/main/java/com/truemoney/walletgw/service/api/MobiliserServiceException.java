package com.truemoney.walletgw.service.api;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class MobiliserServiceException extends RuntimeException {
  private static final long serialVersionUID = -325459087141343181L;
  private final Map<String, String> parameters;

  public MobiliserServiceException() {
    this(Collections.<String, String>emptyMap());
  }

  public MobiliserServiceException(Map<String, String> parameters) {
    this.parameters = Collections.unmodifiableMap(new HashMap<String, String>(parameters));
  }

  public MobiliserServiceException(String message) {
    this(message, Collections.<String, String>emptyMap());
  }

  public MobiliserServiceException(String message, Map<String, String> parameters) {
    super(message);
    this.parameters = Collections.unmodifiableMap(new HashMap<String, String>(parameters));
  }

  public MobiliserServiceException(String message, Throwable cause) {
    this(message, cause, Collections.<String, String>emptyMap());
  }

  public MobiliserServiceException(String message, Throwable cause, Map<String, String> parameters) {
    super(message, cause);
    this.parameters = Collections.unmodifiableMap(new HashMap<String, String>(parameters));
  }

  public MobiliserServiceException(Throwable cause) {
    this(cause, Collections.<String, String>emptyMap());
  }

  public MobiliserServiceException(Throwable cause, Map<String, String> parameters) {
    super(cause);
    this.parameters = Collections.unmodifiableMap(new HashMap<String, String>(parameters));
  }

  public abstract int getErrorCode();
  
  public abstract String getMessageKey();

  public final Map<String, String> getParameters() {
    return this.parameters;
  }
}
