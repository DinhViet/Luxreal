package com.luxury.persistence.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.luxury.framework.persistence.hibernate.dao.GeneratedIdDAOHbnImpl;
import com.luxury.model.LoginRequest;
import com.luxury.persistence.dao.ILuxuryUserDao;
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
		try {
			getSession().saveOrUpdate(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	@Override
	public long checkMail(String mail) {
		try {
			Query query = getSession().createQuery(
					"select count(*) from User  where mail =:mail");
					query.setString("mail", mail);
				return (Long)query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public User loginEmail(LoginRequest request) {
		Criteria criteria = null;
		try {
			criteria = getSession().createCriteria(User.class);
			criteria.add(Restrictions.eq("mail", request.getUserName()));
			criteria.add(Restrictions.eq("passWord", request.getPassWord()));
			return (User) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User getDetail(String token, String passWord) {
		Criteria criteria = null;
		try {
			criteria = getSession().createCriteria(User.class);
			criteria.add(Restrictions.eq("token", token));
			criteria.add(Restrictions.eq("passWord", passWord));
			return (User) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User getDetailbyUserName(String userName) {
		Criteria criteria = null;
		try {
			criteria = getSession().createCriteria(User.class);
			criteria.add(Restrictions.eq("userName", userName));
			return (User) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
