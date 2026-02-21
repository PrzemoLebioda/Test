package com.comida.sia.intelligence.news.port.acquirer;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TickerSentimentItem {
	private String ticker;
	private BigDecimal relevance_score;
	private BigDecimal ticker_sentiment_score;
	private String ticker_sentiment_label;
}
