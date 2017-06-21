package com.ralitzaraynova.artcast.model.user;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("administrator")
public class Administrator extends User{

	private static final long serialVersionUID = 1L;
	
	public Administrator(){
		
	}
	
public Administrator(String fullName, String email, String password){
	this.fullName = fullName;
	this.email = email;
	this.password = password;
	this.type = Type.ADMIN;
	}

}
