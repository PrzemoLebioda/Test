package com.comida.sia.indicators.domain.model;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;

public enum Interval {
	ANNUAL("annual"),
	SEMIANNUAL("semiannual"),
	QUARTERLY("quarterly"),
	MONTHLY("monthly"),
	WEEKLY("weekly"),
	DAILY("daily");
	
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
