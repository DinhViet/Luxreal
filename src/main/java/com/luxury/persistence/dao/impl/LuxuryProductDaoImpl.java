package com.luxury.persistence.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.luxury.framework.persistence.hibernate.dao.GeneratedIdDAOHbnImpl;
import com.luxury.model.DeleteProductRequest;
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
			 
			 if(!StringUtils.isEmpty(request.getBrandName())){
				 criteria.add(Restrictions.ilike("brandName", request.getBrandName(),MatchMode.ANYWHERE));
			 }
			 
			 if(!StringUtils.isEmpty(request.getTag())){
				 criteria.add(Restrictions.ilike("brandName", request.getTag(),MatchMode.ANYWHERE));
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

	@Override
	public boolean deleteProduct(DeleteProductRequest request) {
		String sql = "DELETE FROM Product WHERE id = :productId";
		//Transaction tx = null;
		Session sess = null;
		try {
			sess = getSession();
			//tx = sess.beginTransaction();
			String deleteImage = "DELETE FROM ImageProduct WHERE product = :proId";
			Query query2 = sess.createQuery(deleteImage);
			query2.setParameter("proId",getProductById(request.getProductId()));
			query2.executeUpdate();
			
			Query query = sess.createQuery(sql);
			query.setParameter("productId", Long.parseLong(request.getProductId()));
			query.executeUpdate();
		/*	tx.commit();*/
			return true;
		} catch (Exception e) {
		/*	tx.rollback();*/
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Product getProductById(String productId) {
		Criteria criteria = null;
		try {
			criteria = getSession().createCriteria(Product.class);
			criteria.add(Restrictions.eq("id", Long.parseLong(productId)));
			} catch (Exception e) {
				e.printStackTrace();
		}
		return (Product) criteria.uniqueResult();
	}

	@Override
	public boolean updateProduct(Product product) {
		try {
			getSession().saveOrUpdate(product);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
}
