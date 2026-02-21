package com.comida.sia.intelligence.insidertransactions.adapter.acquirer;

import com.comida.sia.sharedkernel.GenericBuilder;
import com.comida.sia.sharedkernel.cashe.Cache;
import com.comida.sia.sharedkernel.cashe.CacheAssembler;
import com.comida.sia.sharedkernel.cashe.CacheIndexFile;
import com.comida.sia.sharedkernel.cashe.CacheSingleFile;
import com.comida.sia.sharedkernel.cashe.FileType;
import com.comida.sia.sharedkernel.period.InfinitePeriod;

public class IntelligenceCacheAssembler extends CacheAssembler{

	public IntelligenceCacheAssembler(String relativePath) {
		super(relativePath);
	}
	
	//Insider transactions
	public Cache assemblyInsidersTransactionCache() {
		return GenericBuilder.of(CacheSingleFile::new)
				.with(CacheSingleFile::setRelativePath, getRelativePath())
				.with(CacheSingleFile::setCacheMetaFileName, "insiders_transactions_index")
				.with(CacheSingleFile::setCasheMetaFile, 
						GenericBuilder.of(CacheIndexFile::new)
							.with(CacheIndexFile::setName, "insiders_transactions")
							.with(CacheIndexFile::setPath, getAbsolutFilePath())
							.with(CacheIndexFile::setFileType, FileType.JSON)
							.with(CacheIndexFile::setFileLifeCyclePeriod, new InfinitePeriod())
							.with(CacheIndexFile::setForceRead, false)
							.build())
				.build();
	}
	
	public Cache assemblyForceReadInsidersTransactionCache() {
		return GenericBuilder.of(CacheSingleFile::new)
				.with(CacheSingleFile::setRelativePath, getRelativePath())
				.with(CacheSingleFile::setCacheMetaFileName, "insiders_transactions_index")
				.with(CacheSingleFile::setCasheMetaFile, 
						GenericBuilder.of(CacheIndexFile::new)
							.with(CacheIndexFile::setName, "insiders_transactions")
							.with(CacheIndexFile::setPath, getAbsolutFilePath())
							.with(CacheIndexFile::setFileType, FileType.JSON)
							.with(CacheIndexFile::setFileLifeCyclePeriod, new InfinitePeriod())
							.with(CacheIndexFile::setForceRead, true)
							.build())
				.build();
	}

}
