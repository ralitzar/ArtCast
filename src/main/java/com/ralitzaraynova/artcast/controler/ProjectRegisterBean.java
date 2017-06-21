package com.ralitzaraynova.artcast.controler;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import com.ralitzaraynova.artcast.model.Work;
import com.ralitzaraynova.artcast.model.project.Complexity;
import com.ralitzaraynova.artcast.model.project.Project;
import com.ralitzaraynova.artcast.model.project.ProjectItem;
import com.ralitzaraynova.artcast.model.project.ProjectState;
import com.ralitzaraynova.artcast.model.project.SendAddress;
import com.ralitzaraynova.artcast.model.user.Department;
import com.ralitzaraynova.artcast.model.user.User;
import com.ralitzaraynova.artcast.repository.Projects;
import com.ralitzaraynova.artcast.repository.Users;
import com.ralitzaraynova.artcast.repository.Works;
import com.ralitzaraynova.artcast.util.EditedProject;
import com.ralitzaraynova.artcast.util.LoggedIn;
import com.ralitzaraynova.artcast.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ProjectRegisterBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Users users;
	
	@Inject
	@LoggedIn
	private User currentUser;

	@Inject
	private Projects projects;

	@Inject
	private Works works;

	@Produces
	@EditedProject
	private Project project;

	private Work workEditLine;
	private String workCode;


	private List<User> authors;
	private List<User> clients;

	public ProjectRegisterBean() {
		cleaning();
	}

	public void initing() {
		if (FacesUtil.isNotPostback()) {
			this.authors = this.users.authors();
			this.clients = this.users.clients();
			this.project.addNewItemLine();
		}
	}

	public void cleaning() {
		project = new Project();
		project.setAddress(new SendAddress());
	}

	public void saving() {
		this.project.removeItemFirstLine();
		try {
			this.project = this.projects.retain(project);
			FacesUtil.addInfoMessage("Проектът е записан успешно!");
		} finally {
			this.project.addNewItemLine();
		}
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<User> getAuthors() {
		return authors;
	}

	public List<User> getClients() {
		return clients;
	}

	public Work getWorkEditLine() {
		return workEditLine;
	}

	public void setWorkEditLine(Work workEditLine) {
		this.workEditLine = workEditLine;
	}

	public String getWorkCode() {
		return workCode;
	}

	public void setWorkCode(String workCode) {
		this.workCode = workCode;
	}

	public Complexity[] getComplexities() {
		return Complexity.values();
	}

	public Department[] getWorkshops() {
		return Department.values();
	}

	public ProjectState[] getStates() {
		return ProjectState.values();
	}

	public void loadWorkEditLine() {
		ProjectItem item = this.project.getItems().get(0);

		if (this.workEditLine != null) {
			if (this.existItemWork(this.workEditLine)) {
				FacesUtil.addErrorMessage("Вече съществува линия в проекта с избраната дейност!");
			} else {
				item.setWork(this.workEditLine);
				item.setUnit(this.workEditLine.getUnit());
				item.setUnitPrice(this.workEditLine.getUnitPrice());

				this.project.addNewItemLine();
				this.workEditLine = null;
				this.setWorkCode(null);
			}
		}
	}

	public void loadWorkByCode() {
		if (StringUtils.isNotEmpty(this.workCode)) {
			this.workEditLine = this.works.byCode(workCode);
			this.loadWorkEditLine();
		}
	}

	private boolean existItemWork(Work work) {
		boolean existItem = false;
		for (ProjectItem item : this.getProject().getItems()) {
			if (work.equals(item.getWork())) {
				existItem = true;
				break;
			}
		}
		return existItem;
	}

	public void quantityUpdate(ProjectItem projectItem, int line) {
		if (projectItem.getWorkQty() < 0.01) {
			if (line == 0) {
				projectItem.setWorkQty(1f);
			} else {
				this.getProject().getItems().remove(line);
			}
		}this.project.getItemsSubTotal();
	}


	public List<Work> workComplete(String denomination) {
		return this.works.byDenomination(denomination);
	}

	public boolean isEdited() {
		return this.project != null && this.project.getId() != null;
	}

}
