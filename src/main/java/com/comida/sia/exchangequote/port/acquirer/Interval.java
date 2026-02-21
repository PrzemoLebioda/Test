package com.comida.sia.exchangequote.port.acquirer;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;

public enum Interval {
	ONE_MIN_INTERVAL("1min"),
	FIVE_MIN_INTERVAL("5min"),
	FIFTEEN_MIN_INTERVAL("15min"),
	THIRTY_MIN_INTERVAL("30min"),
	SIXTY_MIN_INTERVAL("60min");
	
	@Getter private String name;
	private static final Map<String, Interval> ENUM_MAP;
	
	private Interval(String name) {
		this.name = name;
	}
	
	static {
        Map<String, Interval> map = new ConcurrentHashMap<String, Interval>();
        for (Interval instance : Interval.values()) {
            map.put(instance.getName().toLowerCase(),instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static Interval get (String name) {
        return ENUM_MAP.get(name.toLowerCase());
    }
}
