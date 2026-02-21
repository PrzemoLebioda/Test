package com.comida.sia.fundamentals.domain.model.stock;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;

public enum ListingStatus {
	ACTIVE("active"),
	DELISTED("delisted");
	
	@Getter private String name;
	private static final Map<String, ListingStatus> ENUM_MAP;
	
	private ListingStatus(String name) {
		this.name = name;
	}
	
	static {
        Map<String, ListingStatus> map = new ConcurrentHashMap<String, ListingStatus>();
        for (ListingStatus instance : ListingStatus.values()) {
            map.put(instance.getName().toLowerCase(),instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static ListingStatus get (String name) {
        return ENUM_MAP.get(name.toLowerCase());
    }
}
