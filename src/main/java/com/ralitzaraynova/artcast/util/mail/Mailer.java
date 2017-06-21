package com.ralitzaraynova.artcast.util.mail;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.outjected.email.api.MailMessage;
import com.outjected.email.api.SessionConfig;
import com.outjected.email.impl.MailMessageImpl;

@RequestScoped
public class Mailer implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	SessionConfig config;
	
	public MailMessage newEmail(){
		return new MailMessageImpl(this.config);
	}

}
