package com.comida.sia.indicators.domain.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sync.supervision.domain.model.TimeSeries;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "Indicator")
@Table(name = "indicators",
		indexes = {
				@Index(name = "indicators_interval_idx", columnList = "interval")
		})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", 
					 discriminatorType = DiscriminatorType.STRING)
public abstract class Indicator implements TimeSeries {
	
	@Id
	@Column(name = "id", nullable = false)
	@Getter private UUID id;
	
	@Column(name = "interval", nullable = false)
	@Enumerated(EnumType.STRING)
	@Getter @Setter private Interval interval;
	
	@Column(name = "date", nullable = false)
	@Getter @Setter private Date date;
	
	@Column(name = "value", nullable = false)
	@Getter @Setter private BigDecimal value;
	
	public Indicator() {
		super();
	}
	
	public Indicator(UUID id) {
		AssertionConcern.assertNotNull(id, "Id of the Indicator entry is mandatory");
		this.id = id;
	}
	
	@Override
	public Date occuranceTime() {
		return date;
	}
	
	@Getter
	public static abstract class Builder<T extends Indicator>{
		private UUID id;
		private Interval interval;
		private Date date;
		private BigDecimal value;
		
		public Builder(UUID id) {
			id(id);
		}
		
		private void id(UUID id) {
			AssertionConcern.assertNotNull(id, "Id of the indicator entry is mandatory");
			this.id = id;
		}
		
		public Builder<T> interval(Interval interval) {
			this.interval = interval;
			return this;
		}
		
		public Builder<T> date(Date date) {
			this.date = date;
			return this;
		}
		
		public Builder<T> value(BigDecimal value) {
			this.value = value;
			return this;
		}
		
		public abstract T build() ;
	}
}
