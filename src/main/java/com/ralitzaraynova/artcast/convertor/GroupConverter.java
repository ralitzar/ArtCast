package com.ralitzaraynova.artcast.convertor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ralitzaraynova.artcast.model.user.Group;
import com.ralitzaraynova.artcast.repository.Groups;
import com.ralitzaraynova.artcast.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Group.class)
public class GroupConverter implements Converter{
	
	private Groups groups;
	
	public GroupConverter() {
		this.groups = CDIServiceLocator.getBean(Groups.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Group returned = null;
		
		if (value != null){
			Long id = new Long(value);
			returned = groups.byId(id);
		}
		return returned;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null){
			Group group = (Group)value;
			return group.getId()== null ? null : group.getId().toString();
		}
		return "";
	}
}
