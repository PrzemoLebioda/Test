package com.comida.sia.sharedkernel.text;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;

public enum NumericSystem {
	BINARY("binary", 2),
	OCTADECIMAL("octadecimal", 8),
	DECIMAL("decimal", 10),
	HEXADECIMAL("hexadecimal", 16),
	ALPHA_NUMERIC("alpha numeric", 35);
	
	@Getter private String name;
	@Getter private int basic;
	private static final Map<String, NumericSystem> ENUM_MAP;
	
	private NumericSystem(String name, int basic) {
		this.name = name;
		this.basic = basic;
	}
	
	static {
        Map<String, NumericSystem> map = new ConcurrentHashMap<String, NumericSystem>();
        for (NumericSystem instance : NumericSystem.values()) {
        	map.put(instance.getName(), instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }
	
	public static NumericSystem get (String name) {
        return ENUM_MAP.get(name);
    }
}
