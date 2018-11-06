package com.truemoney.walletgw.service.api;

import java.util.Collections;
import java.util.List;

public final class CallerInformation {
  private final Long actorId;// CustomerID
  private final String actorCif;// CustomerCif
  private final int actorType;// CustomerType
  private final String actorIdentifier;// UserName
  private final List<String> actorPrivs;
  private final String ip;
  private final String userAgent;

  public CallerInformation(Long actorId, String actorCif, int actorType, String actorIdentifier,
      List<String> actorPrivs, String ip, String userAgent) {
    this.actorId = actorId;
    this.actorCif = actorCif;
    this.actorType = actorType;
    this.actorIdentifier = actorIdentifier;
    this.actorPrivs = Collections.unmodifiableList(actorPrivs);
    this.ip = ip;
    this.userAgent = userAgent;
  }

  public Long getActorId() {
    return this.actorId;
  }

  public String getActorCif() {
    return actorCif;
  }

  public int getActorType() {
    return actorType;
  }

  public String getActorIdentifier() {
    return this.actorIdentifier;
  }

  public List<String> getActorPrivs() {
    return this.actorPrivs;
  }

  public String getIp() {
    return this.ip;
  }

  public String getUserAgent() {
    return this.userAgent;
  }

  public boolean hasPrivilege(String privilege) {
    return this.actorPrivs.contains(privilege);
  }
}
