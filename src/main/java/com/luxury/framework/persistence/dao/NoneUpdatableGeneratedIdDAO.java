package com.luxury.framework.persistence.dao;

import com.luxury.framework.persistence.model.NoneUpdatableGeneratedIdEntry;

public interface NoneUpdatableGeneratedIdDAO<T extends NoneUpdatableGeneratedIdEntry> extends BaseDAO<T, Long> {
    public T newInstance();
}