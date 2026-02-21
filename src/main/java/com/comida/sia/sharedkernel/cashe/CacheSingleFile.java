package com.comida.sia.sharedkernel.cashe;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sync.supervision.domain.model.WaterMark;

import lombok.extern.slf4j.Slf4j;

/**
 * Zarządza plikem z metadanymi pliku cache
 */
@Slf4j
public class CacheSingleFile extends Cache {
 	
	protected IndexFile cacheIndex; 
	
	public CacheSingleFile() {
		super();
	}
	
	public void setCasheMetaFile(CacheIndexFile cacheMetaFile) {
		this.cacheIndex = cacheMetaFile;
	}

	@Override
	public String read(WaterMark waterMark) throws CacheDataNotExistsException, ObsoleteCashFileException, ObsoleteCashDataException, FileNotFoundException, IOException {
		return getCasheFileFromRepo().readFile(waterMark);
	}

	@Override
	public void update(WaterMark waterMark, String content) throws ObsoleteCashFileException, LackOfNewDataException, IOException, ParseException {
		getCasheFileFromRepo().writeFile(waterMark, content);
		savaCasheFile();
	}
	
	@Override
	public void update(WaterMark waterMark) throws IOException, ParseException {
		getCasheFileFromRepo().update(waterMark);
		savaCasheFile();
	}
	
	private void savaCasheFile() throws IOException {
		getCacheMetaFileRepository().write(
			getSerializer()
				.serialize(cacheIndex)
			);
	}
	
	@Override
	public void clear() throws IOException {
		getCacheMetaFileRepository().delete();
		getCasheFileFromRepo().clear();
	}
	
	protected IndexFile getCasheFileFromRepo() throws FileNotFoundException, IOException {
		AssertionConcern.assertNotNull(cacheIndex, "Cache Meta File must be set up");
		
		try {
			cacheIndex = getSerializer()
					.deserialize(
						getCacheMetaFileRepository().read(), 
						CacheIndexFile.class
					);
			
			return cacheIndex;
		} catch(FileNotFoundException e) {
			log.info("Cache index file doesn't exists in file system. Returning current one.");
			return cacheIndex;
		}
		
	}
}
