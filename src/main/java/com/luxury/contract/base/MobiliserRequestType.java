package com.luxury.contract.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MobiliserRequestType implements Serializable {
    private static final long serialVersionUID = 1L;

    protected AuditDataType auditData;

    protected List<KeyValue> unstructuredData;

    protected String callback;

    protected String origin;

    protected String traceNo;

    protected Boolean repeat;

    protected String conversationId;

    protected String sessionId;

    public AuditDataType getAuditData() {
	return this.auditData;
    }

    public void setAuditData(AuditDataType value) {
	this.auditData = value;
    }

    public List<KeyValue> getUnstructuredData() {
	if (this.unstructuredData == null) {
	    this.unstructuredData = new ArrayList<KeyValue>();
	}
	return this.unstructuredData;
    }

    public String getCallback() {
	return this.callback;
    }

    public void setCallback(String value) {
	this.callback = value;
    }

    public String getOrigin() {
	return this.origin;
    }

    public void setOrigin(String value) {
	this.origin = value;
    }

    public String getTraceNo() {
	return this.traceNo;
    }

    public void setTraceNo(String value) {
	this.traceNo = value;
    }

    public boolean isRepeat() {
	if (this.repeat == null) {
	    return false;
	}
	return this.repeat.booleanValue();
    }

    public void setRepeat(Boolean value) {
	this.repeat = value;
    }

    public String getConversationId() {
	return this.conversationId;
    }

    public void setConversationId(String value) {
	this.conversationId = value;
    }

    public String getSessionId() {
	return this.sessionId;
    }

    public void setSessionId(String value) {
	this.sessionId = value;
    }
}