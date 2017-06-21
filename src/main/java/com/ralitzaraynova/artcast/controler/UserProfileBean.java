package com.ralitzaraynova.artcast.controler;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ralitzaraynova.artcast.model.user.User;
import com.ralitzaraynova.artcast.repository.Users;
import com.ralitzaraynova.artcast.util.LoggedIn;
import com.ralitzaraynova.artcast.util.jsf.FacesUtil;
import com.ralitzaraynova.artcast.util.security.MD5;

@Named
@ViewScoped
public class UserProfileBean implements Serializable {
	
	private static final long serialVersionUID = -3212736580137406856L;
	
	@Inject
	@LoggedIn
	private User user;
	
	@Inject
	private Users users;
	
	public void updating() {
		String email = user.getEmail();
		String fullName = user.getFullName();
		String password = user.getPassword();
		
		User u = users.byId(user.getId());
		u.setEmail(email);
		u.setFullName(fullName);
		u.setPassword(MD5.crypt(password));
		
		FacesUtil.addInfoMessage("Вашият профил е променен успешно!");
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
		
	}
	
}
