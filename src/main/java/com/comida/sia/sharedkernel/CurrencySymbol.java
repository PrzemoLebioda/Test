package com.comida.sia.sharedkernel;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;

public enum CurrencySymbol {
	ARS("ars"),
	AUD("aud"),
	BRL("brl"),
	CAD("cad"),
	CHF("chf"),
	CLP("clp"),
	CNY("cny"),
	COP("cop"),
	CZK("czk"),
	DKK("dkk"),
	EGP("egp"),
	EUR("eur"),
	GBP("gbp"),
	GEL("gel"),
	HKD("hkd"),
	HUF("huf"),
	IDR("idr"),
	ILS("ils"),
	INR("inr"),
	JPY("jpy"),
	KRW("krw"),
	KZT("kzt"),
	MXN("mxn"),
	MYR("myr"),
	NOK("nok"),
	NZD("nzd"),
	PEN("pen"),
	PHP("php"),
	PLN("pln"),
	RUB("rub"),
	SEK("sek"),
	SGD("sgd"),
	THB("thb"),
	TRY("try"),
	TWD("twd"),
	USD("usd"),
	VND("vnd"),
	ZAR("zar"),
	NONE("None");
	
	@Getter private String symbol;
	private static final Map<String, CurrencySymbol> ENUM_MAP;
	
	private CurrencySymbol(String symbol) {
		this.symbol = symbol;
	}
	
	static {
        Map<String,CurrencySymbol> map = new ConcurrentHashMap<String, CurrencySymbol>();
        for (CurrencySymbol instance : CurrencySymbol.values()) {
            map.put(instance.getSymbol().toLowerCase(),instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static CurrencySymbol get (String symbol) {
        return ENUM_MAP.get(symbol.toLowerCase());
    }

}
