package com.luxury.framework.persistence.hibernate.dao;

import java.io.Serializable;
import java.lang.reflect.Field;

import org.apache.commons.lang3.reflect.ConstructorUtils;
import org.hibernate.SessionFactory;
import org.springframework.util.ReflectionUtils;

import com.luxury.framework.persistence.dao.CompositeIdDAO;
import com.luxury.framework.persistence.model.CompositeIdEntry;

public abstract class CompositeIdDAOHbnImpl<T extends CompositeIdEntry<ID>, ID extends Serializable> extends UpdatableDAOHbnImpl<T, ID> implements
	CompositeIdDAO<T, ID> {
    public CompositeIdDAOHbnImpl() {
    }

    public CompositeIdDAOHbnImpl(SessionFactory sf) {
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
	Field idField = ReflectionUtils.findField(CompositeIdEntry.class, "id");

	ReflectionUtils.makeAccessible(idField);
	ReflectionUtils.setField(idField, t, id);
    }
}