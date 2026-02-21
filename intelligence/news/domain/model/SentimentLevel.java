package com.comida.sia.intelligence.news.domain.model;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;

/**
 * x <= -0.35: Bearish; 
 * -0.35 < x <= -0.15: Somewhat-Bearish;
 * -0.15 < x < 0.15: Neutral;
 * 0.15 <= x < 0.35: Somewhat_Bullish;
 * x >= 0.35: Bullish",
 */
public enum SentimentLevel {
	BEARISH("Bearish"),
	SOMEWHAT_BEARISH("Somewhat-Bearish"),
	NEUTRAL("Neutral"),
	SOMEWHAT_BULLISH("Somewhat_Bullish"),
	BULLISH("Bullish");
	
	@Getter private String name;
	private static final Map<String, SentimentLevel> ENUM_MAP;
	
	private SentimentLevel(String name) {
		this.name = name;
	}
	
	static {
        Map<String, SentimentLevel> map = new ConcurrentHashMap<String, SentimentLevel>();
        for (SentimentLevel instance : SentimentLevel.values()) {
            map.put(instance.getName().toLowerCase(),instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static SentimentLevel get (String name) {
        return ENUM_MAP.get(name.toLowerCase());
    }
}
