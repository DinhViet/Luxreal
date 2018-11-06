package com.luxury.framework.persistence.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.luxury.framework.persistence.model.DbEntry;

public interface BaseDAO<T extends DbEntry, ID extends Serializable> {
    public Class<T> getEntityClass();

    public T getById(ID id);

    public List<T> getAll();

    public long getCountAll();

    public void save(T entity, Long callerId);

    public void delete(T entity);

    public List<T> getPageOfData(PaginationInfo paramPaginationInfo);

    public void flush();

    public boolean lockWait(T entity);

    public boolean lockNoWait(T entity);

    public void evict(T entity);

    public void refresh(T entity);

    public Date getDatabaseTimestamp();
}