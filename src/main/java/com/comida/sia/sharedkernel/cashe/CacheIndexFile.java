package com.comida.sia.sharedkernel.cashe;

import java.io.IOException;
import java.text.ParseException;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.period.InfinitePeriod;
import com.comida.sia.sharedkernel.period.Period;
import com.comida.sia.sync.supervision.domain.model.Direction;
import com.comida.sia.sync.supervision.domain.model.WaterMark;

import lombok.Getter;

/**
 * Klasa ogarnia, czy plik cache jest aktualny i czy dane w tym pliku są aktualne.
 * 
 * Jeżeli plik cache jest aktualny i dane są aktualne , to
 * 		- odczyt jest możliwy
 * 		- zapis jest niemożliwy (prawdopodobnie brak nowych danych u źródła
 * 
 * Jeżeli plik cache jest aktualny i dane są nieaktualne, to
 * 		- odczyt nie ma sensu, ponieważ istnieją nowe dane u źródła
 * 		- zapis danych do pliku jest możliwy
 * 
 * Jeżeli plik cache nie jest aktualny i dane są aktualne, to
 * 		- odczyt jest możliwy (tutaj w grę może wchodzić odtwarzanie danych z łańcucha plików cache
 * 		- zapis jest niemożliwy (jeżeli już to w nowym pliku cache w łańcuchu)
 * 
 * Jeżeli plik cache nie jest aktualny i dane nie są aktualne, to
 * 		znaczy, że jest gruby fuckup - mamy zsynchronizowane dane, które nie były "przepuszczane" przez cache
 * 		- odczyt nie ma sensu, bo nowsze dane są u źródła
 * 		- zapis jest niemożliwy (jeżeli już to w nowym pliku)
 * 
 */
@Getter
public class CacheIndexFile implements IndexFile{	
	
	private String cachePath;
	private String cacheFileName; 
	private FileType fileType;
	private Period fileLifeCyclePeriod;
	private Boolean forceRead;
	
	private CacheWaterMarkLevel waterMarkCurrentLevel;
	
	public CacheIndexFile() {
		super();
		forceRead = false;
	}
	
	public CacheIndexFile(CacheMultiIndexFile metaFile) {
		this.cachePath = metaFile.getCachePath();
		this.cacheFileName = metaFile.getCacheFileName() + "_" + metaFile.getMetaFileListElementLifeCyclePeriod().getFormatted();
		this.fileLifeCyclePeriod = metaFile.getMetaFileListElementLifeCyclePeriod();
		this.fileType = metaFile.getMetaFileListElementType();
		this.forceRead = metaFile.getForceRead();
	}
	
	public CacheIndexFile(String path, String name, FileType fileType) {
		setPath(path);
		setName(name);
		setFileType(fileType);
		
		fileLifeCyclePeriod = new InfinitePeriod();
	}
	
	public CacheIndexFile(Period period, String path, String name, FileType fileType) {
		setPath(path);
		setName(name);
		setFileType(fileType);
		
		setFileLifeCyclePeriod(period);
	}
	
	public void setName(String name) {
		AssertionConcern.assertNotEmpty(name, "Name must be provided in order to create Cache Index File");
		this.cacheFileName = name;
	}
	
	public void setPath(String path) {
		AssertionConcern.assertNotEmpty(path, "Path to cache file must be provided in order to create Cache Index File");
		this.cachePath = path;
	}
	
	public void setFileType(FileType fileType) {
		AssertionConcern.assertNotNull(fileType, "Cache file must have defined format.");
		this.fileType = fileType;
	}
	
	public void setFileLifeCyclePeriod(Period fileLifeCyclePeriod) {
		AssertionConcern.assertNotNull(fileLifeCyclePeriod, "File lifecycle period is mandatory in order to create cache file");
		this.fileLifeCyclePeriod = fileLifeCyclePeriod;
	}
	
	public void setForceRead(Boolean forceRead) {
		AssertionConcern.assertNotNull(forceRead, "Force read parametr must be provided in order to create cache file");
		this.forceRead = forceRead;
	}
	
	protected void update(CacheWaterMarkLevel cacheWaterMarkLevel) {
		AssertionConcern.assertNotNull(cacheWaterMarkLevel, "Cache water mark level must not be null while its update");
		this.waterMarkCurrentLevel = cacheWaterMarkLevel;
	}
	
	protected void reset() {
		this.waterMarkCurrentLevel = null;
	}
	
	@Override
	public String readFile(WaterMark waterMarkLevel) throws CacheDataNotExistsException, ObsoleteCashFileException, ObsoleteCashDataException, IOException  {
		assertCanRead(waterMarkLevel);
		return getRepository().read();
	}
	
	protected void assertCanRead(WaterMark waterMarkLevel) throws ObsoleteCashDataException, CacheDataNotExistsException {
		if(cacheDataNotExists()) {
			throw new CacheDataNotExistsException("Cache file doesn't exists");
		}
		
		if(NotForceRead() && isDataObsolete(waterMarkLevel)) {
			throw new ObsoleteCashDataException("Obsolete cash data in the file");
		}
	}
	
	private Boolean NotForceRead() {
		return !forceRead;
	}
	
	@Override
	public void writeFile(WaterMark waterMarkLevel, String content) throws ObsoleteCashFileException, LackOfNewDataException, IOException, ParseException {
		assertCanWrite(waterMarkLevel);
		getRepository().delete();
		getRepository().write(content);
		
		update(new CacheWaterMarkLevel(waterMarkLevel));
	}
	
	@Override
	public void update(WaterMark waterMarkLevel) throws ParseException {	
		update(new CacheWaterMarkLevel(waterMarkLevel));
	}
	@Override
	public void clear() throws IOException {
		getRepository().delete();
		reset();
	}
	
	protected FileRepository getRepository() {
		switch(fileType) {
			case JSON:
				return buildJsonFileRepository();
			case CSV:
				return buildCsvFileRepository();
			default:
				throw new IllegalArgumentException("Not supported file type");
		}
	}
	
	private JsonFileRepository buildJsonFileRepository() {
		return new JsonFileRepository(
				cachePath, 
				cacheFileName);
	}
	
	private CsvFileRepository buildCsvFileRepository() {
		return new CsvFileRepository(
				cachePath, 
				cacheFileName);
	}
	
	protected void assertCanWrite(WaterMark waterMarkLevel) throws ObsoleteCashFileException, LackOfNewDataException {
		if(isFileNotObsolete()) {
			if(cacheDataExists() && isDataNotObsolete(waterMarkLevel)) {
				throw new LackOfNewDataException("No data expected in the source");
			}
		}
		
		if(isFileObsolete()) {
			throw new ObsoleteCashFileException("Cache file is obsolete");
		}
	}
	
	private boolean cacheDataNotExists() {
		return !cacheDataExists();
	}
	
	private boolean cacheDataExists() {
		if(this.waterMarkCurrentLevel == null) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public Boolean isFileObsolete() {
		if(fileLifeCyclePeriod.distanceToCurrentPeriod() >= 0) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public Boolean isDataObsolete(WaterMark waterMarkLevel) { 		
		if(waterMarkLevel == null || waterMarkLevel.calculateLevel() == null) {
			return false;
		} else {
			if(Direction.ASCENDING.equals(this.waterMarkCurrentLevel.getOrder())) {
				if(this.waterMarkCurrentLevel.isBelow(waterMarkLevel) || this.waterMarkCurrentLevel.sameLevel(waterMarkLevel) ){
					return true;
				} else {
					return false;
				}
			} 

			if(Direction.DESCENDING.equals(this.waterMarkCurrentLevel.getOrder())) {
				if(this.waterMarkCurrentLevel.isAbove(waterMarkLevel) || this.waterMarkCurrentLevel.sameLevel(waterMarkLevel) ){
					return false;
				} else {
					return true;
				}
			}
			
			return false;
		}
	}
}
