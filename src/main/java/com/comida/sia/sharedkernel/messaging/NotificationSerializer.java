package com.comida.sia.sharedkernel.messaging;

public class NotificationSerializer extends AbstractSerializer {

	private static NotificationSerializer notificationSerializer;
	
	public static synchronized NotificationSerializer instance() {
		if(NotificationSerializer.notificationSerializer == null) {
			NotificationSerializer.notificationSerializer = new NotificationSerializer();
		}
		return NotificationSerializer.notificationSerializer;
	}
	
	private NotificationSerializer() {
        this(false, false);
    }

	protected NotificationSerializer(boolean isCompact) {
		this(false, isCompact);
	}
	
	public NotificationSerializer(boolean isPretty, boolean isCompact) {
        super(isPretty, isCompact);
    }

	public String serialize(Notification<?> notification) {
		String serializedNotification = this.getGson().toJson(notification);
		
		return serializedNotification;
	}
	
	public <P extends Notification<?>> P deserialize(String aSerialization, final Class<P> aType) {
		P deserializedNotification = this.getGson().fromJson(aSerialization, aType);

        return deserializedNotification;
	}
}
