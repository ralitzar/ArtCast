package com.ralitzaraynova.artcast.controler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ralitzaraynova.artcast.model.user.Address;
import com.ralitzaraynova.artcast.model.user.Administrator;
import com.ralitzaraynova.artcast.model.user.Author;
import com.ralitzaraynova.artcast.model.user.Client;
import com.ralitzaraynova.artcast.model.user.User;
import com.ralitzaraynova.artcast.model.user.Worker;
import com.ralitzaraynova.artcast.repository.Addresses;
import com.ralitzaraynova.artcast.service.BusinessExeption;
import com.ralitzaraynova.artcast.service.UserRegisterService;
import com.ralitzaraynova.artcast.util.jsf.FacesUtil;
import com.ralitzaraynova.artcast.util.security.MD5;

@Named
@ViewScoped
public class UserRegisterBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UserRegisterService userRegisterService;

	@Inject
	private Addresses addresses;

	private User user;
	private String fullName;
	private String email;
	private String password;
	private String userType;
	private Address userAddress;
	

	private List<Address> userAddreses = new ArrayList<>();

	public UserRegisterBean() {
		cleaning();
	}

	public void initing() {
		if (FacesUtil.isPostback()) {
			this.user.addNewAddress();
		}
	}

	public void cleaning() {
		this.userAddress = new Address();
		this.userType = null;
	}
	
	public User selectUser() {
		this.password = MD5.crypt(password);
		
		switch (userType) {
		case "author":
			this.user = new Author(fullName, email, password);
			break;
		case "client":
			this.user = new Client(fullName, email, password);
			break;
		case "worker":
			this.user = new Worker(fullName, email, password);
			break;
		case "administrator":
			this.user = new Administrator(fullName, email, password);
		}
		return user;
	}
	
	public void more(){
		//TODO complete user registration depend of the UserType
	}

	public void saveMore(){
		//TODO save data completed user registration depend of the UserType
	}
	
	public void saving() throws BusinessExeption {
		selectUser();
		userRegisterService.saveUser(this.user);
		FacesUtil.addInfoMessage("Потребителят е регистриран успешно!");
		}
	
	public void addNewAddress() {
		if(isEdited()){
			userAddress.setUser(this.user);
		}else {
			FacesUtil.addErrorMessage("За да въведете адрес трябва да съществува потребител!");
		}
	}
	
	public void saveAddress(){
			addresses.retain(getUserAddress());
			FacesUtil.addInfoMessage("Адресът на потребителя " + this.user.getFullName()+ " е регистриран успешно!");
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Address getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(Address userAddress) {
		this.userAddress = userAddress;
	}

	public List<Address> getUserAddreses() {
		return userAddreses;
	}

	public void setUserAddreses(List<Address> userAddreses) {
		this.userAddreses = userAddreses;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isAdmin(){
		return this.user != null && (this.user instanceof Administrator);
	}
	
	public boolean isEdited() {
		return this.user != null && this.user.getId() != null;
	}

}
