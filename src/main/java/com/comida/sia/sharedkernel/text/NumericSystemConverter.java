package com.comida.sia.sharedkernel.text;

import java.util.Date;

import com.comida.sia.sharedkernel.AssertionConcern;

public class NumericSystemConverter extends AssertionConcern{
	private String text;
	private int numberOfCharsForIndexing;
	
	public NumericSystemConverter() {
		
	}
	
	public NumericSystemConverter(String text,  int numberOfCharsForIndexing) {
		this.setText(text);
		this.setNumberOfCharsForIndexing(numberOfCharsForIndexing);
	}
	
	private void setText(String text) {
		assertNotEmpty(text, "Text is mandatory for index calculations");
		this.text = text
				.replaceAll("\\s", "")
				.toLowerCase();
	}
	
	private void setNumberOfCharsForIndexing(int numberOfCharsForIndexing) {
		this.numberOfCharsForIndexing = numberOfCharsForIndexing;
	}
	
	private long calculateIndex() {
		
		long index = 0L;
		
		for(int i = 0; i < getMin(); i++) {
			CharIndex charIndex = CharIndex.get(text.charAt(i));
			
			if(charIndex != null) {
				index = (long) (index + (charIndex.getIndex() * Math.pow(25, getMin() - (i + 1))));
			}
		}
		
		return index;
	}
	
	private int getMin() {
		if(this.numberOfCharsForIndexing < this.text.length()) {
			return numberOfCharsForIndexing;
		} else {
			return this.text.length();
		}
	}
	
	public long calculateIndex(String text, int numberOfCharsForIndexing) {
		this.setText(text);
		this.setNumberOfCharsForIndexing(numberOfCharsForIndexing);
		
		return this.calculateIndex();
	}
	
	public String convertToAlphaNumeric(Date date) {	
		long convertableNumber = date == null ? 0L : date.getTime();		
		return convert(convertableNumber, NumericSystem.ALPHA_NUMERIC);
	}
	
	public String convertToAlphaNumeric(Long number) {
		long numberForConvertion = number == null ? 0L : number;
		return convert(numberForConvertion, NumericSystem.ALPHA_NUMERIC);
	}
	
	private String convert(long value, NumericSystem numberSystem) {
		String convertedValue = "";
		
		while(value > 0) {
			long modulo = value % numberSystem.getBasic();
			String character = String.valueOf(CharIndex.get(modulo).getCharacter());
			
			convertedValue = character + convertedValue;
			value = value / numberSystem.getBasic();
		}
		
		return convertedValue;
	}
}
