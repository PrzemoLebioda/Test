package com.comida.sia.sharedkernel.cashe;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.period.Period;
import com.comida.sia.sync.supervision.domain.model.WaterMark;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * This class is a cache meta file selector that meets requirements for reading or writing
 * This selector make sense only when cache consists of more then one file
 * 
 * <p>Read scenarios</p>
 * 
 * Given no cache meta file registered
 * When read operation is performed
 * Then no cache meta file exception is raised
 * 
 * Given one (or more) cache meta file is registered
 * 		AND
 * 		the highest current water mark level of all cache meta file is below the current water mark registered in this selector
 * 		AND
 * 		Sorting order is ASCENDING
 * 		AND
 * 		current water mark level is above the provided water mark level
 * When read operation is performed 
 * Then Inconsistent water mark level exception is raised
 * 
 * Given one (or more) cache meta file registered
 * 		AND
 * 		Sorting order is ASCENDING
 * 		AND
 * 		current water mark level is below the provided water mark level
 * When read operation is performed
 * Then obsolete cache data read attempt exception is raised
 * 
 * Given one (or more) cache meta file is registered
 * 		AND
 * 		Sorting order is ASCENDING
 * 		AND
 * 		current water mark level is above the provided water mark level
 * When read operation is performed
 * Then the first cache meta file with current water mark level above the provided water mark level is found
 * 		AND
 * 		related to found cache meta file cache file has been read
 * 
 * <p>Write scenarios</p>
 * Given one (or more) cache meta file registered
 * 		AND
 * 		Sorting order is ASCENDING
 * 		AND
 * 		current water mark level is above given water mark level
 * When write operation is performed with given water mark level
 * Then Obsolete data write attempt exception is raised
 * 
 * Given no cache meta file registered
 * When write operation is performed with given water mark level
 * Then new cache meta file is created with given water mark level
 * 		AND
 * 		new cache meta file is added to collection of the selector
 * 		AND
 * 		current water mark level is updated with the given one (may be calculation would be better approach)
 * 		AND
 * 		cache file is stored
 * 		AND
 * 		cache meta file is stored
 * 		AND
 * 		selector is stored
 * 
 * Given one (or more) cache meta file registered
 * 		AND
 * 		Sorting order is ASCENDING
 * 		AND
 * 		current water mark level is below given water mark level
 * 		AND
 * 		the last registered cache meta file is NOT OBSOLETE (match to current period)
 * When write operation is performed with given water mark level
 * Then the last cache meta file is gathered
 * 		AND
 * 		related cache file to gathered cache meta file is replaced
 * 
 * Given one (or more) cache meta file registered
 * 		AND
 * 		Sorting order is ASCENDING
 * 		AND
 * 		current water mark level is below given water mark level
 * 		AND
 * 		the last registered cache meta file is OBSOLETE (doesn't match to current period)
 * When write operation is performed with given water mark level
 * Then new cache meta file is created with given water mark level
 * 		AND
 * 		new cache meta file is added to collection of the selector
 * 		AND
 * 		current water mark level is updated with the given one (may be calculation would be better approach)
 * 		AND
 * 		cache file is stored
 * 		AND
 * 		cache meta file is stored
 * 		AND
 * 		selector is stored
 * 
 */
@Slf4j
public class CacheMultiIndexFile extends CacheIndexFile implements IndexFile{
	
	@Getter private Period metaFileListElementLifeCyclePeriod;
	@Getter private FileType metaFileListElementType;
	private List<CacheIndexFile> cacheMetaFileList;
	
	public CacheMultiIndexFile() {
		super();
		cacheMetaFileList = new ArrayList<>();
	}
	
	public void setMetaFileListElementLifeCyclePeriod(Period metaFileListElementLifeCyclePeriod) {
		AssertionConcern.assertNotNull(metaFileListElementLifeCyclePeriod, "");
		this.metaFileListElementLifeCyclePeriod = metaFileListElementLifeCyclePeriod;
	}
	
	public void setMetaFileListElementType(FileType metaFileListElementType) {
		AssertionConcern.assertNotNull(metaFileListElementType, "");
		this.metaFileListElementType = metaFileListElementType;
	}
	
	@Override
	public String readFile(WaterMark waterMarkLevel) throws CacheDataNotExistsException, ObsoleteCashFileException, ObsoleteCashDataException, IOException{
		assertCanRead(waterMarkLevel);
		return getRelevantMetaFileForRead(waterMarkLevel).readFile(waterMarkLevel);
	}
	
	private IndexFile getRelevantMetaFileForRead(WaterMark waterMarkLevel) throws NoRelevantMetaFileFoundException {
		assertCacheMetaFileListNotEmpty();
		
		for(IndexFile metaFile : cacheMetaFileList) {
			
			if(metaFile.isDataNotObsolete(waterMarkLevel)) {
				return metaFile;
			}
		}
		
		throw new NoRelevantMetaFileFoundException();
	}
	
	@Override
	public void writeFile(WaterMark waterMarkLevel, String content) throws ObsoleteCashFileException, LackOfNewDataException, ParseException, IOException  {
		assertCanWrite(waterMarkLevel);
		getMetaFileForWtire(waterMarkLevel).writeFile(waterMarkLevel, content);
	}
	
	private IndexFile getMetaFileForWtire(WaterMark waterMarkLevel) throws ParseException {
		try {
			return getRelevantMetaFileForWrite(waterMarkLevel);
		} catch(NoRelevantMetaFileFoundException e) {
			
			update(new CacheWaterMarkLevel(waterMarkLevel));
			update(metaFileListElementLifeCyclePeriod.next());
			CacheIndexFile cacheMetaFile = new CacheIndexFile(this);
			cacheMetaFileList.add(cacheMetaFile);
			
			return cacheMetaFile;
		}
	}
	
	private IndexFile getRelevantMetaFileForWrite(WaterMark waterMarkLevel) throws NoRelevantMetaFileFoundException {
		assertCacheMetaFileListNotEmpty();
		
		for(IndexFile metaFile : cacheMetaFileList) {
			
			if(metaFile.isFileNotObsolete() && metaFile.isDataObsolete(waterMarkLevel)) {
				return metaFile;
			}
		}
		
		throw new NoRelevantMetaFileFoundException();
	}
	
	private void update(Period period) {
		this.metaFileListElementLifeCyclePeriod = period;
	}
	
	@Override   
	public void clear() throws IOException{
		for(IndexFile cacheMetaFile : cacheMetaFileList) {
			cacheMetaFile.clear();
		}
		
		getRepository().delete();
		
		reset();
	}
	
	private void assertCacheMetaFileListNotEmpty() throws NoRelevantMetaFileFoundException {
		if((cacheMetaFileList == null) || cacheMetaFileList.isEmpty()){
			throw new NoRelevantMetaFileFoundException();
		}
	}
}
