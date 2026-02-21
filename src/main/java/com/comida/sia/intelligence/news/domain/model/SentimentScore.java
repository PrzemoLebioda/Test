package com.comida.sia.intelligence.news.domain.model;

import java.math.BigDecimal;
import java.util.Objects;

import com.comida.sia.sharedkernel.ComparationConcern;
import com.comida.sia.sharedkernel.domain.ValueObject;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Getter;

@Embeddable
public class SentimentScore extends ComparationConcern implements ValueObject<SentimentScore> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6560103636575403807L;
	
	@Embedded
	@AttributeOverrides({
		  @AttributeOverride( name = "value", column = @Column(name = "sentiment_score", columnDefinition = "numeric(34,6)"))
		})
	@Getter private Score score;
	
	public SentimentScore() {
		
	}
	
	public SentimentScore(Score score) {
		this.setScore(score);
	}
	
	public SentimentScore(BigDecimal score) {
		this.setScore(score);
	}
	
	private void setScore(Score score) {
		assertNotNull(score, "Score value for sentimet score must be provided");
		this.score = score;
	}
	
	private void setScore(BigDecimal value) {
		assertNotNull(value, "Score value for sentimet score must be provided");
		this.score = new Score(value);
	}
	
	/**
	 * x <= -0.35: Bearish; 
	 * -0.35 < x <= -0.15: Somewhat-Bearish;
	 * -0.15 < x < 0.15: Neutral;
	 * 0.15 <= x < 0.35: Somewhat_Bullish;
	 * x >= 0.35: Bullish",
	 */
	public SentimentLevel getLevel() {
		if(score.lessOrEqualsTo(new Score(BigDecimal.valueOf(-0.35)))){
			return SentimentLevel.BEARISH;
		}
		
		if(score.greaterThan(new Score(BigDecimal.valueOf(-0.35))) && score.lessOrEqualsTo(new Score(BigDecimal.valueOf(-0.15)))) {
			return SentimentLevel.SOMEWHAT_BEARISH;
		}
		
		if(score.greaterThan(new Score(BigDecimal.valueOf(-0.15))) && score.lessThan(new Score(BigDecimal.valueOf(0.15)))) {
			return SentimentLevel.NEUTRAL;
		}
		
		if(score.greaterOrEqualsTo(new Score(BigDecimal.valueOf(0.15))) && score.lessThan(new Score(BigDecimal.valueOf(0.35)))) {
			return SentimentLevel.SOMEWHAT_BULLISH;
		}
		
		if(score.greaterOrEqualsTo(new Score(BigDecimal.valueOf(0.35)))){
			return SentimentLevel.BULLISH;
		}
		
		return null;
	}

	@Override
	public boolean sameValueAs(SentimentScore other) {
		if(other != null) {
			return this.score.sameValueAs(other.score);
		}else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(score);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SentimentScore other = (SentimentScore) obj;
		return this.sameValueAs(other);
	}

	@Override
	public String toString() {
		return "SentimentScore [score=" + score + ", getLevel()=" + getLevel() + "]";
	}
}
