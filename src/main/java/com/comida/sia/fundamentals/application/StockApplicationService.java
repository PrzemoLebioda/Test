package com.comida.sia.fundamentals.application;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.comida.sia.fundamentals.adapter.messaging.stock.StockNotificationPublisher;
import com.comida.sia.fundamentals.domain.model.stock.Stock;
import com.comida.sia.fundamentals.domain.model.stock.StockSynchronizationAdminAssembler;
import com.comida.sia.fundamentals.port.acquirer.stock.StockDataAcquirer;
import com.comida.sia.fundamentals.port.application.stock.StockService;
import com.comida.sia.fundamentals.port.repository.StockRepository;
import com.comida.sia.sharedkernel.EmptyListException;
import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("StockApplicationService")
public class StockApplicationService implements StockService, Notifier {

	@Autowired
	private StockRepository repository;
	
	@Autowired
	private StockDataAcquirer acquirer;
	
	@Autowired
	private StockNotificationPublisher publisher;

	@Override
	@Transactional
	public void synchronizeListedStock(SynchronizationStateDto syncState) {
		try {
			StockSynchronizationAdminAssembler assembler = new StockSynchronizationAdminAssembler();
			
			persistStocks(
					assembler
						.assemblyListedStockSyncAdmin(syncState, this)
						.synchronize(acquirer.gatherListedStockData(syncState), syncState)
				);
		} catch (EmptyListException e){
			log.warn("Listed stock list for is empty");
		} catch(IllegalArgumentException e) {
			log.error(e.getMessage(), e);
		} catch(Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	@Override
	@Transactional
	public void synchronizeDelistedStock(SynchronizationStateDto syncState) {
		try {
			StockSynchronizationAdminAssembler assembler = new StockSynchronizationAdminAssembler();
			
			persistStocks(
					assembler
						.assemblyDelistedStockSyncAdmin(syncState, this)
						.synchronize(acquirer.gatherDelistedStockData(syncState), syncState)
				);
		} catch (EmptyListException e){
			log.warn("Delisted stock list is empty");
		} catch(IllegalArgumentException e) {
			log.error(e.getMessage(), e);
		} catch(Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	private void persistStocks(List<Stock> stocks) {
		for(Stock stock : stocks) {
			try {
				repository.update(stock);
			} catch(Exception e) {
				repository.store(stock);
				
			}
		}
	}
	
	@Override
	public <P extends SubjectedPayload> void notify(P domainEvent) {
		Notification<P> notification = new Notification<>(UUID.randomUUID(), domainEvent);
		publisher.publish(notification);
		
	}
}
