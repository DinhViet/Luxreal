package com.luxury.contract.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MobiliserResponseType implements Serializable {
    private static final long serialVersionUID = 1L;

    protected List<KeyValue> unstructuredData;

    protected Status status;

    protected String conversationId;

    public List<KeyValue> getUnstructuredData() {
	if (this.unstructuredData == null) {
	    this.unstructuredData = new ArrayList<KeyValue>();
	}
	return this.unstructuredData;
    }

    public Status getStatus() {
	return this.status;
    }

    public void setStatus(Status value) {
	this.status = value;
    }

    public String getConversationId() {
	return this.conversationId;
    }

    public void setConversationId(String value) {
	this.conversationId = value;
    }

    public static class Status implements Serializable {
	private static final long serialVersionUID = 1L;

	protected String value;

	protected int code;

	public String getValue() {
	    return this.value;
	}

	public void setValue(String value) {
	    this.value = value;
	}

	public int getCode() {
	    return this.code;
	}

	public void setCode(int value) {
	    this.code = value;
	}
    }
}