package com.comida.sia.indicators.domain.model.treasuryyeald;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sync.supervision.domain.model.TimeSeries;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity(name = "TreasuryYeald")
@Table(name = "indicators_treasury_yeald",
		indexes = {
				@Index(name = "indicators_treasury_yeald_interval_idx", columnList = "interval"),
				@Index(name = "indicators_treasury_maturity_idx", columnList = "maturity")
		})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class TreasuryYeald implements TimeSeries{
	
	@Id
	@Column(name = "id", nullable = false)
	@Getter private UUID id;
	
	@Column(name = "interval", nullable = false)
	@Enumerated(EnumType.STRING)
	@Getter private Interval interval;
	
	@Column(name = "date", nullable = false)
	@Getter private Date date;
	
	@Column(name = "value", nullable = false)
	@Getter private BigDecimal value;
	
	@Column(name = "maturity", nullable = false)
	@Enumerated(EnumType.STRING)
	@Getter private Maturity maturity;
	
	public TreasuryYeald() {
		super();
	}
	
	public TreasuryYeald(Builder builder) {
		id = builder.id;
		interval = builder.interval;
		date = builder.date;
		value = builder.value;
		maturity = builder.maturity;
	}

	@Override
	public Date occuranceTime() {
		return date;
	}
	
	@Getter
	public static class Builder{
		private UUID id;
		private Interval interval;
		private Date date;
		private BigDecimal value;
		private Maturity maturity;
		
		public Builder(UUID id) {
			id(id);
		}
		
		private void id(UUID id) {
			AssertionConcern.assertNotNull(id, "Id of the GDP entry is mandatory");
			this.id = id;
		}
		
		public Builder interval(Interval interval) {
			this.interval = interval;
			return this;
		}
		
		public Builder date(Date date) {
			this.date = date;
			return this;
		}
		
		public Builder value(BigDecimal value) {
			this.value = value;
			return this;
		}
		
		public Builder maturity(Maturity maturity) {
			this.maturity = maturity;
			return this;
		}
		
		public TreasuryYeald build() {
			return new TreasuryYeald(this);
		}
	}
}
