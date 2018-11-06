package com.luxury.framework.persistence.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.luxury.framework.persistence.model.interfaces.UpdatableEntity;

@MappedSuperclass
public abstract class UpdatableDbEntry extends DbEntry implements UpdatableEntity {
    private static final long serialVersionUID = 1L;

    @Temporal(TemporalType.TIMESTAMP)
    @Basic(optional = true)
    @Column(name = "LAST_UPDATED_TIME", nullable = true)
    private Date lastUpdate;

    @Basic(optional = true)
    @Column(name = "LAST_UPDATED_ID", nullable = true)
    private Long lastUpdater;

    public Date getLastUpdate() {
	if (this.lastUpdate == null) {
	    return null;
	}
	return new Date(this.lastUpdate.getTime());
    }

    public boolean isSetLastUpdate() {
	return (this.lastUpdate != null);
    }

    public void setLastUpdate(Date lastUpdate) {
	this.lastUpdate = new Date(lastUpdate.getTime());
    }

    public Long getLastUpdater() {
	return this.lastUpdater;
    }

    public boolean isSetLastUpdater() {
	return (this.lastUpdater != null);
    }

    public void setLastUpdater(Long lastUpdater) {
	this.lastUpdater = lastUpdater;
    }
}