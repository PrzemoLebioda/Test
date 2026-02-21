package com.comida.sia.fundamentals.domain.model.company;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;

public enum Exchange {
	BATS("bats"),
	NASDAQ("nasdaq"),
	NYSE("nyse"),
	NYSE_ARCA("nyse arca"),
	NYSE_MKT("nyse mkt"),
	P_BIOTECH_BEAR_3X_SHARES("p biotech bear 3x shares"),
	P_BIOTECH_BULL_3X_SHARES("p biotech bull 3x shares"),
	SECURITIES_EXCHANGES_ETF("securities exchanges etf"),
	UNKNOWN("unknown");
	
	@Getter private String name;
	private static final Map<String, Exchange> ENUM_MAP;
	
	private Exchange(String name) {
		this.name = name;
	}
	
	static {
        Map<String, Exchange> map = new ConcurrentHashMap<String, Exchange>();
        for (Exchange instance : Exchange.values()) {
            map.put(instance.getName().toLowerCase(),instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static Exchange get (String name) {
        return ENUM_MAP.get(name.toLowerCase());
    }
}
