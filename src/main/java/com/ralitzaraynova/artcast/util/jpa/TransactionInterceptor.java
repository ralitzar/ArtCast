package com.ralitzaraynova.artcast.util.jpa;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Transactional 
@Interceptor
public class TransactionInterceptor implements Serializable{

	private static final long serialVersionUID = 1L;
	
private @Inject EntityManager manager;
	
	@AroundInvoke
	public Object invoke(InvocationContext context) throws Exception {
		EntityTransaction trx = manager.getTransaction();
		boolean criator = false;

		try {
			if (!trx.isActive()) {
				trx.begin();
				trx.rollback();
				trx.begin();
				
				criator = true;
			}

			return context.proceed();
		} catch (Exception e) {
			if (trx != null && criator) {
				trx.rollback();
			}

			throw e;
		} finally {
			if (trx != null && trx.isActive() && criator) {
				trx.commit();
			}
		}
	}
}

