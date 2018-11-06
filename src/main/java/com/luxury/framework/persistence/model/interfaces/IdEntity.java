package com.luxury.framework.persistence.model.interfaces;

import java.io.Serializable;

public interface IdEntity<ID extends Serializable> {
    public ID getId();

    public boolean isSetId();
}