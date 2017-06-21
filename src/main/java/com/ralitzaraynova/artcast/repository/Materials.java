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
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.ralitzaraynova.artcast.model.Category;
import com.ralitzaraynova.artcast.model.Material;
import com.ralitzaraynova.artcast.repository.filter.FilterProperties;
import com.ralitzaraynova.artcast.service.BusinessExeption;
import com.ralitzaraynova.artcast.util.jpa.Transactional;

public class Materials implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Material retain(Material material){
		return manager.merge(material);
	}
	
	public Material byCode(String materialCode){
		try {
			return manager.createQuery("from Material where upper(materialCode)= :materialCode", Material.class)
					.setParameter("materialCode", materialCode.toUpperCase())
					.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
	
	public List<Material> byCategory(Category category){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Material.class);
		if(category != null && category.getId() != null){
			criteria.add(Restrictions.ilike("category", category.getDescription(), MatchMode.ANYWHERE));
		}
		return criteria.list();
	}
	
	public List<Material> filtered(FilterProperties filter){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Material.class);
		
		if (StringUtils.isNotBlank(filter.getMaterialCode())){
			criteria.add(Restrictions.eq("materialCode",filter.getMaterialCode()));
		}
		
		if (StringUtils.isNotBlank(filter.getMaterialName())){
			criteria.add(Restrictions.ilike("materialName", filter.getMaterialName(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("materialName")).list();
	}
	
	public Material byId(Long id){
		return manager.find(Material.class, id);
	}
	
	@Transactional
	public void removeMaterial(Material material){
		try{
			material = byId(material.getId());
			manager.remove(material);
			manager.flush();
		} catch (PersistenceException e){
			throw new BusinessExeption("Материалът не може да бъде изтрит.");
		}
	}

}
