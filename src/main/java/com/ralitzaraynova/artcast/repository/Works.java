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

import com.ralitzaraynova.artcast.model.Work;
import com.ralitzaraynova.artcast.repository.filter.FilterProperties;
import com.ralitzaraynova.artcast.service.BusinessExeption;
import com.ralitzaraynova.artcast.util.jpa.Transactional;

public class Works implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Work retain(Work work){
		return manager.merge(work);
	}

	public Work byCode(String workCode){
		try{
			return manager.createQuery("from Work where upper(workCode)= :workCode", Work.class)
				.setParameter("workCode", workCode.toUpperCase())
				.getSingleResult();
		}catch (NoResultException e){
			return null;
		}
	}
	
	public List<Work> byDenomination(String denomination){
		return this.manager.createQuery("from Work where upper(denomination) like :denomination", Work.class)
				.setParameter("denomination", denomination.toUpperCase()+ "%")
				.getResultList();
		
	}

	public List<Work> filtered(FilterProperties filter){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Work.class);
		
		if(StringUtils.isNotBlank(filter.getWorkCode())){
			criteria.add(Restrictions.eq("workCode", filter.getWorkCode()));
		}
		
		if(StringUtils.isNotBlank(filter.getDenomination())){
			criteria.add(Restrictions.ilike("denomination", filter.getDenomination(), MatchMode.ANYWHERE));
		}
		return criteria.list();
	}

	public Work byId(Long id) {
		return manager.find(Work.class, id);
	}
	
	@Transactional
	public void removeWork(Work work){
		try{
			work = byId(work.getId());
			manager.remove(work);
			manager.flush();
		}catch (PersistenceException e) {
			throw new BusinessExeption("Дейността не може да бъде изтрита!");
			}
	}
}
