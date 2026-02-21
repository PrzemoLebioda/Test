package com.comida.sia.intelligence.news.port.acquirer;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.comida.sia.sharedkernel.TranslationConcern;
import com.comida.sia.sync.supervision.domain.model.Direction;
import com.comida.sia.sync.supervision.domain.model.WaterMark;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedItem extends WaterMark {
	private String title;
	private String url;
	private String time_published;
	private List<String> authors;
	private String summary;
	private String banner_image;
	private String source;
	private String category_within_source;
	private String source_domain;
	private List <TopicItem> topics;
	private BigDecimal overall_sentiment_score;
	private String overall_sentiment_label;
	private List<TickerSentimentItem> ticker_sentiment;
	
	public FeedItem() {
		super(Direction.ASCENDING);
	}
	
	@Override
	public String calculateLevel() {
		String level = "";
		try {
			level = calculateTimePublishedMark() + 
					calculateSourceMark() + 
					calculateTitleMark();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return level;
	}
	
	private String calculateTimePublishedMark() throws ParseException {
		return TranslationConcern.fillLeadingZeros(TranslationConcern.getConventer().convertToAlphaNumeric(TranslationConcern.getDateFrom(time_published)), 10);
	}
	
	private String calculateSourceMark() {
		return TranslationConcern.fillLeadingZeros(this.source, 10);
	}
	
	private String calculateTitleMark() {
		return TranslationConcern.fillFollowingZeros(this.title, 45);
	}
	
	@Override
	public Date occuranceTime() throws ParseException {
		return TranslationConcern.getDateFrom(time_published);
	}

	@Override
	public String toString() {
		return "FeedItem [time_published=" + time_published + ", source=" + source + ", title=" + title + "]";
	}

	

}
