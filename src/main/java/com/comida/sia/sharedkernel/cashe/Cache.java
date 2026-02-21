package com.comida.sia.sharedkernel.cashe;

import java.io.IOException;
import java.text.ParseException;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.messaging.GeneralSerializer;
import com.comida.sia.sync.supervision.domain.model.WaterMark;
/**
 * 
 */
public abstract class Cache{
	 
	private static String FILE_ROOT_PATH = "C:\\sia\\";
	
	private String relativePath;
	private String cacheMetaFileName;
	
	public Cache() {

	}
	
	public void setRelativePath(String relativePath) {
		AssertionConcern.assertNotEmpty(relativePath, "Relative path is mandatory in order to create cache");
		this.relativePath = relativePath;
	}
	
	public void setCacheMetaFileName(String fileName) {
		AssertionConcern.assertNotEmpty(fileName, "Cache meta file name must be provided in order to create cache.");
		this.cacheMetaFileName = fileName;
	}
	
	public abstract String read(WaterMark waterMark) throws ObsoleteCashFileException, ObsoleteCashDataException, IOException, CacheDataNotExistsException;
	public abstract void update(WaterMark waterMark, String content) throws ObsoleteCashFileException, LackOfNewDataException, IOException, ParseException;
	public abstract void update(WaterMark waterMark) throws IOException, ParseException;
	public abstract void clear() throws IOException;
	
	protected FileRepository getCacheMetaFileRepository() {		
		return new JsonFileRepository(getFilePath(), cacheMetaFileName);
	}
	
	protected String getFilePath() {
		if(relativePath.endsWith("\\")) {
			return FILE_ROOT_PATH + relativePath;
		} else {
			return FILE_ROOT_PATH + relativePath + "\\";
		}
	}
	
	protected GeneralSerializer<IndexFile> getSerializer(){
		return new GeneralSerializer<>(true, true);
	}
}
