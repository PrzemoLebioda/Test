package com.comida.sia.fundamentals.domain.model.company;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;

public enum Sector {
	TECHNOLOGY("Technology"),
	FINANCIAL_SERVICES("Financial Services"),
	CONSUMER_CYCLICAL("Consumer Cyclical"),
	COMMUNICATION_SERVICES("Communication Services"),
	HEALTHCARE("Healthcare"),
	INDUSTRIALS("Industrials"),
	CONSUMER_DEFENSIVE("Consumer Defensive"),
	ENERGY("Energy"),
	BASIC_MATERIALS("Basic Materials"),
	REAL_ESTATE("Real Estate"),
	UTILITIES("Utilities");
	
	@Getter private String name;
	private static final Map<String, Sector> ENUM_MAP;
	
	private Sector(String name) {
		this.name = name;
	}
	
	static {
        Map<String, Sector> map = new ConcurrentHashMap<String, Sector>();
        for (Sector instance : Sector.values()) {
            map.put(instance.getName().toLowerCase(),instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static Sector get (String name) {
        return ENUM_MAP.get(name.toLowerCase());
    }
}
