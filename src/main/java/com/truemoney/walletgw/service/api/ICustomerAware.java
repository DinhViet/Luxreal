package com.truemoney.walletgw.service.api;

public interface ICustomerAware {

  public long getCustomerId();

  public String getCustomerCif();

  public String getUsername();

  public int getCustomerTypeId();

}
