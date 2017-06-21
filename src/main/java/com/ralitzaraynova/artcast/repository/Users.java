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
import org.hibernate.criterion.Restrictions;

import com.ralitzaraynova.artcast.model.user.User;
import com.ralitzaraynova.artcast.repository.filter.FilterProperties;
import com.ralitzaraynova.artcast.service.BusinessExeption;
import com.ralitzaraynova.artcast.util.jpa.Transactional;

public class Users implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	@Transactional
	public User retain(User user) {
		return manager.merge(user);
	}
	
	@Transactional
	public void removeUser(User user){
		try{
			user = byId(user.getId());
			manager.remove(user);
			manager.flush();
		}catch (PersistenceException e) {
			throw new BusinessExeption("Потребителят не може да бъде изтрит!");
			}
	}
	
	@Transactional
	public User byEmail(String email){
		User user = null;
		try{
			user = this.manager.createQuery("From User where lower(email) = :email",User.class)
					.setParameter("email", email.toLowerCase()).getSingleResult();
		} catch (NoResultException e) {
			
		}
		return user;
	}
	
	public List<User> byFullName(String fullName){
		return this.manager.createQuery("From User " + "where upper(fullName)like :fullName",User.class)
				.setParameter("fullName", fullName.toUpperCase() + "%")
				.getResultList();
	}
	
	public User byId(Long id){
		return this.manager.find(User.class, id);
	}
	
	//User List by type

	public List<User> authors(){
		return this.manager.createQuery("From Author ", User.class).getResultList();
	}
	
	public List<User> clients(){
		return this.manager.createQuery("From Client ", User.class).getResultList();
	}
	
	public List<User> administrators(){
		return this.manager.createQuery("From Administrator", User.class).getResultList();
	}
	
	public List<User> workers(){
		return this.manager.createQuery("From Worker", User.class).getResultList();
	}

	public List<User> filtered(FilterProperties filter){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(User.class);
		
		if(StringUtils.isNotBlank(filter.getFullName())){
			criteria.add(Restrictions.ilike("fullName", filter.getFullName(), MatchMode.ANYWHERE));
		}
		
		if(StringUtils.isNotBlank(filter.getEmail())){
			criteria.add(Restrictions.ilike("email", filter.getEmail(), MatchMode.ANYWHERE));
		}
		return criteria.list();
	}
	
}

