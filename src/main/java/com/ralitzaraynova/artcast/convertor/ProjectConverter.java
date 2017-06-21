package com.ralitzaraynova.artcast.convertor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import com.ralitzaraynova.artcast.model.project.Project;
import com.ralitzaraynova.artcast.repository.Projects;
import com.ralitzaraynova.artcast.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Project.class)
public class ProjectConverter implements Converter{
	
	private Projects projects;
	
	public ProjectConverter(){
		this.projects = CDIServiceLocator.getBean(Projects.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Project returned = null;
		
		if (value != null && !"".equals(value)){
			Long id = new Long(value);
			returned = projects.byId(id);
		}
		return returned;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null){
			Project project = (Project)value;
			return project.getId()== null ? null : project.getId().toString();
		}
		return " ";
	}

}
