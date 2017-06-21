package com.ralitzaraynova.artcast.repository.filter;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.ralitzaraynova.artcast.model.project.Complexity;
import com.ralitzaraynova.artcast.model.project.ProjectState;

public class ProjectFilterProperties implements Serializable{

	private static final long serialVersionUID = 1L;
	
		private String number;
		private String name;
		private LocalDateTime date;
		private LocalDateTime dueDate;
		private String clientFullName;
		private String authorFullName;
		private String serie;
		private ProjectState state;
		
		private ProjectState[] states;
		private Complexity[] complexities;

		public String getNumber() {
			return number;
		}
		public void setNumber(String number) {
			this.number = number == null ? null : number.toUpperCase();
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public LocalDateTime getDate() {
			return date;
		}
		public void setDate(LocalDateTime date) {
			this.date = date;
		}
		public LocalDateTime getDueDate() {
			return dueDate;
		}
		public void setDueDate(LocalDateTime dueDate) {
			this.dueDate = dueDate;
		}
		
		public String getClientFullName() {
			return clientFullName;
		}
		public void setClientFullName(String clientFullName) {
			this.clientFullName = clientFullName;
		}
		public String getAuthorFullName() {
			return authorFullName;
		}
		public void setAuthorFullName(String authorFullName) {
			this.authorFullName = authorFullName;
		}
		public ProjectState getState() {
			return state;
		}
		public void setState(ProjectState state) {
			this.state = state;
		}
		
		public ProjectState[] getStates() {
			return states;
		}
		public void setStates(ProjectState[] states) {
			this.states = states;
		}
		public Complexity[] getComplexities() {
			return complexities;
		}
		public void setComplexities(Complexity[] complexities) {
			this.complexities = complexities;
		}
		public String getSerie() {
			return serie;
		}
		public void setSerie(String serie) {
			this.serie = serie;
		}
}
