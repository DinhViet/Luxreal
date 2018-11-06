package com.truemoney.framework.persistence.hibernate.dao;

import java.util.concurrent.atomic.AtomicBoolean;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseSessionDAOHbnImpl {
    @Autowired
    private SessionFactory sessionFactory;
    private AtomicBoolean cacheQueries = new AtomicBoolean(false);

    public BaseSessionDAOHbnImpl() {
    }

    public BaseSessionDAOHbnImpl(SessionFactory sf) {
	setSessionFactory(sf);
    }

    public Session getSession() {
	return getSessionFactory().getCurrentSession();
    }

    public SessionFactory getSessionFactory() {
	return this.sessionFactory;
    }

    public void setSessionFactory(SessionFactory sF) {
	this.sessionFactory = sF;
    }

    public boolean isCacheQueries() {
	return this.cacheQueries.get();
    }

    public void setCacheQueries(boolean cachableQueries) {
	this.cacheQueries.set(cachableQueries);
    }
}