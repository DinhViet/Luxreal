package com.luxury.framework.persistence.dao;

import com.luxury.framework.persistence.model.GeneratedIdEntry;

public interface GeneratedIdDAO<T extends GeneratedIdEntry> extends UpdatableDAO<T, Long> {
    public T newInstance();
}