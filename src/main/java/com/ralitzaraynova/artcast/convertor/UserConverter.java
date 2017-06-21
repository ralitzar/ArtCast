package com.ralitzaraynova.artcast.convertor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.commons.lang3.StringUtils;

import com.ralitzaraynova.artcast.model.user.User;
import com.ralitzaraynova.artcast.repository.Users;
import com.ralitzaraynova.artcast.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=User.class)
public class UserConverter implements Converter{
	
	//@Inject
	private Users users;

	public UserConverter() {
		this.users = CDIServiceLocator.getBean(Users.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		User returned = null;
		if (StringUtils.isNotEmpty(value)){
			Long id = new Long(value);
			returned = users.byId(id);
		}
		return returned;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null){
			User user = (User)value;
			return user.getId()== null ? null : user.getId().toString();
		}
		return "";
	}

}
