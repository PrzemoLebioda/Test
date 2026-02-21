package com.comida.sia.sharedkernel.messaging;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.opencsv.exceptions.CsvException;

public abstract class NotificationSubscriber {
	protected String message;
	private List<NotificationHandler> notificationHandlerList;
	
	
	public NotificationSubscriber() {
		super();
	}
	
	public NotificationSubscriber register(NotificationHandler notificationHandler) {
		AssertionConcern.assertNotNull(notificationHandler, "Notification handler must be provided for registration");
		getNotificationHandlerList().add(notificationHandler);
		
		return this;
	}
	
	private List<NotificationHandler> getNotificationHandlerList(){
		if(notificationHandlerList == null) {
			notificationHandlerList = new ArrayList<>();
		}
		
		return notificationHandlerList;
	}
	
	public void handle(String message) throws ParseException, IOException, CsvException {
		this.message = message;
		handleNotification();
	}
	
	private void handleNotification() throws ParseException, IOException, CsvException {
		for (NotificationHandler handler : getNotificationHandlerList()) {
			if(handler.canHandle(message)) {
				handler.hanle();
			}
		}
	}
}
