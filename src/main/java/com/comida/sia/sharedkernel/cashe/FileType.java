package com.comida.sia.sharedkernel.cashe;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;

public enum FileType {
	CSV("csv"),
	JSON("json");
	
	@Getter private String name;
	private static final Map<String, FileType> ENUM_MAP;
	
	private FileType(String name) {
		this.name = name;
	}
	
	static {
        Map<String, FileType> map = new ConcurrentHashMap<String, FileType>();
        for (FileType instance : FileType.values()) {
            map.put(instance.getName().toLowerCase(),instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static FileType get (String name) {
        return ENUM_MAP.get(name.toLowerCase());
    }
}
