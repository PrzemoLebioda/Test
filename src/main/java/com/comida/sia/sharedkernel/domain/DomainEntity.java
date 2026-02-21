/**
 * 
 */
package com.comida.sia.sharedkernel.domain;

/**
 * @author przemyslaw.lebioda
 *
 */
public interface DomainEntity<T> {
	
	/**
	 * Entities compare by identity, not by attributes.
	 * 
	 * @param other
	 * @return
	 */
	boolean sameIdentityAs(T other);
}
