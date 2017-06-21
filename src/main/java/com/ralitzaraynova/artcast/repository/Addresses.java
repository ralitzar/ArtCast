package com.ralitzaraynova.artcast.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.ralitzaraynova.artcast.model.user.Address;
import com.ralitzaraynova.artcast.util.jpa.Transactional;

public class Addresses implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	@Transactional
	public Address retain (Address address){
		return manager.merge(address);
	}
	
	public List<Address> byUserId(Long userId){
		return manager.createQuery("from Address where (user) = :userId", Address.class).getResultList();	
	}
	
	public Address byId(Long id){
		return manager.find(Address.class, id);
	}

}
