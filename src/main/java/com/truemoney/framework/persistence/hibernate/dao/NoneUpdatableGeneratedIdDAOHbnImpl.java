package com.truemoney.framework.persistence.hibernate.dao;

import org.hibernate.SessionFactory;
import org.springframework.util.ReflectionUtils;

import com.truemoney.framework.persistence.dao.NoneUpdatableGeneratedIdDAO;
import com.truemoney.framework.persistence.model.NoneUpdatableGeneratedIdEntry;

public abstract class NoneUpdatableGeneratedIdDAOHbnImpl<T extends NoneUpdatableGeneratedIdEntry> extends BaseDAOHbnImpl<T, Long> implements
	NoneUpdatableGeneratedIdDAO<T> {
    public NoneUpdatableGeneratedIdDAOHbnImpl() {
    }

    public NoneUpdatableGeneratedIdDAOHbnImpl(SessionFactory sf) {
	super(sf);
    }

    public T newInstance() {
	try {
	    return ((T) getEntityClass().newInstance());
	} catch (Exception e) {
	    ReflectionUtils.handleReflectionException(e);
	    throw new IllegalStateException("Error creating instance of class " + getEntityClass(), e);
	}
    }
}