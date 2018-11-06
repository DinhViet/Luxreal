package com.truemoney.framework.persistence.hibernate.dao;

import java.io.Serializable;

import org.hibernate.SessionFactory;

import com.truemoney.framework.persistence.dao.UpdatableDAO;
import com.truemoney.framework.persistence.model.UpdatableDbEntry;

public abstract class UpdatableDAOHbnImpl<T extends UpdatableDbEntry, ID extends Serializable> extends BaseDAOHbnImpl<T, ID> implements UpdatableDAO<T, ID> {
    public UpdatableDAOHbnImpl() {
    }

    public UpdatableDAOHbnImpl(SessionFactory sf) {
	super(sf);
    }

    public T update(T object, Long updator) {
	addAuditingInformation(object, updator, Boolean.TRUE);

	getSession().update(object);

	return object;
    }

    public T saveOrUpdate(T object, Long actor) {
	addAuditingInformation(object, actor, null);

	getSession().saveOrUpdate(object);

	return object;
    }
}