package com.ralitzaraynova.artcast.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import com.ralitzaraynova.artcast.model.project.Project;
import com.ralitzaraynova.artcast.model.project.ProjectState;
import com.ralitzaraynova.artcast.repository.Projects;
import com.ralitzaraynova.artcast.util.jpa.Transactional;

public class ProjectRegisterService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Projects projects;
	
	@Transactional
	public Project saveProject(Project project){
		if(project.isNew()){
			project.setDate(new Date());
			project.setState(ProjectState.ОФЕРТА);
		}
		project = this.projects.retain(project);
		return project;
		
		/*Project existingProject = projects.byCode(project.getNumber());
		
		if(existingProject != null && !existingProject.equals(project)){
			throw new BusinessExeption("Проект с посочения код вече съществува!");
		}
		return projects.retain(project);*/
	}
}
