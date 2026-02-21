package com.comida.sia.intelligence.news.port.acquirer;

import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class News {
	private String items;
	private String sentiment_score_definition;
	private String relevance_score_definition;
	List<FeedItem> feed;
	
	public void sortFeeds() {
		Collections.sort(feed);
	}
	
	public String getDateOfFirst(){
		if(feed != null && feed.size() >= 1) {
			return feed.get(0).getTime_published();
		} else {
			return "";
		}
	}
	
	public String getDateOfLast() {
		if(feed != null && feed.size() >= 1) {
			return feed.get(feed.size() - 1).getTime_published();
		} else {
			return "";
		}
		
	}
	
	@Override
	public String toString() {
		return "News [items=" + items + ", sentiment_score_definition=" + sentiment_score_definition
				+ ", relevance_score_definition=" + relevance_score_definition + ", feed=" + feed + "]";
	}
	
	
}
