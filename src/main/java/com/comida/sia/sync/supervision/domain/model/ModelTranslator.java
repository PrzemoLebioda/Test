package com.comida.sia.sync.supervision.domain.model;

import java.text.ParseException;

public interface ModelTranslator <S extends WaterMark, T> {
	public T translate(S source) throws ParseException;
}
