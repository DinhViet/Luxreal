package com.truemoney.framework.persistence.hibernate.dao;

import org.hibernate.SessionFactory;
import org.springframework.util.ReflectionUtils;

import com.truemoney.framework.persistence.dao.GeneratedIdDAO;
import com.truemoney.framework.persistence.model.GeneratedIdEntry;

public abstract class GeneratedIdDAOHbnImpl<T extends GeneratedIdEntry> extends UpdatableDAOHbnImpl<T, Long> implements GeneratedIdDAO<T> {
    public GeneratedIdDAOHbnImpl() {
    }

    public GeneratedIdDAOHbnImpl(SessionFactory sf) {
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