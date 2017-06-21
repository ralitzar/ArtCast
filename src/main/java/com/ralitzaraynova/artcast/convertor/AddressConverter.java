package com.ralitzaraynova.artcast.convertor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ralitzaraynova.artcast.model.user.Address;
import com.ralitzaraynova.artcast.repository.Addresses;
import com.ralitzaraynova.artcast.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Address.class)
public class AddressConverter implements Converter{
	
	private Addresses addresses;

	public AddressConverter() {
		this.addresses = CDIServiceLocator.getBean(Addresses.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Address returned = null;
		
		if (value != null){
			Long id = new Long(value);
			returned = addresses.byId(id);
		}
		
	return returned;
	}
	
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null){
			Address address = (Address)value;
			return address.getId() == null ? null : address.getId().toString();
		}
		return "";
	}

}
