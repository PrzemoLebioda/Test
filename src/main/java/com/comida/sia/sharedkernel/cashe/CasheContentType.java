package com.comida.sia.sharedkernel.cashe;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;

public enum CasheContentType {
	CPI("CPI"),
	JSON("json");
	
	@Getter private String name;
	private static final Map<String, CasheContentType> ENUM_MAP;
	
	private CasheContentType(String name) {
		this.name = name;
	}
	
	static {
        Map<String, CasheContentType> map = new ConcurrentHashMap<String, CasheContentType>();
        for (CasheContentType instance : CasheContentType.values()) {
            map.put(instance.getName().toLowerCase(),instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static CasheContentType get (String name) {
        return ENUM_MAP.get(name.toLowerCase());
    }
}
