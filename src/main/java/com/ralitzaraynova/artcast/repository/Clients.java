package com.ralitzaraynova.artcast.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.ralitzaraynova.artcast.model.user.Client;

public class Clients implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Client byId(Long id) {
		return this.manager.find(Client.class, id);
	}
	
	public List<Client> byName(String fullName) {
		return this.manager.createQuery("from Client " +
				"where upper(fullName) like :fullName", Client.class)
				.setParameter("fullName", fullName.toUpperCase() + "%")
				.getResultList();
	}
	
	public List<Client> group(){
		return manager.createQuery("from Client", Client.class).getResultList();
	}

}
