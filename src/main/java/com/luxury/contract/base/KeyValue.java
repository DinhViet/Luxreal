package com.luxury.contract.base;

import java.io.Serializable;

@SuppressWarnings("serial")
public class KeyValue implements Serializable {
    protected String key;
    protected String value;

    public KeyValue() {
    }

    public KeyValue(String key, String value) {
	super();
	this.key = key;
	this.value = value;
    }

    public String getKey() {
	return key;
    }

    public void setKey(String key) {
	this.key = key;
    }

    public String getValue() {
	return value;
    }

    public void setValue(String value) {
	this.value = value;
    }
}
