package com.comida.sia.sharedkernel.messaging;

public class GeneralSerializer<T> extends AbstractSerializer{
	
	public GeneralSerializer() {
        this(false, false);
    }

	protected GeneralSerializer(boolean isCompact) {
		this(false, isCompact);
	}
	
	public GeneralSerializer(boolean isPretty, boolean isCompact) {
        super(isPretty, isCompact);
    }

	public String serialize(T content) {
		String serializedNotification = this.getGson().toJson(content);
		
		return serializedNotification;
	}
	
	public <P extends T> P deserialize(String aSerialization, final Class<P> aType) {
		P deserializedNotification = this.getGson().fromJson(aSerialization, aType);

        return deserializedNotification;
	}

}
