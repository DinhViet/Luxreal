package com.luxury.framework.persistence.hibernate.dao;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.engine.spi.SessionFactoryImplementor;

import com.luxury.framework.persistence.dao.BaseDAO;
import com.luxury.framework.persistence.dao.PaginationInfo;
import com.luxury.framework.persistence.model.DbEntry;
import com.luxury.framework.persistence.model.interfaces.UpdatableEntity;

public abstract class BaseDAOHbnImpl<T extends DbEntry, ID extends Serializable> extends BaseSessionDAOHbnImpl implements BaseDAO<T, ID> {
    private static final Logger LOG = Logger.getLogger(BaseDAOHbnImpl.class);

    private AtomicBoolean defaultOrderAsc = new AtomicBoolean(true);

    private AtomicReference<String> defaultOrderColumn = new AtomicReference<String>("id");

    public BaseDAOHbnImpl() {
    }

    public BaseDAOHbnImpl(SessionFactory sf) {
	setSessionFactory(sf);
    }

    @SuppressWarnings("unchecked")
    public T getById(ID id) {
	return (T) getSession().get(getEntityClass(), id);
    }

    @SuppressWarnings("unchecked")
    public List<T> getAll() {
	return getBaseCriteria().setCacheable(isCacheQueries()).list();
    }

    public long getCountAll() {
	return getCount(getBaseCriteria());
    }

    public void save(T object, Long creator) {
	addAuditingInformation(object, creator, Boolean.FALSE);

	getSession().save(object);
    }

    public void delete(T object) {
	getSession().delete(object);
    }

    public List<T> getPageOfData(PaginationInfo pageInfo) {
	return getPageOfData(getBaseCriteria(), pageInfo);
    }

    @SuppressWarnings("unchecked")
    public List<T> getPageOfData(Criteria criteria, PaginationInfo pageInfo) {
	return addPaginationInformation(criteria, pageInfo).list();
    }

    public void flush() {
	getSession().flush();
    }

    public boolean lockNoWait(T entity) {
	return lock(entity, LockMode.UPGRADE_NOWAIT);
    }

    @SuppressWarnings("deprecation")
    public boolean lockWait(T entity) {
	return lock(entity, LockMode.UPGRADE);
    }

    @SuppressWarnings("deprecation")
    private boolean lock(T entity, LockMode lockMode) {
	try {
	    getSession().refresh(entity, lockMode);

	    return true;
	} catch (HibernateException he) {
	}

	return false;
    }

    public void evict(T object) {
	getSession().evict(object);
    }

    public void refresh(T object) {
	getSession().refresh(object);
    }

    public Date getDatabaseTimestamp() {
	if (getSessionFactory() instanceof SessionFactoryImplementor) {
	    SessionFactoryImplementor sessionFactoryImpl = (SessionFactoryImplementor) getSessionFactory();

	    if (sessionFactoryImpl.getDialect().supportsCurrentTimestampSelection()) {
		SQLQuery query = getSession().createSQLQuery(sessionFactoryImpl.getDialect().getCurrentTimestampSelectString());
		try {
		    return ((Timestamp) query.uniqueResult());
		} catch (Exception e) {
		    LOG.error("Failed to get Timestamp", e);
		}

	    }

	}

	return new Date();
    }

    protected T addAuditingInformation(T object, Long actor, Boolean update) {
	if ((((update == null) || (update.booleanValue()))) && (UpdatableEntity.class.isAssignableFrom(object.getClass()))) {
	    UpdatableEntity ue = (UpdatableEntity) object;
	    ue.setLastUpdate(new Date());
	    ue.setLastUpdater(actor);
	}

	if ((update == null) || (!(update.booleanValue()))) {
	    if (object.getCreationDate() == null) {
		object.setCreationDate(new Date());
	    }
	    object.setCreator(actor);
	}

	addCustomAuditingInformation(object, actor, update);

	return object;
    }

    protected void addCustomAuditingInformation(T object, Long actor, Boolean update) {
    }

    protected Criteria addPaginationInformation(Criteria criteria, PaginationInfo pageInfo) {
	if (pageInfo == null) {
	    return criteria;
	}

	if ((pageInfo.getOrderColumn() == null) || ("".equals(pageInfo.getOrderColumn().trim()))) {
	    pageInfo.setOrderColumn(getDefaultOrderColumn());
	}

	addOrder(criteria, pageInfo.getOrderColumn(), pageInfo.isOrderAsc());

	if (pageInfo.getFirstRow() != -1) {
	    criteria.setFirstResult(pageInfo.getFirstRow());
	}

	if (pageInfo.getMaxResults() != -1) {
	    criteria.setMaxResults(pageInfo.getMaxResults());
	}

	addAdditionalPaginationInformation(criteria, pageInfo);

	return criteria;
    }

    protected void addAdditionalPaginationInformation(Criteria criteria, PaginationInfo pageInfo) {
    }

    protected Criteria getBaseCriteria() {
	return getSession().createCriteria(getEntityClass());
    }

    protected Criteria addOrder(Criteria criteria, String orderColumn, boolean orderAsc) {
	return criteria.addOrder((orderAsc) ? Order.asc(orderColumn) : Order.desc(orderColumn));
    }

    protected Criteria addDefaultOrder(Criteria criteria) {
	return addOrder(criteria, getDefaultOrderColumn(), isDefaultOrderAsc());
    }

    protected long getCount(Criteria criteria) {
	Long count = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();

	return ((count == null) ? 0L : count.longValue());
    }

    protected String sqlPatternEncode(String in) {
	if (in == null) {
	    return "";
	}

	return StringUtils.replaceChars(in, '*', '%');
    }

    public boolean isDefaultOrderAsc() {
	return this.defaultOrderAsc.get();
    }

    public void setDefaultOrderAsc(boolean defaultOrderAsc) {
	this.defaultOrderAsc.set(defaultOrderAsc);
    }

    public String getDefaultOrderColumn() {
	return ((String) this.defaultOrderColumn.get());
    }

    public void setDefaultOrderColumn(String defaultOrderColumn) {
	this.defaultOrderColumn.set(defaultOrderColumn);
    }
}