package com.truemoney.framework.persistence.dao;

import java.io.Serializable;

import com.truemoney.framework.persistence.model.CompositeIdEntry;

public interface CompositeIdDAO<T extends CompositeIdEntry<ID>, ID extends Serializable> extends UpdatableDAO<T, ID> {
    public T newInstance(ID paramID);
}