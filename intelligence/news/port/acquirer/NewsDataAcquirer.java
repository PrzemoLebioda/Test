package com.comida.sia.intelligence.news.port.acquirer;

import java.io.IOException;
import java.util.Date;

public interface NewsDataAcquirer {
	public News gatherNews(Date dateFrom) throws IOException;
}
