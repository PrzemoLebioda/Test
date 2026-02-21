package com.comida.sia.sync.supervision.domain.model;

public class DifferentialDiscriminator<T extends WaterMark> extends Discriminator<T> {

	public DifferentialDiscriminator() {
		super();
	}
	
	public DifferentialDiscriminator(T currentWaterMark) {
		super(currentWaterMark);
	}

	@Override
	public Discriminator<T> alignTo(T waterMark) throws NotEligibleException {
		if(isEligible(waterMark)) {
			return new DifferentialDiscriminator<T>(waterMark);
		} else {
			String message = "Candidate of new water mark is not eligible (should be just different): Current water mark is " +  currentWaterMarkLevel + " the candidate one is " + waterMark.calculateLevel();
			throw new NotEligibleException(message);
		}
	}

	@Override
	public Boolean isEligible(T waterMark) {
		if(waterMark.sameLevel(currentWaterMarkLevel)) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public Direction requiredOrder() {
		return Direction.ASCENDING;
	}
	
	
}