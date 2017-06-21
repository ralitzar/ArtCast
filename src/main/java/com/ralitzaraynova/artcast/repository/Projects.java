package com.ralitzaraynova.artcast.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.ralitzaraynova.artcast.model.project.Project;
import com.ralitzaraynova.artcast.repository.filter.ProjectFilterProperties;
import com.ralitzaraynova.artcast.service.BusinessExeption;
import com.ralitzaraynova.artcast.util.jpa.Transactional;

public class Projects implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Project byCode(String number){
		try{
			return manager.createQuery("from Project where upper(number)= :number", Project.class)
				.setParameter("number", number.toUpperCase())
				.getSingleResult();
		}catch (NoResultException e){
			return null;
		}
	}
	
	public Project byId(Long id) {
		return manager.find(Project.class, id);
		}
	
	public List<Project> filtered(ProjectFilterProperties filter){
		Session session = this.manager.unwrap(Session.class);
		
		Criteria criteria = session.createCriteria(Project.class)
			.createAlias("client", "c")
			.createAlias("author", "a");
		if(StringUtils.isNotBlank(filter.getNumber())){
			criteria.add(Restrictions.eq("number",filter.getNumber()));
		}
		if(StringUtils.isNotBlank(filter.getName())){
			criteria.add(Restrictions.ilike("name", filter.getName(), MatchMode.ANYWHERE));
		}
		if(filter.getDate() != null){
			criteria.add(Restrictions.eq("date",filter.getDate()));
		}
		if(filter.getDueDate() != null){
			criteria.add(Restrictions.eq("dueDate",filter.getDueDate()));
		}
		if(StringUtils.isNotBlank(filter.getClientFullName())){
			criteria.add(Restrictions.ilike("c.fullName", filter.getClientFullName(), MatchMode.ANYWHERE));
		}
		if(StringUtils.isNotBlank(filter.getAuthorFullName())){
			criteria.add(Restrictions.ilike("a.fullName", filter.getAuthorFullName(), MatchMode.ANYWHERE));
		}
		
		if(filter.getState() != null){
			criteria.add(Restrictions.eq("projectStates", filter.getState()));
		}
		
		return criteria.list();
	}
	
	@Transactional
	public Project retain(Project project){
		return manager.merge(project);
	}
	
	@Transactional
	public void removeProject(Project project) {
		try{
			project = byId(project.getId());
			manager.remove(project);
			manager.flush();
		}catch (PersistenceException e){
			throw new BusinessExeption("Проекта не може да бъде изтрита!");
		}
	}

}
