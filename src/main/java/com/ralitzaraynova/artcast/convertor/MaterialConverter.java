package com.ralitzaraynova.artcast.convertor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.ralitzaraynova.artcast.model.Material;
import com.ralitzaraynova.artcast.repository.Materials;
import com.ralitzaraynova.artcast.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Material.class)
public class MaterialConverter implements Converter{
	
	@Inject
	private Materials materials;

	public MaterialConverter() {
		this.materials = CDIServiceLocator.getBean(Materials.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Material returned = null;
		if (StringUtils.isNotEmpty(value)){
			Long id = new Long(value);
			returned = materials.byId(id);
		}
		return returned;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null){
			Material material = (Material)value;
			return material.getId()== null ? null : material.getId().toString();
		}
		return "";
	}

}
