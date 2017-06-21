package com.ralitzaraynova.artcast.controler;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ralitzaraynova.artcast.model.user.User;
import com.ralitzaraynova.artcast.repository.Users;
import com.ralitzaraynova.artcast.repository.filter.FilterProperties;
import com.ralitzaraynova.artcast.util.jsf.FacesUtil;


@Named
@ViewScoped
public class UserSearchBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject 
	private Users users;
	
	private FilterProperties filter;
	private User selectedUser;
	private List<User> filteredUsers;
	
	public UserSearchBean() {
		filter = new FilterProperties();
	}
	
	public void removing(){
		users.removeUser(selectedUser);
		filteredUsers.remove(selectedUser);
		
		FacesUtil.addInfoMessage("Потребителят " + selectedUser.getFullName() + "бе изтрит успешно!");
	}

	public void searching(){
		filteredUsers = users.filtered(filter);
	}
	
	public User getSelectedUser() {
		return selectedUser;
	}
	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

	public List<User> getFilteredUsers() {
		return filteredUsers;
	}

	public FilterProperties getFilter() {
		return filter;
	}
	
}
