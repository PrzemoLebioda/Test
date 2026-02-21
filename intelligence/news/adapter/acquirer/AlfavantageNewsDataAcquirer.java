package com.comida.sia.intelligence.news.adapter.acquirer;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.comida.sia.intelligence.news.port.acquirer.News;
import com.comida.sia.intelligence.news.port.acquirer.NewsDataAcquirer;
import com.comida.sia.sharedkernel.TranslationConcern;
import com.comida.sia.sharedkernel.cashe.CacheFileManager;
import com.comida.sia.sharedkernel.cashe.FileType;
import com.comida.sia.sharedkernel.messaging.GeneralSerializer;
import com.comida.sia.sharedkernel.period.PeriodType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("AlfavantageNewsDataAcquirer")
@PropertySource({"classpath:application.properties"})
public class AlfavantageNewsDataAcquirer implements NewsDataAcquirer {

	@Autowired
	private Environment env;
	
	private static String URI = "https://www.alphavantage.co/query?function={functionName}&time_from={timeFrom}&limit={limit}&sort={order}&apikey={apiKeyString}";
	
	private static String FUNCTION = "NEWS_SENTIMENT";
	private static String LIMIT = "1000";
	private static String ORDER = "EARLIEST";
	
	private GeneralSerializer<News> serializer = new GeneralSerializer<>(true, true);
	
	@Override
	public News gatherNews(Date dateFrom) throws IOException {
		Date gatheringFromDate = getGatheringFromDate(dateFrom);
		
		CacheFileManager cacheFileManager = getCacheFileManager(gatheringFromDate);
		News news = null;
		
		if(cacheFileManager.canReadCashe()) {
			news = serializer.deserialize(cacheFileManager.readFile(), News.class);
		
			log.info("News for " + TranslationConcern.getFormattedDate("yyyy-MM-dd_HHmmss", gatheringFromDate) + " read from cashe");
		} else {
			cacheFileManager.deleteFile();
			news = getherFromAcquirer(gatheringFromDate);
			cacheFileManager.writeFile(serializer.serialize(news));

			log.info("News for " + TranslationConcern.getFormattedDate("yyyy-MM-dd_HHmmss", gatheringFromDate) + " read from alfavantage");
		}

		return news;
	}
	
	private CacheFileManager getCacheFileManager(Date dateFrom) {
		String relativePath = "news\\";
		String fileName = TranslationConcern.getFormattedDate("yyyy-MM-dd_HHmmss", dateFrom) + "_news";
		
		CacheFileManager cacheFileManager = new CacheFileManager(relativePath, fileName, FileType.JSON, PeriodType.QUARTERLY);
		
		return cacheFileManager;
	}
	
	private News getherFromAcquirer(Date dateFrom) {
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("functionName", FUNCTION);
		params.put("timeFrom", TranslationConcern.getFormattedDate("yyyyMMdd HHmm", dateFrom).replace(" ", "T"));
		params.put("limit", LIMIT);
		params.put("order", ORDER);
		params.put("apiKeyString", env.getProperty("apiKey"));
		
		RestClient restClient = RestClient.create();
		
		News news = restClient.get()
				.uri(URI, params)
				.retrieve()
				.body(News.class);
		
		news.sortFeeds();
		news.getDateOfFirst();
		news.getDateOfLast();
		
		return news;
	}
	
	private Date getGatheringFromDate(Date dateFrom) {
		
		if(dateFrom == null) {
			Calendar calendar = GregorianCalendar.getInstance();
			calendar.set(Calendar.YEAR, 2022);
			calendar.set(Calendar.MONTH, Calendar.MARCH);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.set(Calendar.HOUR, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			
			return calendar.getTime();
		} else {
			return dateFrom;
		}
	}

}
