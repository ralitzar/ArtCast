package com.ralitzaraynova.artcast.controler;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.outjected.email.api.MailMessage;
import com.ralitzaraynova.artcast.util.jsf.FacesUtil;
import com.ralitzaraynova.artcast.util.mail.Mailer;

@Named
@RequestScoped
public class LoginEmailBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Mailer mailer;
	
	private String userEmail;
	
	public void sendPasswordRequest(){
		MailMessage email = mailer.newEmail();
		
		email.to(this.getUserEmail())
		.subject("Вашата заявка за нова парола е приета!")
		.bodyHtml("<strong>Изпратена заявка за нова парола от:</strong>" + this.userEmail)
		.send();
	
		FacesUtil.addInfoMessage("Съобщението Ви бе изпратено успешно!");
	}
	
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

}
