package com.comida.sia.sharedkernel.cashe;

import java.io.IOException;
import java.text.ParseException;

import com.comida.sia.sharedkernel.EmptyListException;
import com.comida.sia.sync.supervision.domain.model.WaterMark;

public interface IndexFile {
	/**
	 * 
	 * @param waterMarkLevel
	 * @return
	 * @throws CacheDataNotExistsException
	 * @throws ObsoleteCashFileException
	 * @throws ObsoleteCashDataException
	 * @throws IOException
	 * <p>
	 * Reads data from cache file which water mark level is:
	 * <ul>
	 *  <li><b>below</b> current cached water mark level for <b>ascending</b> order</li>
	 *  <li><b>above</b> current cached water mark level for <b>descending</b> order</li>
	 * </ul> 
	 * </p>
	 * @throws EmptyListException 
	 */
	public String readFile(WaterMark waterMarkLevel) throws CacheDataNotExistsException, ObsoleteCashFileException, ObsoleteCashDataException, IOException;
	
	/**
	 * 
	 * @param waterMarkLevel
	 * @param content
	 * @throws ObsoleteCashFileException
	 * @throws LackOfNewDataException
	 * @throws IOException
	 * @throws ParseException
	 * 
	 * <p>
	 * Writes content to be cached and updates current water mark level with extreme value of cached data's water mark
	 * <ul>
	 * 	<li>max value of water mark for ascending order</li>
	 *  <li>min value of water mark for descending order</li>
	 * </ul>
	 * </p>
	 */
	public void writeFile(WaterMark waterMarkLevel, String content) throws ObsoleteCashFileException, LackOfNewDataException, IOException, ParseException;
	
	public void update(WaterMark waterMarkLevel) throws ParseException;
	/**
	 *
	 * @throws IOException
	 * 
	 * <p>
	 * Deletes all cache files related to the meta file and reset the value of current water mark level
	 * </p>
	 */
	public void clear() throws IOException;
	
	/**
	 * 
	 * @return TRUE if file is not obsolete and FALSE when file is obsolete
	 * <p>
	 * File is considered as obsolete, when its file cycle has been ended, which means the current period (period relevant to current date and time)
	 * is future period in relation to the period when the last cache update took place for ascending order. In case of descending order file is obsolete
	 * when current period is in the past in relation to the period of the last synchronization.
	 * </p>
	 */
	public default Boolean isFileNotObsolete() {
		return !isFileObsolete();
	}
	
	/**
	 * 
	 * @return TRUE if file is obsolete and FALSE when file is not obsolete
	 * <p>
	 * File is considered as obsolete, when its file cycle has been ended, which means the current period (period relevant to current date and time)
	 * is future period in relation to the period when the last cache update took place for ascending order. In case of descending order file is obsolete
	 * when current period is in the past in relation to the period of the last synchronization.
	 * </p>
	 */
	public Boolean isFileObsolete();
	
	/**
	 * 
	 * @param waterMarkLevel
	 * @return FALSE if data in the cache file is not obsolete and FALSE when is obsolete
	 * 
	 * <p>
	 * Data is considered as obsolete, when given water mark level is 
	 * <ul>
	 * 	<li><b>below</b> current cached water mark level for <b>ascending</b> order</li>
	 *  <li><b>above</b> current cached water mark level for <b>descending</b> order</li>
	 * </ul>
	 * </p>
	 * 
	 */
	public default Boolean isDataNotObsolete(WaterMark waterMarkLevel) {
		return !isDataObsolete(waterMarkLevel);
	}
	
	/**
	 * 
	 * @param waterMarkLevel
	 * @return FALSE if data in the cache file is obsolete and FALSE when is not obsolete
	 * 
	 * <p>
	 * Data is considered as obsolete, when given water mark level is 
	 * <ul>
	 * 	<li><b>below</b> current cached water mark level for <b>ascending</b> order</li>
	 *  <li><b>above</b> current cached water mark level for <b>descending</b> order</li>
	 * </ul>
	 * </p>
	 */
	public Boolean isDataObsolete(WaterMark waterMarkLevel);
}
