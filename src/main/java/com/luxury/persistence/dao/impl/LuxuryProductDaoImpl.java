package com.luxury.persistence.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.luxury.framework.persistence.hibernate.dao.GeneratedIdDAOHbnImpl;
import com.luxury.model.GetProductRequest;
import com.luxury.persistence.dao.ILuxuryProductDao;
import com.luxury.persistence.model.Product;


@Repository
public class LuxuryProductDaoImpl extends GeneratedIdDAOHbnImpl<Product> implements ILuxuryProductDao {

	@Override
	public boolean createProduct(Product product) {
		try {
			getSession().save(product);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Class<Product> getEntityClass() {
		// TODO Auto-generated method stub
		return Product.class;
	}

	@Override
	public List<Product> getListProduct(GetProductRequest request) {
		Criteria criteria = null;
		try {
			criteria = getSession().createCriteria(Product.class);
			
			 if(!StringUtils.isEmpty(request.getCategory())){
				 criteria.add(Restrictions.ilike("category", request.getCategory(),MatchMode.ANYWHERE));
			 }
			/* if(!StringUtils.isEmpty(request.getFromDate())){
				 criteria.add(Restrictions.ge("requestTime", Utils.getDateFirtDay(request.getFromDate())));
			 }
			 
			 if(!StringUtils.isEmpty(request.getToDate())){
				 criteria.add(Restrictions.le("requestTime",Utils.getDateLastDay(request.getToDate())));
			 }*/
			
			criteria.addOrder(Order.desc("id"));
			 
			criteria.setFirstResult(request.getOffset());
			criteria.setMaxResults(request.getLimit());
			List<Product> listProduct = criteria.list();
			
			return listProduct;
			
		} catch (Exception e) {
				e.printStackTrace();
		}
		return null;
	}
	
}
