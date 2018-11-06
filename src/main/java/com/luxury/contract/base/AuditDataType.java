package com.luxury.contract.base;

import java.io.Serializable;

public class AuditDataType implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String device;
    protected String deviceId;
    protected String otherDeviceId;
    protected String application;
    protected String applicationVersion;

    public String getDevice() {
	return this.device;
    }

    public void setDevice(String value) {
	this.device = value;
    }

    public String getDeviceId() {
	return this.deviceId;
    }

    public void setDeviceId(String value) {
	this.deviceId = value;
    }

    public String getOtherDeviceId() {
	return this.otherDeviceId;
    }

    public void setOtherDeviceId(String value) {
	this.otherDeviceId = value;
    }

    public String getApplication() {
	return this.application;
    }

    public void setApplication(String value) {
	this.application = value;
    }

    public String getApplicationVersion() {
	return this.applicationVersion;
    }

    public void setApplicationVersion(String value) {
	this.applicationVersion = value;
    }
}