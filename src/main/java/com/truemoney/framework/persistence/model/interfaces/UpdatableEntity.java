package com.truemoney.framework.persistence.model.interfaces;

import java.util.Date;

public interface UpdatableEntity {

    public Long getLastUpdater();

    public boolean isSetLastUpdater();

    public void setLastUpdater(Long paramLong);

    public Date getLastUpdate();

    public boolean isSetLastUpdate();

    public void setLastUpdate(Date paramDate);
}