package com.comida.sia.sharedkernel.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.comida.sia.sharedkernel.AssertionConcern;

public abstract class NotificationPublisher {
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	protected RoutingKeyDeterminator routingKeyDeterminator;
	protected String exchangeName;
	
	public NotificationPublisher() {
	
	}
	
	public <T extends Notification<P>, P extends SubjectedPayload> void publish(T notification) {
		configureExchange();
		send(notification);
	}
	
	protected abstract void configureExchange();	
	
	private <T extends Notification<P>, P extends SubjectedPayload> void send(T notification) {
		AssertionConcern.assertNotEmpty(exchangeName, "Exchange name is necessary for sending notification");
		AssertionConcern.assertNotNull(notification, "There is no to send, notification is null");
		
		rabbitTemplate.convertAndSend(
				exchangeName, 
				routingKeyDeterminator != null ? routingKeyDeterminator.getRoutingKey() : notification.getSubject().getName(), 
				NotificationSerializer
					.instance()
					.serialize(notification));
	}
}
