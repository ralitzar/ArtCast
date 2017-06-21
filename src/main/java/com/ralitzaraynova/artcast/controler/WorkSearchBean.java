package com.ralitzaraynova.artcast.controler;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ralitzaraynova.artcast.model.Work;
import com.ralitzaraynova.artcast.repository.Works;
import com.ralitzaraynova.artcast.repository.filter.FilterProperties;
import com.ralitzaraynova.artcast.util.jsf.FacesUtil;


@Named
@ViewScoped
public class WorkSearchBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject 
	private Works works;
	
	private FilterProperties filter;
	private Work selectedWork;
	private List<Work> filteredWorks;
	
	public WorkSearchBean() {
		filter = new FilterProperties();
	}
	
	public void removing(){
		works.removeWork(selectedWork);
		filteredWorks.remove(selectedWork);
		
		FacesUtil.addInfoMessage("Дейност " + selectedWork.getWorkCode() + "бе изтрита успешно!");
	}

	public void searching(){
		filteredWorks = works.filtered(filter);
	}
	
	public Work getSelectedWork() {
		return selectedWork;
	}
	public void setSelectedWork(Work selectedWork) {
		this.selectedWork = selectedWork;
	}

	public List<Work> getFilteredWorks() {
		return filteredWorks;
	}

	public FilterProperties getFilter() {
		return filter;
	}
	
	
}
