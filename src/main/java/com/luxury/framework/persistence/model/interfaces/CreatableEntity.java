package com.luxury.framework.persistence.model.interfaces;

import java.util.Date;

public interface CreatableEntity {

    public Long getCreator();

    public boolean isSetCreator();

    public void setCreator(Long paramLong);

    public Date getCreationDate();

    public boolean isSetCreationDate();

    public void setCreationDate(Date paramDate);
}