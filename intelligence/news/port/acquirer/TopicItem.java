package com.comida.sia.intelligence.news.port.acquirer;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopicItem {
	private String topic;
	private BigDecimal relevance_score;
}
