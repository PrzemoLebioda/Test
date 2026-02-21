package com.comida.sia.indicators.application.payroll;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.indicators.domain.model.Interval;
import com.comida.sia.indicators.domain.model.payroll.NonfarmPayroll;
import com.comida.sia.indicators.domain.model.payroll.NonfarmPayrollSynchronizationAdminAssembler;
import com.comida.sia.indicators.port.acquirer.IndicatorsDataAcquirer;
import com.comida.sia.indicators.port.application.payroll.NonfarmPayrollService;
import com.comida.sia.indicators.port.repository.payroll.NonfarmPayrollRepository;
import com.comida.sia.sharedkernel.EmptyListException;
import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("NonfarmPayrollApplicationService")
public class NonfarmPayrollApplicationService implements NonfarmPayrollService, Notifier {

	@Autowired
	@Qualifier("AlfavantageNonfarmPayrollDataAcquirer")
	private IndicatorsDataAcquirer acquirer;
	
	@Autowired
	@Qualifier("NonfarmPayrollNotificationPublisher")
	private NotificationPublisher publisher;

	@Autowired
	@Qualifier("NonfarmPayrollHibernateRepository")
	public NonfarmPayrollRepository repository;
	
	@Override
	@Transactional
	public void synchronizeMonthly(SynchronizationStateDto syncState) {
		try {
			NonfarmPayrollSynchronizationAdminAssembler assembler = new NonfarmPayrollSynchronizationAdminAssembler();
			
			persist(
					assembler
						.assemblyNonfarmPayrollMonthlySyncAdmin(syncState, this)
						.synchronize(
								acquirer.gatherIndicatorData(Interval.MONTHLY).getData(),
								syncState)
			);
			
		} catch (EmptyListException e){
			log.warn("Nonfarm payroll monthly list is empty");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	private void persist(List<NonfarmPayroll> nonfarmPayrollList) {
		for(NonfarmPayroll nonfarmPayroll : nonfarmPayrollList) {
			persist(nonfarmPayroll);
		}
	}

	private void persist(NonfarmPayroll nonfarmPayroll) {
		try {
			repository.store(nonfarmPayroll);
		} catch(Exception e) {
			repository.update(nonfarmPayroll);
		}
	}

	@Override
	public <P extends SubjectedPayload> void notify(P domainEvent) {
		Notification<P> notification = new Notification<>(UUID.randomUUID(), domainEvent);
		publisher.publish(notification);
	}
}
