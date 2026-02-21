package com.comida.sia.sync.supervision.domain.model;

import java.text.ParseException;
import java.util.List;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.EmptyListException;
import com.comida.sia.sharedkernel.messaging.Notifier;

public class SynchronizationAdmin<T extends WaterMark, M extends ModelTranslator<T, R>, R extends TimeSeries> {
	private SynchronizationWorker<T, M, R> syncWorker;
	private M translator;
	protected Notifier notifier;
	
	protected SynchronizationReport <T, R> report;
	private String tickerSymbol;
	
	private DomainEventBuilder<T, R> domainEventBuilder;
	
	public SynchronizationAdmin() {
		
	}
	
	public void setDomainEventBuilder(DomainEventBuilder<T, R> domainEventBuilder) {
		AssertionConcern.assertNotNull(domainEventBuilder, "DomainEventBuilder is mandatory");
		this.domainEventBuilder = domainEventBuilder;
	}
	
	public void setTickerSymbol(String tickerSymbol) {
		AssertionConcern.assertNotEmpty(tickerSymbol, "Ticker symbol is mandatory");
		this.tickerSymbol = tickerSymbol;
	}
	
	public void setSyncWorker(SynchronizationWorker<T, M, R> syncWorker) {
		AssertionConcern.assertNotNull(syncWorker, "Synchronization worker is necessary");
		this.syncWorker = syncWorker;
	}
	
	public void setNotifier(Notifier notyfier) {
		AssertionConcern.assertNotNull(notyfier, "Notifier is necessary for sending notification about domain events");
		this.notifier = notyfier;
	}
	
	public void setTranslator(M translator) {
		AssertionConcern.assertNotNull(translator, "translator is necessary");
		this.translator = translator;
	}
	
	public List<R> synchronize(List<T> items, SynchronizationStateDto syncState) throws ParseException, EmptyListException {
		obtainSynchronizedItems(items);
		enrichReport();
		sentNotification();
		return synchronizationResult();
	}
	
	private void obtainSynchronizedItems(List<T> items) throws ParseException, EmptyListException{
		AssertionConcern.assertNotEmpty(items, "List of items for synchronization must not be empty");
		syncWorker
			.with(translator)
			.prepareSynchronizedItemList(items);
	}
	
	private void enrichReport() {
		this.report = syncWorker.getReport();
		
		report.setPeriod(syncWorker.obtainPeriod());
		report.setWaterMarkLevel(syncWorker.obtainWaterMarkCurrentLevel());
		report.setTickerSymbol(tickerSymbol);
	}
	
	public void sentNotification() {
		if(report.obtainSynchronizedItems().size() > 0) {
			AssertionConcern.assertNotNull(domainEventBuilder, "Domain event builder is mandatory in order to send notification");
			AssertionConcern.assertNotNull(report, "Report from synchronization is mandatory in order to create and send notification");
			
			notifier.notify(domainEventBuilder.build(report));
		}
	}
	
	private List<R> synchronizationResult(){
		return syncWorker.obtainSynchronizedItems();
	}
}
