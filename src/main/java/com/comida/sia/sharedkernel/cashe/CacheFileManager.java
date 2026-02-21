package com.comida.sia.sharedkernel.cashe;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.util.Date;

import com.comida.sia.sharedkernel.period.DailyPeriod;
import com.comida.sia.sharedkernel.period.MonthlyPeriod;
import com.comida.sia.sharedkernel.period.Period;
import com.comida.sia.sharedkernel.period.PeriodType;
import com.comida.sia.sharedkernel.period.QuarterlyPeriod;
import com.comida.sia.sharedkernel.period.WeeklyPeriod;

public class CacheFileManager {
	private static String FILE_PATH = "C:\\sia\\";
	
	private String relativePath;
	private String fileName;
	private FileType fileType;
	private PeriodType validPeriodType;
		
	public CacheFileManager(String relativePath, String fileName, FileType fileType, PeriodType validPeriodType) {
		this.relativePath = relativePath;
		this.fileName = fileName;
		this.fileType = fileType;
		this.validPeriodType = validPeriodType;
	}
	
	public void writeFile(String cashedData) throws IOException {
		createDictionary();
	    BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH + relativePath + fileName + "." + fileType.getName()));
	    writer.write(cashedData);
	    
	    writer.close();
	}
	
	private void createDictionary() {
		File directory = new File(FILE_PATH + relativePath);	
		if(!directory.exists()) {
			directory.mkdirs();
		}
	}
	
	public String readFile() throws IOException {	     
		BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH + relativePath + fileName + "." + fileType.getName()));
				
		StringBuilder stringBuilder = new StringBuilder();
		String line = null;

		while ((line = reader.readLine()) != null) {
			if(FileType.JSON.equals(fileType)) {
				stringBuilder.append(line);
			} else {
				stringBuilder.append(line + "\r\n");
			}
			
		}
		
		reader.close();
		
		return stringBuilder.toString();
	}
	
	public void deleteFile() throws IOException {
		if(fileExists()) {
			Files.delete(getPath());
		}
	}
	
	public Boolean canReadCashe() throws IOException {
		if(fileNotExists()) {
			return false;
		}
		
		if(fileIsOutdated()) {
			return false;
		}
		
		return true;
	}
	
	private Boolean fileExists() {
		return Files.exists(getPath());
	}
	
	private Boolean fileNotExists() {
		return !fileExists();
	}
	
	private Boolean fileIsOutdated() throws IOException {
		return !(getPeriod(getCreationDate()).isCurrent());
	}
	
	private Date getCreationDate() throws IOException {
		FileTime creationTime = (FileTime) Files.getAttribute(getPath(), "lastModifiedTime"); 
		return new Date(creationTime.toMillis());	
	}
	
	private Path getPath() {
		return Paths.get(FILE_PATH + relativePath + fileName + "." + fileType.getName());
	}
	
	private Period getPeriod(Date date) {
		switch (validPeriodType){
			case QUARTERLY:
				return new QuarterlyPeriod(date);
			case MONTHLY:
				return new MonthlyPeriod(date);
			case WEEKLY:
				return new WeeklyPeriod(date);
			case DAILY:
				return new DailyPeriod(date);
			default:
				throw new IllegalArgumentException("Not supported period type: " + validPeriodType);
		}
	}
}
