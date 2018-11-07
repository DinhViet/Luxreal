package com.luxury.persistence.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.luxury.framework.persistence.hibernate.dao.GeneratedIdDAOHbnImpl;
import com.luxury.model.GetProductRequest;
import com.luxury.model.LoginRequest;
import com.luxury.persistence.dao.ILuxuryUserDao;
import com.luxury.persistence.model.Product;
import com.luxury.persistence.model.User;

@Repository
public class LuxuryUserDaoImpl extends GeneratedIdDAOHbnImpl<User> implements ILuxuryUserDao {

	@Override
	public boolean createUser(User user) {
		try {
			getSession().save(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateUser(User user) {
		getSession().saveOrUpdate(user);
		return false;
	}

	@Override
	public Class<User> getEntityClass() {
		// TODO Auto-generated method stub
		return User.class;
	}

	@Override
	public User login(LoginRequest request) {
		Criteria criteria = null;
		try {
			criteria = getSession().createCriteria(User.class);
			criteria.add(Restrictions.eq("userName", request.getUserName()));
			criteria.add(Restrictions.eq("passWord", request.getPassWord()));
			return (User) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User getDetail(String token) {
		Criteria criteria = null;
		try {
			criteria = getSession().createCriteria(User.class);
			criteria.add(Restrictions.eq("token", token));
			return (User) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public long checkUserName(String userName) {
		try {
			Query query = getSession().createQuery(
					"select count(*) from User  where userName =:userName");
					query.setString("userName", userName);
				return (Long)query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}
}
