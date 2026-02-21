package com.comida.sia.sharedkernel;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.comida.sia.sharedkernel.domain.ValueObject;
import com.comida.sia.sharedkernel.text.CharIndex;

public class ComparationConcern extends AssertionConcern{
	public static Boolean sameDates(Date oneDate, Date secondDate) {
	
    	if(oneDate == null && secondDate == null) {
    		return true;
    	}
    	
    	if(oneDate == null && secondDate != null) {
    		return false;
    	}
    	
    	if(oneDate != null && secondDate == null) {
    		return false;
    	}
    	
    	Calendar oneDateCalendar = Calendar.getInstance();
    	oneDateCalendar.setTime(oneDate);
    	
    	Calendar secondDateCalendar = Calendar.getInstance();
    	secondDateCalendar.setTime(secondDate);
    	
    	return (oneDateCalendar.get(Calendar.YEAR) == secondDateCalendar.get(Calendar.YEAR)) &&
    			(oneDateCalendar.get(Calendar.MONTH) == secondDateCalendar.get(Calendar.MONTH)) &&
    			(oneDateCalendar.get(Calendar.DAY_OF_MONTH) == secondDateCalendar.get(Calendar.DAY_OF_MONTH));
    }
	
	public static Boolean differentDates(Date oneDate, Date secondDate) {
		return !sameDates(oneDate, secondDate);
	}
	
	public static Date getMax(Date firstDate, Date otherDate) {
		if(firstDate == null && otherDate == null) {
			return null;
		}
		
		if(firstDate == null && otherDate != null) {
			return otherDate;
		}
		
		if(firstDate != null && otherDate == null) {
			return firstDate;
		}
		
		if(firstDate.after(otherDate)) {
			return firstDate;
		} else {
			return otherDate;
		}
	}
	
	public static Date getMin(Date firstDate, Date otherDate) {
		if(firstDate == null && otherDate == null) {
			return null;
		}
		
		if(firstDate == null && otherDate != null) {
			return otherDate;
		}
		
		if(firstDate != null && otherDate == null) {
			return firstDate;
		}
		
		if(firstDate.before(otherDate)) {
			return firstDate;
		} else {
			return otherDate;
		}
	}
	
	public static Boolean sameNumbers(BigDecimal oneNumber, BigDecimal secondNumber) {
		if(oneNumber == null && secondNumber == null) {
			return true;
		}
		
		if(oneNumber == null && secondNumber != null) {
			return false;
		}
		
		if(oneNumber != null && secondNumber == null) {
			return false;
		}
		
		if(oneNumber.compareTo(secondNumber) == 0 ) {
			return true;
		}else {
			return false;
		}
	}
	
	public static Boolean sameNumbers(Integer oneNumber, Integer secondNumber) {
		if(oneNumber == null && secondNumber == null) {
			return true;
		}
		
		if(oneNumber == null && secondNumber != null) {
			return false;
		}
		
		if(oneNumber != null && secondNumber == null) {
			return false;
		}
		
		if(oneNumber.compareTo(secondNumber) == 0 ) {
			return true;
		}else {
			return false;
		}
	}
	
	public static Boolean sameNumbers(Long oneNumber, Long secondNumber) {
		if(oneNumber == null && secondNumber == null) {
			return true;
		}
		
		if(oneNumber == null && secondNumber != null) {
			return false;
		}
		
		if(oneNumber != null && secondNumber == null) {
			return false;
		}
		
		if(oneNumber.compareTo(secondNumber) == 0 ) {
			return true;
		}else {
			return false;
		}
	}
	
	public static Boolean sameTexts(String oneText, String secondText) {
		if(oneText == null && secondText == null) {
			return true;
		}
		
		if(oneText == null && secondText != null) {
			return false;
		}
		
		if(oneText != null && secondText == null) {
			return false;
		}
		
		return oneText.equals(secondText);
	}
	
	public static <T extends ValueObject<T>>Boolean sameLists(List<T> oneList, List<T> secondList){
		if(oneList == null && secondList == null) {
			return true;
		}
		
		if(oneList == null && secondList != null) {
			return false;
		}
		
		if(oneList != null && secondList == null) {
			return false;
		}
		
		if(oneList.size() != secondList.size()) {
			return false;
		}
		
		for(int i = 0; i < oneList.size(); i++) {
			if(!oneList.contains(secondList.get(i))) {
				return false;
			}
		}
		
		return true;
	}
	
	protected int compare(char one, char two) {
		if(CharIndex.get(one).getIndex() > CharIndex.get(two).getIndex()) {
			return 1;
		}
		
		if(CharIndex.get(one).getIndex() < CharIndex.get(two).getIndex()) {
			return -1;
		}
		
		if(CharIndex.get(one).getIndex() == CharIndex.get(two).getIndex()) {
			return 0;
		}
		
		return 0;
	}
}
