package com.comida.sia.intelligence.news.port.repository;

import java.util.List;

import com.comida.sia.intelligence.news.domain.model.NewsFeed;

public interface NewsRepository {
	
	void store(NewsFeed newsFeed);
	void update(NewsFeed newsFeed);
	
	List<NewsFeed> getFor(String tickerSybmol);
}
