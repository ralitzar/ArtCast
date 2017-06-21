package com.ralitzaraynova.artcast.util.jsf;

import java.io.IOException;
import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ralitzaraynova.artcast.service.BusinessExeption;

public class JsfExeptionHandler extends ExceptionHandlerWrapper{

	private static Log log = LogFactory.getLog(JsfExeptionHandler.class);
	
	private ExceptionHandler wrapped;
	
	public JsfExeptionHandler(ExceptionHandler wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public ExceptionHandler getWrapped() {
		return this.wrapped;
	}
	
	@Override
	public void handle()throws FacesException{
		Iterator<ExceptionQueuedEvent> events = getUnhandledExceptionQueuedEvents().iterator();
		
		while (events.hasNext()){
			ExceptionQueuedEvent event = events.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext)event.getContext();
			
			Throwable exeption = context.getException();
			BusinessExeption businessExeption = getBusinessExeption(exeption);
			
			boolean handled = false;
			
			try{
				if(exeption instanceof ViewExpiredException){
					handled = true;
					redirect("/Login.xhtml");
				}else if(businessExeption != null){
					handled = true;
					FacesUtil.addErrorMessage(businessExeption.getMessage());
				}else {
					handled = true;
					log.error("System error!!!" + exeption.getMessage(), exeption);
//					redirect("/Error.xhtml");
				}
			}finally {
				if(handled){
				events.remove();
				}
			}
		}
		getWrapped().handle();
	}

	private BusinessExeption getBusinessExeption(Throwable exeption) {
		if (exeption instanceof BusinessExeption) {
			return (BusinessExeption) exeption;
		} else if (exeption.getCause() != null) {
			return getBusinessExeption(exeption.getCause());
		}
		return null;
	}

	private void redirect(String page) {
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext();
			String contextPath = externalContext.getRequestContextPath();
	
			externalContext.redirect(contextPath + page);
			facesContext.responseComplete();
		} catch (IOException e) {
			throw new FacesException(e);
		}
	}

}
