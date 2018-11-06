package com.luxury.framework.persistence.dao;

import java.io.Serializable;

import com.luxury.framework.persistence.model.CompositeIdEntry;

public interface CompositeIdDAO<T extends CompositeIdEntry<ID>, ID extends Serializable> extends UpdatableDAO<T, ID> {
    public T newInstance(ID paramID);
}