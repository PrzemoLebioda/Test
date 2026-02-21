package com.comida.sia.options.domain.model;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;

public enum OptionType {
	PUT("put"),
	CALL("call");
	
	@Getter private String name;
	private static final Map<String, OptionType> ENUM_MAP;
	
	private OptionType(String name) {
		this.name = name;
	}
	
	static {
        Map<String, OptionType> map = new ConcurrentHashMap<String, OptionType>();
        for (OptionType instance : OptionType.values()) {
            map.put(instance.getName().toLowerCase(),instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static OptionType get (String name) {
        return ENUM_MAP.get(name.toLowerCase());
    }
}
