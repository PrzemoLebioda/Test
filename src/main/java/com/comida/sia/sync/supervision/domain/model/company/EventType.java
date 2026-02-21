package com.comida.sia.sync.supervision.domain.model.company;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;

public enum EventType {
	CALENDAR_UPDATED("calendar updated");
	
	@Getter private String name;
	private static final Map<String, EventType> ENUM_MAP;
	
	private EventType(String name) {
		this.name = name;
	}
	
	static {
        Map<String, EventType> map = new ConcurrentHashMap<String, EventType>();
        for (EventType instance : EventType.values()) {
            map.put(instance.getName().toLowerCase(),instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static EventType get (String name) {
        return ENUM_MAP.get(name.toLowerCase());
    }
}
