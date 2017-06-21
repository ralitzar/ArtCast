package com.ralitzaraynova.artcast.controler;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.outjected.email.api.MailMessage;
import com.outjected.email.impl.templating.velocity.VelocityTemplate;

import com.ralitzaraynova.artcast.model.project.Project;
import com.ralitzaraynova.artcast.util.EditedProject;
import com.ralitzaraynova.artcast.util.jsf.FacesUtil;
import com.ralitzaraynova.artcast.util.mail.Mailer;

@Named
@RequestScoped
public class ProjectEmailSendBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Mailer mailer;
	
	@Inject	
	@EditedProject
	private Project project;
	
	public void sendProject(){
		MailMessage email = mailer.newEmail();
		
		email.to(this.project.getClient().getEmail())
			.subject("Вашата заявка №" + this.project.getNumber()+ "за изработка в Марковстудио ООД!")
			.bodyHtml(new VelocityTemplate(getClass().getResourceAsStream("/emails/projectEmail.template")))
			.put("project", this.project)
			.send();
		
		FacesUtil.addInfoMessage("Съобщението Ви бе изпратено успешно!");
	}

}
