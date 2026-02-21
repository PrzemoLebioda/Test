package com.comida.sia.indicators.adapter.messaging.durables;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.indicators.port.application.durables.DurableGoodsOrdersMonthlySynchronizeCommand;
import com.comida.sia.indicators.port.application.durables.DurableGoodsOrdersService;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.indicators.events.DurableGoodsOrdersMonthlySynchronizationOrderedDomainEvent;
import com.comida.sia.sync.supervision.port.messaging.indicators.DurableGoodsOrdersMonthlySynchronizationOrderedNotifiction;
import com.opencsv.exceptions.CsvException;

@Component("DurableGoodsOrdersMonthlySynchronizationOrderedNotifictionHandler")
public class DurableGoodsOrdersMonthlySynchronizationOrderedNotifictionHandler implements NotificationHandler {
	
	private DurableGoodsOrdersMonthlySynchronizationOrderedNotifiction notification;
	
	@Autowired
	@Qualifier("DurableGoodsOrdersApplicationService")
	private DurableGoodsOrdersService service;
	
	public DurableGoodsOrdersMonthlySynchronizationOrderedNotifictionHandler(
			@Qualifier("DurableGoodsOrdersSyncNotificationSubscriber") NotificationSubscriber subscriber) {
		subscriber.register(this);
	}

	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, DurableGoodsOrdersMonthlySynchronizationOrderedNotifiction.class);
			
			if(Subject.DURABLE_GOODS_ORDER_MONTHLY_SYNC_ORDERED.equals(notification.getSubject())) {
				return true;
			} else {
				return false;
			}
		} catch(Exception e) {
			return false;
		}
	}

	@Override
	public void hanle() throws ParseException, IOException, CsvException {
		buildCommand(notification.getPayload()).execute();
	}

	private Command<DurableGoodsOrdersService> buildCommand(DurableGoodsOrdersMonthlySynchronizationOrderedDomainEvent domainEvent) {
		return new DurableGoodsOrdersMonthlySynchronizeCommand(
				service,
				domainEvent.getSyncState());
	}
}
