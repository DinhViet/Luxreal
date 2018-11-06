package com.truemoney.framework.persistence.dao;

import java.io.Serializable;

import com.truemoney.framework.persistence.model.IdEntry;

public interface IdDAO<T extends IdEntry<ID>, ID extends Serializable> extends UpdatableDAO<T, ID> {
    public T newInstance(ID paramID);
}