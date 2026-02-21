package com.comida.sia.intelligence.news.domain.model;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.ComparationConcern;
import com.comida.sia.sharedkernel.domain.ValueObject;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity(name = "TickerSentiment")
@Table(name = "intelligence_news_ticker_sentiments",
		indexes = {
				@Index(name = "ticker_sentiments_ticker_symbol_idx", columnList = "ticker_symbol")
		})
public class TickerSentiment implements ValueObject<TickerSentiment>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6225193376031468062L;
	
	@Id
	@Column(name = "id", nullable = false)
	@Getter private UUID id;
	
	@ManyToOne
    @JoinColumn(name="news_feed_id", nullable=false)
	private NewsFeed newsFeed;
	
	@Column(name = "ticker_symbol", nullable = false)
	@Getter private String tickerSymbol;
	
	@Embedded
	@AttributeOverrides({
		  @AttributeOverride( name = "value", column = @Column(name = "relevance_score", columnDefinition = "numeric(34,6)"))
		})
	@Getter private Score relevanceScore;
	
	@Embedded
	@AttributeOverrides({
		  @AttributeOverride( name = "score", column = @Column(name = "sentiment_score", columnDefinition = "numeric(34,6)"))
		})
	@Getter private SentimentScore sentimentScore;
	
	public TickerSentiment() {
		
	}
	
	public TickerSentiment(NewsFeed newsFeed, String tickerSymbol, Score relevanceScore, SentimentScore sentimentScore) {
		this.id = UUID.randomUUID();
		this.setNewsFeed(newsFeed);
		this.setTickerSymbol(tickerSymbol);
		this.setRelevanceScore(relevanceScore);
		this.setSentimentScore(sentimentScore);
	}
	
	public TickerSentiment(NewsFeed newsFeed, String tickerSymbol, BigDecimal relevanceScore, BigDecimal sentimentScore) {
		this.id = UUID.randomUUID();
		this.setNewsFeed(newsFeed);
		this.setTickerSymbol(tickerSymbol);
		this.setRelevanceScore(relevanceScore);
		this.setSentimentScore(sentimentScore);
	}
	
	private void setNewsFeed(NewsFeed newsFeed) {
		AssertionConcern.assertNotNull(newsFeed, "News feed must be provided");
		this.newsFeed = newsFeed;
	}
	
	private void setTickerSymbol(String tickerSymbol) {
		AssertionConcern.assertNotEmpty(tickerSymbol, "Ticker sybmol for ticker sentiment must be provided");
		this.tickerSymbol = tickerSymbol;
	}
	
	private void setRelevanceScore(Score relevanceScore) {
		AssertionConcern.assertNotNull(relevanceScore, "Relevance score for ticker sentiment must be provided");
		this.relevanceScore = relevanceScore;
	}
	
	private void setRelevanceScore(BigDecimal relevanceScore) {
		AssertionConcern.assertNotNull(relevanceScore, "Relevance score for ticker sentiment must be provided");
		this.relevanceScore = new Score(relevanceScore);
	}
	
	private void setSentimentScore(SentimentScore sentimentScore) {
		AssertionConcern.assertNotNull(sentimentScore, "Sentiment score for ticker sentiment must be provided");
		this.sentimentScore = sentimentScore;
	}
	
	private void setSentimentScore(BigDecimal sentimentScore) {
		AssertionConcern.assertNotNull(sentimentScore, "Sentiment score for ticker sentiment must be provided");
		this.sentimentScore = new SentimentScore(sentimentScore);
	}
	
	@Override
	public boolean sameValueAs(TickerSentiment other) {
		if(other != null) {
			return  ComparationConcern.sameTexts(this.tickerSymbol, other.tickerSymbol) &&
					this.relevanceScore.sameValueAs(other.relevanceScore) &&
					this.sentimentScore.sameValueAs(other.sentimentScore);
		}else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(relevanceScore, sentimentScore, tickerSymbol);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TickerSentiment other = (TickerSentiment) obj;
		return this.sameValueAs(other);
	}

	@Override
	public String toString() {
		return "TickerSentiment [tickerSymbol=" + tickerSymbol + ", relevanceScore=" + relevanceScore
				+ ", sentimentScore=" + sentimentScore + "]";
	}

}
