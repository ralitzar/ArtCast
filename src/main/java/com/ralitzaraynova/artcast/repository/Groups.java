package com.ralitzaraynova.artcast.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.ralitzaraynova.artcast.model.user.Group;

public class Groups implements Serializable{

	@Inject
	private EntityManager manager;
	
	private static final long serialVersionUID = 1L;

	public Group byId(Long id) {
		return manager.find(Group.class, id);
	}
	
	public List<Group> groups(){
		return manager.createQuery("from Group", Group.class).getResultList();
	}
	
	public Group byName(String name){
		try{
			return manager.createQuery("from Group where lower (name)= :name", Group.class)
					.setParameter("name", name.toLowerCase())
					.getSingleResult();
		}catch (NoResultException e){
			return null;
		}
	}
}
