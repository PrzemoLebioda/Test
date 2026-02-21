package com.comida.sia.sharedkernel.messaging;

import com.comida.sia.sharedkernel.AssertionConcern;

public abstract class RoutingKeyDeterminator {
	public static String DEFAULT_DETERMINANT_VALUE = "NONE";
	protected String determinant;
	
	public RoutingKeyDeterminator(String determinant) {
		setDeterminant(determinant);
	}
	
	private void setDeterminant(String determinant) {
		AssertionConcern.assertNotNull(determinant, "Determinant value is necessary for creating Routing key Determinator");
		this.determinant = determinant;
	}
	
	public abstract String getRoutingKey();
}
