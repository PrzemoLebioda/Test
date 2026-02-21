package com.comida.sia.sharedkernel;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.comida.sia.sharedkernel.text.NumericSystemConverter;

import jakarta.persistence.Transient;

public class TranslationConcern extends ComparationConcern {
	
	@Transient
	private SimpleDateFormat dateFormatter;
	
	@Transient
	private SimpleDateFormat dateTimeFormatter;
	
	@Transient
	private SimpleDateFormat dateTSeparateTimeFormatter;
	
	

	public TranslationConcern(){
		super();
	}
	
	private static SimpleDateFormat getDateFormatter() {		
		return new SimpleDateFormat("yyyy-MM-dd");
	}
	
	private static SimpleDateFormat getDateTSeparateTimeFormatter() {
		return new SimpleDateFormat("yyyyMMdd HHmmss");
	}
	
	private static SimpleDateFormat getDateTimeFormatter() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	public static NumericSystemConverter getConventer() {
		return new NumericSystemConverter();
	}
	
	public static String fillLeadingZeros(String stringMark, int lenght) {
		String calculatedMark = "";
		String puryfiedStringMark = stringMark.replaceAll("\\s", "");
		
		if(puryfiedStringMark.length() >= lenght) {
			calculatedMark = puryfiedStringMark.substring(0, lenght);
		} else {
			String leadingZero = "0";
			calculatedMark = leadingZero.repeat(lenght - puryfiedStringMark.length()) + puryfiedStringMark;
		}
		
		return calculatedMark.toLowerCase();
	}
	
	public static String fillFollowingZeros(String stringMark, int lenght) {
		String calculatedMark = "";
		String puryfiedStringMark = stringMark.replaceAll("\\s", "");
		
		if(puryfiedStringMark.length() >= lenght) {
			calculatedMark = puryfiedStringMark.substring(0, lenght);
		} else {
			String followingZero = "0";
			calculatedMark = puryfiedStringMark + followingZero.repeat(lenght - puryfiedStringMark.length());
		}
		
		return calculatedMark.toLowerCase();
	}
	
	public static Date getMidnightAt(String date) throws ParseException {
		return getMidnightAt(getDateFrom(date));
	}
	
	public static Date getMidnightAt(Date date) {
		if(date == null) {
			return null;
		}
		
		return Date.from(
				LocalDateTime
					.ofInstant(date.toInstant(), ZoneId.systemDefault())
					.withHour(0)
					.withMinute(0)
					.withSecond(0)
					.withNano(0)
					.atZone(ZoneId.systemDefault())
					.toInstant());
	}
	
	public static Date getMilisecondBeforeMidnightAt(String date) throws ParseException {
		return getMilisecondBeforeMidnightAt(getDateFrom(date));
	}
	
	public static Date getMilisecondBeforeMidnightAt(Date date) {
		if(date == null) {
			return null;
		}
		
		return Date.from(
					LocalDateTime
						.ofInstant(date.toInstant(), ZoneId.systemDefault())
						.withHour(23)
						.withMinute(59)
						.withSecond(59)
						.withNano(999999999)
						.atZone(ZoneId.systemDefault())
						.toInstant());
	}
	
	public static Date getDateTimeFrom(String dateTime) throws ParseException {
		if(dateTime == null || dateTime.isBlank()) {
			return null;
		}
		
		String extractedDateTimeString = extractDateTimeString(dateTime);
		
		if(extractedDateTimeString.length() == 10) {
			return extractedDateTimeString.equals("None") || extractedDateTimeString.equals("null") ? null : getDateFormatter().parse(extractedDateTimeString);
		} else if (extractedDateTimeString.contains("T") && extractedDateTimeString.length() == 15) {
			return extractedDateTimeString.equals("None") || extractedDateTimeString.equals("null") ? null : getDateTSeparateTimeFormatter().parse(extractedDateTimeString.replace('T', ' '));
		} else {
			return extractedDateTimeString.equals("None") || extractedDateTimeString.equals("null") ? null : getDateTimeFormatter().parse(extractedDateTimeString);
		}
		
		
	}
	
	public static Date getDateFrom(String date) throws ParseException {
		if(date == null || date.isBlank()) {
			return null;
		}
		
		String extractedDateTimeString = extractDateTimeString(date);
		
		if(extractedDateTimeString.length() == 10) {
			return extractedDateTimeString.equals("None") || extractedDateTimeString.equals("null") ? null : getDateFormatter().parse(extractedDateTimeString);
		} else if (extractedDateTimeString.contains("T") && extractedDateTimeString.length() == 15) {
			return extractedDateTimeString.equals("None") || extractedDateTimeString.equals("null") ? null : getDateTSeparateTimeFormatter().parse(extractedDateTimeString.replace('T', ' '));
		} else {
			return extractedDateTimeString.equals("None") || extractedDateTimeString.equals("null") ? null : getDateTimeFormatter().parse(extractedDateTimeString);
		}
	}
	
	public static String extractDateTimeString(String date) {
		//Pattern dateTimePattern1 = Pattern.compile("\\d{4}-\\d{2}-\\d{2}T\\d{2 }");
		Pattern dateTimePattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}T\\d{6}");
		Pattern datePattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
		
		//Matcher dateTimeMatcher = dateTimePattern.matcher(date.replaceAll("\\s", "").replaceAll("[\n\t\s ]", "").replace("\\n", "").replace("--", "-"));
		Matcher dateTimeMatcher = dateTimePattern.matcher(date.replaceAll("\\r\\n", "").replaceAll("\\n", "").replaceAll("\\s", "").replaceAll(" ", "").replaceAll("--", "-"));
		
		if (dateTimeMatcher.find()) {                                  
			return dateTimeMatcher.group().replaceAll("\\r\\n", "").replaceAll("\\n", "").replaceAll("\\s", "").replaceAll(" ", "").replaceAll("--", "-");
		}
		
		Matcher dateMatcher = datePattern.matcher(date);
		
		if(dateMatcher.find()) {
			return dateMatcher.group().replaceAll("\\r\\n", "").replaceAll("\\n", "").replaceAll("\\s", "").replaceAll(" ", "").replaceAll("--", "-");
		}
		
		return date.replaceAll("\\r\\n", "").replaceAll("\\n", "").replaceAll("\\s", "").replaceAll(" ", "").replaceAll("--", "-");
	}
	
	public static BigDecimal getNumberFrom(String number) {
		if(number == null || number.isEmpty()) {
			return null;
		}
		
		return number.equals("None") || number.equals("null") ? null : new BigDecimal(number);
	}
	
	public static Integer getIntegerFrom(String number) {
		if(number == null || number.isEmpty()) {
			return null;
		}
		
		return Integer.valueOf(number);
	}
	
	public static Long getLongFrom(String number) {
		if(number == null || number.isEmpty()) {
			return null;
		}
		
		return Long.valueOf(number);
	}
	
	public static String getFormattedDate(String pattern, Date date) {
		if(date == null || pattern == null) {
			return null;
		}
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(date);
	}
}
