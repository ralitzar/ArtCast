package com.ralitzaraynova.artcast.convertor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ralitzaraynova.artcast.model.Work;
import com.ralitzaraynova.artcast.repository.Works;
import com.ralitzaraynova.artcast.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Work.class)
public class WorkConverter implements Converter{
	
	private Works works;

	public WorkConverter() {
		this.works = CDIServiceLocator.getBean(Works.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Work returned = null;
		
		if (value != null && !"".equals(value)){
			Long id = new Long(value);
			returned = works.byId(id);
		}
		return returned;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null){
			Work work = (Work)value;
			return work.getId()== null ? null : work.getId().toString();
		}
		return " ";
	}

}
