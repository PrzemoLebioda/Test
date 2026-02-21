package com.comida.sia.sharedkernel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class AssertionConcern {
	
	public static void assertTrue(Boolean object, String message) {
		if(object == null) {
			throw new IllegalArgumentException(message);
		}
		
		if(!object) {
			throw new IllegalArgumentException(message);
		}
	}
	
	public static void assertEquals(Object object, Object another, String message) {
		if((object == null) && (another != null)){
			throw new IllegalArgumentException(message);
		}
		
		if(!object.equals(another)) {
			throw new IllegalArgumentException(message);
		}
	}
	
	public static void assertNotEquals(Object object, Object another, String message) {
		if((object == null) && (another == null)) {
			throw new IllegalArgumentException(message);
		}
		
		if(object != null && object.equals(another)) {
			throw new IllegalArgumentException(message);
		}
		
		if(another != null && another.equals(object)) {
			throw new IllegalArgumentException(message);
		}
	}
	
	public static void assertNotNull(Object object, String message) {
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
	}
	
	public static void assertNull(Object object, String message) {
		if(object != null) {
			throw new IllegalArgumentException(message);
		}
	}
	
	public static void assertNotEmpty(String text, String message) {
		if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException(message);
        }
	}
	
	public static void assertNotContains(String text, String subtext, String message, Object object) {
		if(text != null && text.contains(subtext)) {
			throw new IllegalArgumentException(object.getClass().getSimpleName() + ": " + message);
		}
	}
	
	public static void assertBefore(Date date, Date anotherDate, String message) {
		if((date == null) &&  (anotherDate != null)){
			throw new IllegalArgumentException(message);
		}
		
		if(anotherDate == null) {
			return;
		}
		
		if(date.after(anotherDate)) {
			throw new IllegalArgumentException(message);
		}
	}
	
	public static void assertAfter(Date date, Date anotherDate, String message) {
		if((date == null) &&  (anotherDate != null)){
			throw new IllegalArgumentException(message);
		}
		
		if(anotherDate == null) {
			return;
		}
		
		if(date.before(anotherDate)) {
			throw new IllegalArgumentException(message);
		}
	}
	
	protected void assertGreaterThen(BigDecimal number, BigDecimal another, String message) {
		if (number.compareTo(another) <= 0) {
			throw new IllegalArgumentException(message);
		}
	}
	
	protected void assertGreaterThen(Integer number, Integer another, String message) {
		if (number.compareTo(another) <= 0) {
			throw new IllegalArgumentException(message);
		}
	}
	
	protected void assertGreaterOrEqualsTo(BigDecimal number, BigDecimal another, String message) {
		if (number.compareTo(another) < 0) {
			throw new IllegalArgumentException(message);
		}
	}
	
	public static void assertGreaterOrEqualsTo(Integer number, Integer another, String message) {
		if (number.compareTo(another) < 0) {
			throw new IllegalArgumentException(message);
		}
	}
	
	protected void assertLessThen(BigDecimal number, BigDecimal another, String message) {
		if (number.compareTo(another) >= 0) {
			throw new IllegalArgumentException(message);
		}
	}
	
	protected void assertLessThen(Integer number, Integer another, String message) {
		if (number.compareTo(another) >= 0) {
			throw new IllegalArgumentException(message);
		}
	}
	
	protected void assertLessOrEqualsTo(BigDecimal number, BigDecimal another, String message) {
		if (number.compareTo(another) > 0) {
			throw new IllegalArgumentException(message);
		}
	}
	
	protected void assertLessOrEqualsTo(Integer number, Integer another, String message) {
		if (number.compareTo(another) > 0) {
			throw new IllegalArgumentException(message);
		}
	}
	
	protected void assertModuloEqualsTo(BigDecimal number, BigDecimal divisor, BigDecimal modulo, String message) {
		BigDecimal reminder = number.divideAndRemainder(divisor)[1];
		
		if(reminder.compareTo(modulo) != 0) {
			throw new IllegalArgumentException(message);
		}
	}
	
	public static <T> void assertNotEmpty(List<T> list, String message) throws EmptyListException {
		if((list == null) || list.isEmpty()){
			throw new EmptyListException(message);
		}
	}
	
	protected void assertBelongs(Object object, List<Object> to, String message) throws EmptyListException {
		assertNotEmpty(to, "Empty set");
		
		if(!to.contains(object)) {
			throw new IllegalArgumentException(message);
		}		
	}
}
