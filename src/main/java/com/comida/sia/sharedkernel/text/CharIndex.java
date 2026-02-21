package com.comida.sia.sharedkernel.text;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;

public enum CharIndex {
	ZERO	('0', 0),
	ONE		('1', 1),
	TWO		('2', 2),
	THREE	('3', 3),
	FOUR	('4', 4),
	FIVE	('5', 5),
	SIX		('6', 6),
	SEVEN	('7', 7),
	EIGHT	('8', 8),
	NINE	('9', 9),
	A		('a', 10),
	B		('b', 11),
	C		('c', 12),
	D		('d', 13),
	E		('e', 14),
	F		('f', 15),
	G		('g', 16),
	H		('h', 17),
	I		('i', 18),
	J		('j', 19),
	K		('k', 20),
	L		('l', 21),
	M		('m', 22),
	N		('n', 23),
	O		('o', 24),
	P		('p', 25),
	R		('r', 26),
	S		('s', 27),
	T		('t', 28),
	U		('u', 29),
	V		('v', 30),
	W		('w', 31),
	X		('x', 32),
	Y		('y', 33),
	Z		('z', 34);
	
	@Getter private char character;
	@Getter private int index;
	private static final Map<String, CharIndex> ENUM_CHAR_MAP;
	private static final Map<Integer, CharIndex> ENUM_INDEX_MAP;
	
	private CharIndex(char character, int index) {
		this.character = character;
		this.index = index;
	}
	
	static {
        Map<String, CharIndex> charMap = new ConcurrentHashMap<String, CharIndex>();
        Map<Integer, CharIndex> indexMap = new ConcurrentHashMap<Integer, CharIndex>();
        for (CharIndex instance : CharIndex.values()) {
        	charMap.put(String.valueOf(instance.getCharacter()), instance);
        	indexMap.put(instance.getIndex(), instance);
        }
        ENUM_CHAR_MAP = Collections.unmodifiableMap(charMap);
        ENUM_INDEX_MAP = Collections.unmodifiableMap(indexMap);
    }

    public static CharIndex get (char character) {
    	String letter = String.valueOf(character);
        return ENUM_CHAR_MAP.get(letter.toLowerCase());
    }
    
    public static CharIndex get (Integer index) {
        return ENUM_INDEX_MAP.get(index);
    }
    
    public static CharIndex get(long index) {
    	Integer idx = (int) index;
    	return ENUM_INDEX_MAP.get(idx);
    }
}
