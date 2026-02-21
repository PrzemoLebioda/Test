package com.comida.sia.fundamentals.domain.model.earnings.estimation;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;

public enum Horizon {
	NEXT_FISCAL_YEAR("next fiscal year"),
	NEXT_FISCAL_QUARTER("next fiscal quarter"),
	CURRENT_FICAL_YEAR("current fiscal year"),
	CURRENT_FISCAL_QUARTER("current fiscal quarter"),
	HISTORICAL_FISCAL_YEAR("historical fiscal year"),
	HISTORICAL_FISCAL_QUARTER("historical fiscal quarter");
	
	
	@Getter private String name;
	private static final Map<String, Horizon> ENUM_MAP;
	
	private Horizon(String name) {
		this.name = name;
	}
	
	static {
        Map<String, Horizon> map = new ConcurrentHashMap<String, Horizon>();
        for (Horizon instance : Horizon.values()) {
            map.put(instance.getName().toLowerCase(), instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static Horizon get (String name) {
        return ENUM_MAP.get(name.toLowerCase());
    }
}
