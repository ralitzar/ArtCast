package com.ralitzaraynova.artcast.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.ralitzaraynova.artcast.model.user.Worker;

public class Workers implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Worker retain(Worker worker){
		return manager.merge(worker);
	}
	
	public Worker byEgn(String egn){
		try{
			return manager.createQuery("from Worker where upper(workerId)= :egn", Worker.class)
				.setParameter("workerId", egn.toUpperCase())
				.getSingleResult();
		}catch (NoResultException e){
			return null;
		}
	}
	
	

}
