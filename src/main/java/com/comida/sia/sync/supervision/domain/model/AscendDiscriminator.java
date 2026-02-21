package com.comida.sia.sync.supervision.domain.model;

import com.comida.sia.sharedkernel.AssertionConcern;

public class AscendDiscriminator<T extends WaterMark> extends Discriminator<T> {

	public AscendDiscriminator() {
		super();
	}
	
	public AscendDiscriminator(T currentWaterMark) {
		super(currentWaterMark);
	}

	@Override
	public Discriminator<T> alignTo(T waterMark) throws NotEligibleException {		
		if(isEligible(waterMark)) {
			return new AscendDiscriminator<T>(waterMark);
		} else {
			String message = "Candidate of new water mark is not eligible (should be above): Current water mark is " +  currentWaterMarkLevel + " the candidate one is " + waterMark.calculateLevel();
			throw new NotEligibleException(message);
		}
	}

	@Override
	public Boolean isEligible(T waterMark) {
		AssertionConcern.assertNotNull(waterMark, "Water mark candiate can't be null in order to check whether is egigible or not");
		return waterMark.isAbove(currentWaterMarkLevel);
	}

	@Override
	public Direction requiredOrder() {
		return Direction.ASCENDING;
	}

}
