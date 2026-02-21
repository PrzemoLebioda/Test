package com.comida.sia.indicators.domain.model.treasuryyeald;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;

public enum Maturity {
	THREE_MONTH("3month"), 
	TWO_YEAR("2year"), 
	FIVE_YEAR("5year"), 
	SEVEN_YEAR("7year"), 
	TEN_YEAR("10year"), 
	THIRTY_YEAR("30year");
	
	@Getter private String name;
	private static final Map<String, Maturity> ENUM_MAP;
	
	private Maturity(String name) {
		this.name = name;
	}
	
	static {
        Map<String, Maturity> map = new ConcurrentHashMap<String, Maturity>();
        for (Maturity instance : Maturity.values()) {
            map.put(instance.getName().toLowerCase(),instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static Maturity get (String name) {
        return ENUM_MAP.get(name.toLowerCase());
    }
}
