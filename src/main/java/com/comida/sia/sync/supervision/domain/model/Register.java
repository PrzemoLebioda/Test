package com.comida.sia.sync.supervision.domain.model;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.EmptyListException;
import com.comida.sia.sharedkernel.period.Period;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Register<T extends WaterMark, M extends ModelTranslator<T, R>, R extends TimeSeries> {
	
	@Getter private Period period;
	private Discriminator<T> discriminator;
	private ModelTranslator<T, R> modelTranslator;
	
	public Register() {
		
	}
	
	public void setPeriod(Period period) {
		AssertionConcern.assertNotNull(period, "Period must be provided");
		this.period = period;
	}
	
	public void setDiscriminator(Discriminator<T> discriminator) {
		AssertionConcern.assertNotNull(discriminator, "Discriminator must be provided");
		this.discriminator = discriminator;
	}
	
	public String getWaterMarkCurrentLevel() {
		return discriminator.getCurrentWaterMarkLevel();
	}
	
	public Register<T, M, R> with(ModelTranslator<T, R> modelTranslator) {
		AssertionConcern.assertNotNull(modelTranslator, "Model translator must be provided");
		this.modelTranslator = modelTranslator;
		return this;
	}
	
	public Register<T, M, R> reproduceNext() {
		Register<T, M, R> registerClone = new Register<>();
		
		if(Direction.ASCENDING.equals(discriminator.requiredOrder())) {
			registerClone.setPeriod(this.period.next());
		} else {
			registerClone.setPeriod(this.period.previous());
		}
			
		registerClone.setDiscriminator(discriminator);
		return registerClone;
	}
	
	public SynchronizationReport<T, R> synchronize(List<T> items) throws ParseException, EmptyListException {
		AssertionConcern.assertNotNull(modelTranslator, "Model translator must be provided");
		AssertionConcern.assertNotNull(period, "Period must be provided");
		AssertionConcern.assertNotNull(discriminator, "Discriminator must be provided in order to select items");
		AssertionConcern.assertNotEmpty(items, "There is nothing to synchronize");
		
		Collections.sort(items);
		
		SynchronizationReport<T, R> report = new SynchronizationReport<>();
		
		for(T item : items) {
			if(period.contains(item.occuranceTime())) {
				handleInPeriodItem(item, report);
			} else {
				handleOutOfPeriodItem(item, report);
			}	
		}
		
		return report.issue();
	}
	
	private void handleInPeriodItem(T item, SynchronizationReport<T, R> report) throws ParseException {
		try {			
			discriminator = discriminator.alignTo(item);
			report.addSynchronizedItem(modelTranslator.translate(item));
		} catch(NotEligibleException e) {
			report.addAlreadySynchronizedItem(item);
		} catch(Exception e) {
			log.warn("Skipping item: " + item + " caused by");
			log.warn(e.getMessage(), e);
		}
	}
	
	private void handleOutOfPeriodItem(T item, SynchronizationReport<T, R> report) throws ParseException {
		
		if(discriminator.requiredOrder().equals(Direction.ASCENDING)) {
			if(period.before(period.move(item.occuranceTime()))) {
				report.addOutOfPeriodItem(item);
			} else {
				report.addAlreadySynchronizedItem(item);
			}
		}
		
		if(discriminator.requiredOrder().equals(Direction.DESCENDING)) {
			if(period.after(period.move(item.occuranceTime()))) {
				report.addOutOfPeriodItem(item);
			} else {
				report.addAlreadySynchronizedItem(item);
			}
		}
	}
}
