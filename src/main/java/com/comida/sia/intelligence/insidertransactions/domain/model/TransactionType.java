package com.comida.sia.intelligence.insidertransactions.domain.model;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;

public enum TransactionType {
	ACQUISITION("A"),
	DISPOSAL("D");
	
	@Getter private String name;
	private static final Map<String, TransactionType> ENUM_MAP;
	
	private TransactionType(String name) {
		this.name = name;
	}
	
	static {
        Map<String, TransactionType> map = new ConcurrentHashMap<String, TransactionType>();
        for (TransactionType instance : TransactionType.values()) {
            map.put(instance.getName().toLowerCase(),instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static TransactionType get (String name) {
        return ENUM_MAP.get(name.toLowerCase());
    }
}
