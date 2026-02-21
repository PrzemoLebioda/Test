package com.comida.sia.intelligence.insidertransactions.application;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.intelligence.insidertransactions.domain.model.InsiderTransaction;
import com.comida.sia.intelligence.insidertransactions.domain.model.InsiderTransactionsSynchronizationAdminAssembler;
import com.comida.sia.intelligence.insidertransactions.port.acquirer.InsiderTransactionData;
import com.comida.sia.intelligence.insidertransactions.port.acquirer.InsiderTransactionsDataAcquirer;
import com.comida.sia.intelligence.insidertransactions.port.application.InsiderTransactionService;
import com.comida.sia.intelligence.insidertransactions.port.repository.InsiderTransactionRepository;
import com.comida.sia.sharedkernel.EmptyListException;
import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("InsiderTransactionApplicationService")
public class InsiderTransactionApplicationService implements InsiderTransactionService, Notifier {
	
	@Autowired
	@Qualifier("InsiderTransactionHibernateRepository")
	private InsiderTransactionRepository repository;
	
	@Autowired
	@Qualifier("AlfavantageInsiderTransactionsDataAcquirer")
	private InsiderTransactionsDataAcquirer acquirer;
	
	@Autowired
	@Qualifier("InsiderTransactionsynchronizedNotificationPublisher")
	private NotificationPublisher publisher;

	@Override
	@Transactional
	public void registerInsiderTransactionsFor(String tickerSymbol, SynchronizationStateDto syncState) {
		try {
			InsiderTransactionsSynchronizationAdminAssembler assembler = new InsiderTransactionsSynchronizationAdminAssembler(tickerSymbol);
			
			persist(assembler
					.assemblyInsiderTransactionSyncAdmin(syncState, this)
					.synchronize(
							cleanUp(
									acquirer.gatherInsiderTransactionsFor(tickerSymbol, syncState).getData()
							), 
							syncState)
			);
			
		} catch (IllegalArgumentException e) {
			log.error(e.getMessage(), e);
		} catch (EmptyListException e){
			log.warn("Insider transaction list is empty");
		} catch(Exception e) {
			log.error(e.getMessage(), e);
		}
		
	}
	
	private void persist(List<InsiderTransaction> insiterTransactions){
		for(InsiderTransaction item : insiterTransactions) {
			if(item != null) {
				repository.store(item);
			}
		}
	}
	
	private List<InsiderTransactionData> cleanUp(List<InsiderTransactionData> insiderTransactionDataList){
		List<InsiderTransactionData> cleanInsiderTransactionDataList = new ArrayList<>();
		
		for(InsiderTransactionData insiderTransactionData : insiderTransactionDataList) {
			if(insiderTransactionData.getTransaction_date() != null && insiderTransactionData.getTransaction_date() != "") {
				cleanInsiderTransactionDataList.add(insiderTransactionData);
			}
		}
		
		return cleanInsiderTransactionDataList;
	}
	
	@Override
	public <P extends SubjectedPayload> void notify(P domainEvent) {
		Notification<P> notification = new Notification<>(UUID.randomUUID(), domainEvent);
		publisher.publish(notification);
	}

}
