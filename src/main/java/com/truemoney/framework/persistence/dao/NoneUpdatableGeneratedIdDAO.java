package com.truemoney.framework.persistence.dao;

import com.truemoney.framework.persistence.model.NoneUpdatableGeneratedIdEntry;

public interface NoneUpdatableGeneratedIdDAO<T extends NoneUpdatableGeneratedIdEntry> extends BaseDAO<T, Long> {
    public T newInstance();
}