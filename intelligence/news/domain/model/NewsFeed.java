package com.comida.sia.intelligence.news.domain.model;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.ComparationConcern;
import com.comida.sia.sharedkernel.TranslationConcern;
import com.comida.sia.sharedkernel.domain.ValueObject;
import com.comida.sia.sync.supervision.domain.model.TimeSeries;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity(name = "NewsFeed")
@Table(name = "intelligence_news_feeds",
		indexes = {
				@Index(name = "news_feeds_source_idx", columnList = "source")
		})
public class NewsFeed implements ValueObject<NewsFeed>, TimeSeries {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -311473574791414501L;
	
	@Id
	@Column(name = "id", nullable = false)
	@Getter private UUID id;
	
	@Column(name = "title", columnDefinition="text", nullable = false)
	@Getter private String title;
	
	@Column(name = "url", columnDefinition="text", nullable = false)
	@Getter private String url;
	
	@Column(name = "time_published", nullable = false)
	@Getter private Date timePublished;
	
	@OneToMany(
			fetch = FetchType.EAGER,
			cascade = CascadeType.ALL,
			orphanRemoval = true
		)
    @JoinColumn(name = "news_feed_id")
	private List<Author> authors;
	
	@Column(name = "summary", columnDefinition="text", nullable = false)
	@Getter private String summary;
	
	@Column(name = "banner_image", columnDefinition="text", nullable = true)
	@Getter private String bannerImage;
	
	@Column(name = "source", nullable = false)
	@Getter private String source;
	
	@Column(name = "category_within_source", nullable = false)
	@Getter private String categoryWithinSource;
	
	@Column(name = "source_domain", nullable = false)
	@Getter private String sourceDomain;
	
	@OneToMany(
			fetch = FetchType.EAGER,
			cascade = CascadeType.ALL,
			orphanRemoval = true
		)
    @JoinColumn(name = "news_feed_id")
	private List <Topic> topics;
	
	@Embedded
	@AttributeOverrides({
		  @AttributeOverride( name = "sentiment_score", column = @Column(name = "overall_sentiment_score", columnDefinition = "numeric(34,6)"))
		})
	@Getter private SentimentScore overallSentimentScore;
	
	@OneToMany(
			fetch = FetchType.EAGER,
			cascade = CascadeType.ALL,
			orphanRemoval = true
		)
    @JoinColumn(name = "news_feed_id")
	private List<TickerSentiment> tickerSentiments;
	
	public NewsFeed() {
		
	}
	
	public NewsFeed(Builder builder) {
		this.id = builder.id;
		this.title = builder.title;
		this.url = builder.url;
		this.timePublished = builder.timePublished;
		this.authors = builder.authors;
		this.summary = builder.summary;
		this.bannerImage = builder.bannerImage;
		this.source = builder.source;
		this.categoryWithinSource = builder.categoryWithinSource;
		this.sourceDomain = builder.sourceDomain;
		this.topics = builder.topics;
		this.overallSentimentScore = builder.overallSentimentScore;
		this.tickerSentiments = builder.tickerSentiments;
	}
	
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	
	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}
	
	public void setTickerSentiments(List<TickerSentiment> tickerSentiments) {
		//this.assertNotEmpty(tickerSentiments, "Ticker sentiments must be provided");
		this.tickerSentiments = tickerSentiments;
	}
	
	@Override
	public Date occuranceTime() {
		return timePublished;
	}
	@Override
	public boolean sameValueAs(NewsFeed other) {
		if(other != null) {
			return  ComparationConcern.sameTexts(this.title, other.title) &&
					ComparationConcern.sameTexts(this.url, other.url) &&
					ComparationConcern.sameDates(timePublished, other.timePublished) &&
					ComparationConcern.sameLists(this.authors, other.authors) &&
					ComparationConcern.sameTexts(this.summary, other.summary) &&
					ComparationConcern.sameTexts(this.bannerImage, other.bannerImage) &&
					ComparationConcern.sameTexts(this.source, other.source) &&
					ComparationConcern.sameTexts(this.categoryWithinSource, other.categoryWithinSource) &&
					ComparationConcern.sameTexts(this.sourceDomain, other.sourceDomain) &&
					ComparationConcern.sameLists(this.topics, other.topics) &&
					this.overallSentimentScore.sameValueAs(other.overallSentimentScore) &&
					ComparationConcern.sameLists(this.tickerSentiments, other.tickerSentiments);
		}else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(authors, bannerImage, categoryWithinSource, id, overallSentimentScore, source, sourceDomain,
				summary, tickerSentiments, timePublished, title, topics, url);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NewsFeed other = (NewsFeed) obj;
		return this.sameValueAs(other);
	}

	@Override
	public String toString() {
		return "NewsFeed [id=" + id + ", title=" + title + ", url=" + url + ", timePublished=" + timePublished + ", authors=" + authors
				+ ", summary=" + summary + ", bannerImage=" + bannerImage + ", source=" + source
				+ ", categoryWithinSource=" + categoryWithinSource + ", sourceDomain=" + sourceDomain + ", topics="
				+ topics + ", overallSentimentScore=" + overallSentimentScore + ", tickerSentiment=" + tickerSentiments
				+ "]";
	}
	
	public static class Builder {
		private UUID id;
		private String title;
		private String url;
		private Date timePublished;
		private List<Author> authors;
		private String summary;
		private String bannerImage;
		private String source;
		private String categoryWithinSource;
		private String sourceDomain;
		private List <Topic> topics;
		private SentimentScore overallSentimentScore;
		private List<TickerSentiment> tickerSentiments;
		
		public Builder(UUID id) {
			this.setId(id);
		}
		
		private void setId(UUID id) {
			AssertionConcern.assertNotNull(id, "Id of newws feed must be provided");
			this.id = id;
		}
		
		public Builder setTitle(String title) {
			AssertionConcern.assertNotEmpty(title, "Title must be provided");
			this.title = title;
			
			return this;
		}
		
		public Builder setUrl(String url) {
			this.url = url;
			
			return this;
		}
		
		public Builder setTimePublished(String timePublished) throws ParseException {
			AssertionConcern.assertNotNull(timePublished, "Publication time must be provided");
			this.timePublished = TranslationConcern.getDateFrom(timePublished);;
			
			return this;
		}
		
		public Builder setAuthors(List<Author> authors) {
			this.authors = authors;
			
			return this;
		}
		
		public Builder setSummary(String summary) {
			this.summary = summary;
			
			return this;
		}
		
		public Builder setBannerImage(String bannerImage) {
			this.bannerImage = bannerImage;
			
			return this;
		}
		
		public Builder setSource(String source) {
			AssertionConcern.assertNotEmpty(source, "Source must be provided");
			this.source = source;
			
			return this;
		}
		
		public Builder setCategoryWithinSource(String categoryWithinSource) {
			this.categoryWithinSource = categoryWithinSource;
			
			return this;
		}
		
		public Builder setSourceDomain(String sourceDomain) {
			this.sourceDomain = sourceDomain;
			
			return this;
		}
		
		public Builder setTopics(List<Topic> topics) {
			this.topics = topics;
			
			return this;
		}
		
		public Builder setOverallSentimentScore(BigDecimal overallSentimentScore) {
			AssertionConcern.assertNotNull(overallSentimentScore, "Overall sentiment score must be provided");
			
			this.overallSentimentScore = new SentimentScore(overallSentimentScore);
			
			return this;
		}
		
		public Builder setTickerSentiments(List<TickerSentiment> tickerSentiments) {
			//AssertionConcern..assertNotEmpty(tickerSentiments, "Ticker sentiments must be provided");
			this.tickerSentiments = tickerSentiments;
			
			return this;
		}
		
		public NewsFeed build() {
			return new NewsFeed(this);
		}
	}
}
