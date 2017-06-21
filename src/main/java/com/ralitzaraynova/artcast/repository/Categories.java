package com.ralitzaraynova.artcast.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.ralitzaraynova.artcast.model.Category;
import com.ralitzaraynova.artcast.repository.filter.FilterProperties;
import com.ralitzaraynova.artcast.service.BusinessExeption;
import com.ralitzaraynova.artcast.util.jpa.Transactional;

public class Categories implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Category retain(Category category){
		return manager.merge(category);
	}
	
	public List<Category> groups(){
		return manager.createQuery("from Category", Category.class).getResultList();
	}
	
	public Category byId(Long id) {
		return manager.find(Category.class, id);
	}
	
	public Category byDescription(String description){
		try{
			return manager.createQuery("from Category where upper (description)= :description", Category.class)
					.setParameter("description", description.toUpperCase())
					.getSingleResult();
		}catch (NoResultException e){
			return null;
		}
	}

	@Transactional
	public void removeCategory(Category category) {
		try{
			category = byId(category.getId());
			manager.remove(category);
			manager.flush();
		}catch (PersistenceException e){
			throw new BusinessExeption("Категорията не може да бъде изтрита!");
		}
	}

	public List<Category> filtered(FilterProperties filter) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Category.class);
		
		if (StringUtils.isNotBlank(filter.getDescription())){
			criteria.add(Restrictions.eq("description",filter.getDescription()));
		}
		return criteria.list();
	}
}
