package com.ralitzaraynova.artcast.service;

import java.io.Serializable;
import javax.inject.Inject;

import com.ralitzaraynova.artcast.model.user.User;
import com.ralitzaraynova.artcast.repository.Users;
import com.ralitzaraynova.artcast.util.jpa.Transactional;

public class UserRegisterService implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private Users users;
	
	@Transactional
	public User saveUser(User user){
		User existingUser = users.byEmail(user.getEmail());
		
		if(existingUser != null){
			throw new BusinessExeption("Поребител с посочения е-майл вече съществува!");
		}
		return users.retain(user);
	}

	@Transactional
	public User updateUser(User  user){
		return users.retain(user);
	}
}
