package com.comida.sia.sharedkernel.cashe;

import com.comida.sia.sharedkernel.AssertionConcern;

public abstract class CacheAssembler {
	private static String FILE_ROOT_PATH = "C:\\sia\\";
	private String relativePath;
	
	public CacheAssembler(String relativePath) {
		setRelativePath(relativePath);
	}
	
	private void setRelativePath(String relativePath) {
		AssertionConcern.assertNotEmpty(relativePath, "Relative paht of cache files must be provided in order to create cache");
		this.relativePath = relativePath;
	}
	
	protected String getAbsolutFilePath() {		
		if(relativePath.endsWith("\\")) {
			return FILE_ROOT_PATH + relativePath;
		} else {
			return FILE_ROOT_PATH + relativePath + "\\";
		}
	}
	
	protected String getRelativePath() {
		return relativePath;
	}
}
