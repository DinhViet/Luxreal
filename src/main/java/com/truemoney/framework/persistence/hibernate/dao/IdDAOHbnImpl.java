package com.truemoney.framework.persistence.hibernate.dao;

import java.io.Serializable;
import java.lang.reflect.Field;

import org.apache.commons.lang3.reflect.ConstructorUtils;
import org.hibernate.SessionFactory;
import org.springframework.util.ReflectionUtils;

import com.truemoney.framework.persistence.dao.IdDAO;
import com.truemoney.framework.persistence.model.IdEntry;

public abstract class IdDAOHbnImpl<T extends IdEntry<ID>, ID extends Serializable> extends UpdatableDAOHbnImpl<T, ID> implements IdDAO<T, ID> {
    public IdDAOHbnImpl() {
    }

    public IdDAOHbnImpl(SessionFactory sf) {
	super(sf);
    }

    public T newInstance(ID id) {
	try {
	    T instance = (T) ConstructorUtils.invokeConstructor(getEntityClass(), id);

	    return instance;
	} catch (NoSuchMethodException e) {
	    throw new IllegalArgumentException("No constructor for ID class " + id.getClass() + " found!");
	} catch (Exception e) {
	    ReflectionUtils.handleReflectionException(e);
	    throw new IllegalStateException("Error creating instance of class " + getEntityClass(), e);
	}
    }

    protected final void setId(T t, ID id) {
	Field idField = ReflectionUtils.findField(IdEntry.class, "id");
	ReflectionUtils.makeAccessible(idField);
	ReflectionUtils.setField(idField, t, id);
    }
}