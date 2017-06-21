package com.ralitzaraynova.artcast.convertor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import com.ralitzaraynova.artcast.model.Category;
import com.ralitzaraynova.artcast.repository.Categories;
import com.ralitzaraynova.artcast.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Category.class)
public class CategoryConverter implements Converter{
	
	//@Inject
	private Categories categories;

	public CategoryConverter() {
		this.categories = CDIServiceLocator.getBean(Categories.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Category returned = null;
		
		if (value != null){
			Long id = new Long(value);
			returned = categories.byId(id);
		}
		
		return returned;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null){
			Category category = (Category)value;
			return category.getId()== null ? null : category.getId().toString();
		}
		return "";
	}

}
