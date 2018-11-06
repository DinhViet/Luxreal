package com.luxury.framework.persistence.dao;

import java.io.Serializable;

import com.luxury.framework.persistence.model.IdEntry;

public interface IdDAO<T extends IdEntry<ID>, ID extends Serializable> extends UpdatableDAO<T, ID> {
    public T newInstance(ID paramID);
}