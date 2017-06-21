package com.ralitzaraynova.artcast.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.ralitzaraynova.artcast.model.user.Author;


public class Authors implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	public Author byId(Long id) {
		return this.manager.find(Author.class, id);
	}
	
	public List<Author> byName(String fullName) {
		return this.manager.createQuery("from Author " +
				"where upper(fullName) like :fullName", Author.class)
				.setParameter("fullName", fullName.toUpperCase() + "%")
				.getResultList();
	}
}
