package com.luxury.service.api;

public interface ICallerUtils {

  public long getCallerId();

  public CallerInformation getCallerInformation();

  public boolean hasPrivilege(String privilege);

}
