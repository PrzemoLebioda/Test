package com.comida.sia.fundamentals.domain.model.company;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;

public enum AssetType {
	STOCK("STOCK"),
	ETF("ETF"),
	NYSE_ARCA("NYSE ARCA");
	
	@Getter private String name;
	private static final Map<String, AssetType> ENUM_MAP;
	
	private AssetType(String name) {
		this.name = name;
	}
	
	static {
        Map<String, AssetType> map = new ConcurrentHashMap<String, AssetType>();
        for (AssetType instance : AssetType.values()) {
            map.put(instance.getName().toLowerCase(),instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static AssetType get (String name) {
        return ENUM_MAP.get(name.toLowerCase());
    }
}
