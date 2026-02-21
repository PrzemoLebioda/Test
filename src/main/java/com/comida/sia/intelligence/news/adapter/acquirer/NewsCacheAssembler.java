package com.comida.sia.intelligence.news.adapter.acquirer;

import java.util.Date;

import com.comida.sia.sharedkernel.GenericBuilder;
import com.comida.sia.sharedkernel.cashe.Cache;
import com.comida.sia.sharedkernel.cashe.CacheAssembler;
import com.comida.sia.sharedkernel.cashe.CacheMultiFile;
import com.comida.sia.sharedkernel.cashe.CacheMultiIndexFile;
import com.comida.sia.sharedkernel.cashe.FileType;
import com.comida.sia.sharedkernel.period.InfinitePeriod;
import com.comida.sia.sharedkernel.period.MonthlyPeriod;

public class NewsCacheAssembler extends CacheAssembler {

	public NewsCacheAssembler(String relativePath) {
		super(relativePath);
	}
	
	public Cache assemblyNewsCache(){
		return GenericBuilder.of(CacheMultiFile::new)
				.with(CacheMultiFile::setRelativePath, getRelativePath())
				.with(CacheMultiFile::setCacheMetaFileName, "news_feed_meta")
				.with(CacheMultiFile::setCasheMetaFile, 
						GenericBuilder.of(CacheMultiIndexFile::new)
							.with(CacheMultiIndexFile::setName, "news_feed")
							.with(CacheMultiIndexFile::setPath, getAbsolutFilePath())
							.with(CacheMultiIndexFile::setFileType, FileType.JSON)
							.with(CacheMultiIndexFile::setFileLifeCyclePeriod, new InfinitePeriod())
							.with(CacheMultiIndexFile::setMetaFileListElementLifeCyclePeriod, new MonthlyPeriod(new Date()))
							.with(CacheMultiIndexFile::setMetaFileListElementType, FileType.JSON)
							.build())
				.build();
	}

}
