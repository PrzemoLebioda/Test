/**
 * 
 */
package com.comida.sia.config;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityManager;

/**
 * @author Przemysław Lebioda
 * 
 * <p>
 * Functionality common to all Hibernate repositories.
 * </p>
 * 
 */
public class HibernateRepository {
	private EntityManager entityManager;
	  
	  @Autowired
	  public HibernateRepository(final EntityManager entityManager) {
		  this.entityManager = entityManager;
	  }

	  protected EntityManager getEntityManager() {
	    return entityManager;
	  }
}
