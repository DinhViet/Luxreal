package com.truemoney.framework.persistence.dao;

import com.truemoney.framework.persistence.model.GeneratedIdEntry;

public interface GeneratedIdDAO<T extends GeneratedIdEntry> extends UpdatableDAO<T, Long> {
    public T newInstance();
}