package com.ralitzaraynova.artcast.controler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ralitzaraynova.artcast.model.project.Project;
import com.ralitzaraynova.artcast.model.project.ProjectState;
import com.ralitzaraynova.artcast.model.user.Administrator;
import com.ralitzaraynova.artcast.model.user.User;
import com.ralitzaraynova.artcast.repository.Projects;
import com.ralitzaraynova.artcast.repository.filter.ProjectFilterProperties;
import com.ralitzaraynova.artcast.util.LoggedIn;
import com.ralitzaraynova.artcast.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ProjectSearchBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Projects projects;
	
	@Inject
	@LoggedIn
	private User currentUser;
	
	private ProjectFilterProperties filter;
	private Project selectedProject;
	private List<Project> filteredProjects;
	private ProjectState[] states;
	
	public ProjectSearchBean() {
		filter = new ProjectFilterProperties();
		filteredProjects = new ArrayList<>();
	}
	
	public void searching(){
		if ((currentUser != null) && !(currentUser instanceof Administrator)){
			filter.setClientFullName(currentUser.getFullName());
		}
		filteredProjects = projects.filtered(filter);
	}
	
	public void adminSearching(){filteredProjects = projects.filtered(filter);}
	
	public void removing(){
		projects.removeProject(selectedProject);
		filteredProjects.remove(selectedProject);
		
		FacesUtil.addInfoMessage("Дейност " + selectedProject.getNumber() + "бе изтрита успешно!");
	}

	public ProjectFilterProperties getFilter() {
		return filter;
	}

	public List<Project> getFilteredProjects() {
		return filteredProjects;
	}

	public ProjectState[] getStates() {
		return states;
	}

	public Project getSelectedProject() {
		return selectedProject;
	}

	public void setSelectedProject(Project selectedProject) {
		this.selectedProject = selectedProject;
	}
	
}
