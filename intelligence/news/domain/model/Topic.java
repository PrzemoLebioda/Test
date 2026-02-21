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

@Entity(name = "Topic")
@Table(name = "intelligence_news_topics",
		indexes = {
				@Index(name = "topics_content_idx", columnList = "content")
		})
public class Topic implements ValueObject<Topic>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2860079519349730519L;
	
	@Id
	@Column(name = "id", nullable = false)
	@Getter private UUID id;
	
	@ManyToOne
    @JoinColumn(name="news_feed_id", nullable=false)
	private NewsFeed newsFeed;
	
	@Column(name = "content", nullable = false)
	@Getter private String content;
	
	@Embedded
	@AttributeOverrides({
		  @AttributeOverride( name = "value", column = @Column(name = "relevance_score", columnDefinition = "numeric(34,6)"))
		})
	@Getter private Score relevanceScore;
	
	public Topic() {
		
	}
	
	public Topic(NewsFeed newsFeed, String content, Score relevanceScore) {
		this.id = UUID.randomUUID();
		this.setNewsFeed(newsFeed);
		this.setContent(content);
		this.setRelevanceScore(relevanceScore);
	}
	
	public Topic(NewsFeed newsFeed, String content, BigDecimal relevanceScore) {
		this.id = UUID.randomUUID();
		this.setNewsFeed(newsFeed);
		this.setContent(content);
		this.setRelevanceScore(relevanceScore);
	}
	
	private void setNewsFeed(NewsFeed newsFeed) {
		AssertionConcern.assertNotNull(newsFeed, "News feed must be provided");
		this.newsFeed = newsFeed;
	}
	
	private void setContent(String content) {
		AssertionConcern.assertNotEmpty(content, "Content of the topic must be provided");
		this.content = content;
	}
	
	private void setRelevanceScore(Score relevanceScore) {
		AssertionConcern.assertNotNull(relevanceScore, "Relevance score of the topic must be provided");
		this.relevanceScore = relevanceScore;
	}
	
	private void setRelevanceScore(BigDecimal relevanceScore) {
		AssertionConcern.assertNotNull(relevanceScore, "Relevance score of the topic must be provided");
		this.relevanceScore = new Score(relevanceScore);
	}

	@Override
	public boolean sameValueAs(Topic other) {
		if(other != null) {
			return  ComparationConcern.sameTexts(this.content, other.content) &&
					this.relevanceScore.sameValueAs(other.relevanceScore);
		}else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(content, relevanceScore);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Topic other = (Topic) obj;
		return this.sameValueAs(other);
	}

	@Override
	public String toString() {
		return "Topic [content=" + content + ", relevanceScore=" + relevanceScore + "]";
	}
}
