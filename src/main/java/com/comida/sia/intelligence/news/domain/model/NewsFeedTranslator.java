package com.comida.sia.intelligence.news.domain.model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.comida.sia.intelligence.news.port.acquirer.FeedItem;
import com.comida.sia.intelligence.news.port.acquirer.TickerSentimentItem;
import com.comida.sia.intelligence.news.port.acquirer.TopicItem;
import com.comida.sia.sync.supervision.domain.model.ModelTranslator;

public class NewsFeedTranslator implements ModelTranslator<FeedItem, NewsFeed>{

	@Override
	public NewsFeed translate(FeedItem source) throws ParseException {
		NewsFeed newsFeed = new NewsFeed.Builder(UUID.randomUUID())
				.setTitle(source.getTitle())
				.setUrl(source.getUrl())
				.setTimePublished(source.getTime_published())
				.setSummary(source.getSummary())
				.setBannerImage(source.getBanner_image())
				.setSource(source.getSource())
				.setCategoryWithinSource(source.getCategory_within_source())
				.setSourceDomain(source.getSource_domain())
				.setOverallSentimentScore(source.getOverall_sentiment_score())
				.build();
		
		newsFeed.setAuthors(getAuthors(newsFeed, source.getAuthors()));
		newsFeed.setTopics(getTopics(newsFeed, source.getTopics()));
		newsFeed.setTickerSentiments(getTickerSentiments(newsFeed, source.getTicker_sentiment()));
		
		return newsFeed;
	}
	
	private List<Author> getAuthors(NewsFeed newsFeed, List<String> ghateredAuthors){
		List<Author> authors = new ArrayList<Author>();
		
		for(String author : ghateredAuthors) {
			authors.add(new Author(newsFeed, author));
		}
		
		return authors;
	}
	
	private List<Topic> getTopics(NewsFeed newsFeed, List<TopicItem> topicItems){
		List<Topic> topics = new ArrayList<Topic>();
		
		for(TopicItem topicItem : topicItems) {
			topics.add(new Topic(newsFeed, topicItem.getTopic(), topicItem.getRelevance_score()));
		}
		
		return topics;
	}
	
	private List<TickerSentiment> getTickerSentiments (NewsFeed newsFeed, List<TickerSentimentItem> tickerSentimentItems){
		List<TickerSentiment> tickerSentiments = new ArrayList<TickerSentiment>();
		
		for(TickerSentimentItem tickerSentimentItem : tickerSentimentItems) {
			tickerSentiments.add(new TickerSentiment(
					newsFeed,
					tickerSentimentItem.getTicker(), 
					tickerSentimentItem.getRelevance_score(),
					tickerSentimentItem.getTicker_sentiment_score()));
		}
		
		return tickerSentiments;
	}

}
