package com.comida.sia.sync.supervision.domain.model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.EmptyListException;
import com.comida.sia.sharedkernel.period.Period;

import lombok.Getter;

public class SynchronizationWorker<T extends WaterMark, M extends ModelTranslator<T, R>, R extends TimeSeries> extends AssertionConcern {
	
	@Getter private UUID id;
	
	private Register<T, M, R> register;
	private ModelTranslator<T, R> modelTranslator;
	
	@Getter private SynchronizationReport<T, R> report;
	
	private List<R> synchronizedItems;
	
	
	public SynchronizationWorker() {
		super();
	}
	
	public void setRegister(Register<T, M, R> register) {
		assertNotNull(register, "Register must be provided");
		this.register = register;
	}
	
	public void prepareSynchronizedItemList(List<T> itemsToSynchronize) throws ParseException, EmptyListException{
		report = register
				.with(modelTranslator)
				.synchronize(itemsToSynchronize);
		
		appendSynchronizedItems(report.obtainSynchronizedItems());
		
		if(report.containsOutOfPeriodItems()) {
			register = register.reproduceNext();
			prepareSynchronizedItemList(itemsToSynchronize);
		} 
	}
	
	public SynchronizationWorker<T, M, R> with(M translator){
		assertNotNull(translator, "translator must be provided");
		this.modelTranslator = translator;
		return this;
	}
	
	private void appendSynchronizedItems(List<R> items) {
		getSynchronizedItems().addAll(items);
	}
	
	public List<R> obtainSynchronizedItems(){
		return Collections.unmodifiableList(getSynchronizedItems());
	}
	
	private List<R> getSynchronizedItems(){
		if(synchronizedItems == null) {
			synchronizedItems = new ArrayList<>();
		}
		
		return synchronizedItems;
	}
	
	public Period obtainPeriod() {
		return register.getPeriod();
	}
	
	public String obtainWaterMarkCurrentLevel() {
		return register.getWaterMarkCurrentLevel();
	}
}
