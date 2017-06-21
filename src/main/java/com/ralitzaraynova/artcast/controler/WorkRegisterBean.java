package com.ralitzaraynova.artcast.controler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import com.ralitzaraynova.artcast.model.Category;
import com.ralitzaraynova.artcast.model.Work;
import com.ralitzaraynova.artcast.model.WorkType;
import com.ralitzaraynova.artcast.repository.Categories;
import com.ralitzaraynova.artcast.repository.Works;
import com.ralitzaraynova.artcast.service.WorkRegisterService;
import com.ralitzaraynova.artcast.util.jsf.FacesUtil;


@Named
@ViewScoped
public class WorkRegisterBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Categories categories;
	
	@Inject
	private WorkRegisterService workRegirsterService;
	
	@Inject
	private Works works;
	
	private Work work;
	private Category workMaterial;
	private WorkType[] workTypes;
	
	private List<Category> categoryGroup;
	
	public WorkRegisterBean(){
		cleaning();
	}
	
	public void initing(){
		if(FacesUtil.isNotPostback()){
			categoryGroup = categories.groups();
			workTypes = WorkType.values();
			}
		}
	
	public void cleaning() {
		work = new Work();
		workMaterial = null;
		categoryGroup = new ArrayList<>();
	}

	public void saving(){
		this.work = workRegirsterService.saveWork(this.work);
		FacesUtil.addInfoMessage("Работата е регистрирана успешно!");
	}
	
	public Work getWork(){
		return work;
	}
	public void setWork(Work work) {
		this.work = work;
		
		if (this.work != null){
			this.workMaterial = this.work.getWorkMaterial();
		}
	}
	
	public WorkType[] getWorkTypes() {
		return workTypes;
	}
	public void setWorkTypes(WorkType[] workTypes) {
		this.workTypes = workTypes;
	}
	
	public List<Category> getCategoryGroup() {
		return categoryGroup;
	}

	public Category getWorkMaterial() {
		return workMaterial;
	}
	
	public void setWorkMaterial(Category workMaterial) {
		this.workMaterial = workMaterial;
	}
	
	public Works getWorks() {
		return works;
	}

	public boolean isEdited() {
		return this.work != null && this.work.getId() != null;
	}

}
