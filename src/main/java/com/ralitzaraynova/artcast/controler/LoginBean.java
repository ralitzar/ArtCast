package com.ralitzaraynova.artcast.controler;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.ralitzaraynova.artcast.model.user.Administrator;
import com.ralitzaraynova.artcast.model.user.User;
import com.ralitzaraynova.artcast.repository.Users;
import com.ralitzaraynova.artcast.util.LoggedIn;
import com.ralitzaraynova.artcast.util.security.MD5;

@Named
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = -7708648536360942173L;

	@Inject
	private Users users;

	private String email;
	private String password;
	 private boolean loggedIn;

	private User user;

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

	public String login() {
		FacesContext context = FacesContext.getCurrentInstance();

		User u = users.byEmail(email);

		if (u != null) {
			String pass = u.getPassword();
			if (pass != null && pass.equals(MD5.crypt(password))){
				loggedIn = true;
				this.user = u;
				
				if (user instanceof Administrator) {
					return "/AdminHome?faces-redirect=true";
				} else {
					return "/Home?faces-redirect=true";
				}
			}
		}
		FacesMessage message = new FacesMessage("Невалидна комбинация е-майл/парола!");
		message.setSeverity(FacesMessage.SEVERITY_ERROR);
		context.addMessage(null, message);

		return null;
	}
	
	public String logout(){
		FacesContext context = FacesContext.getCurrentInstance();
		loggedIn = false;
		context.getExternalContext().invalidateSession();
		return "/Login?faces-redirect=true";
	}
	
	  public boolean isLoggedIn() {
	        return loggedIn;
	    }
	    public void setLoggedIn(boolean loggedIn) {
	        this.loggedIn = loggedIn;
	    }

	@Produces
	@LoggedIn
	@SessionScoped
	public User getUser() {
		return user;
	}
}
