package com.luxury.framework.persistence.dao;

import java.io.Serializable;

import com.luxury.framework.persistence.model.UpdatableDbEntry;

public interface UpdatableDAO<T extends UpdatableDbEntry, ID extends Serializable> extends BaseDAO<T, ID> {
    public T update(T paramT, Long paramLong);

    public T saveOrUpdate(T paramT, Long paramLong);
}