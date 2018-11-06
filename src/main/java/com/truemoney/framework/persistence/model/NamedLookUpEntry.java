package com.truemoney.framework.persistence.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class NamedLookUpEntry extends IdEntry<Integer> {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @Column(name = "STR_NAME", nullable = false, length = 2048)
    private String name;

    @Deprecated
    protected NamedLookUpEntry() {
    }

    protected NamedLookUpEntry(int id) {
	super(Integer.valueOf(id));
    }

    public String getName() {
	return this.name;
    }

    public boolean isSetName() {
	return (this.name != null);
    }

    public void setName(String name) {
	this.name = name;
    }
}