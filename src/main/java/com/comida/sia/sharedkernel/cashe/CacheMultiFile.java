package com.comida.sia.sharedkernel.cashe;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.comida.sia.sharedkernel.AssertionConcern;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CacheMultiFile extends CacheSingleFile{

	public CacheMultiFile() {
		super();
	}
	
	@Override
	protected IndexFile getCasheFileFromRepo() throws FileNotFoundException, IOException {
		AssertionConcern.assertNotNull(cacheIndex, "Cache Meta File must be set up");
		
		try {
			cacheIndex = getSerializer()
					.deserialize(
						getCacheMetaFileRepository().read(), 
						CacheMultiIndexFile.class
					);
			
			return cacheIndex;
		} catch(FileNotFoundException e) {
			log.info("Cache index file doesn't exists in file system. Returning current one.");
			return cacheIndex;
		}
		
	}
}
