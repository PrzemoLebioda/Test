package com.comida.sia.intelligence.news.domain.model;

import java.math.BigDecimal;
import java.util.Objects;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.ComparationConcern;
import com.comida.sia.sharedkernel.domain.ValueObject;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
public class Score implements ValueObject<Score>, Comparable<Score>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1391916475860253354L;
	
	@Getter private BigDecimal value;
	
	public Score() {
		
	}
	
	public Score(BigDecimal value) {
		this.setValue(value);
	}
	
	private void setValue(BigDecimal value) {
		AssertionConcern.assertNotNull(value, "Value of the score must be providec");
		this.value = value;
	}

	@Override
	public int compareTo(Score o) {
		return value.compareTo(o.value);
	}
	
	public Boolean lessThan(Score o) {
		if(this.compareTo(o) < 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public Boolean lessOrEqualsTo(Score o) {
		if(this.compareTo(o) <= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public Boolean greaterThan(Score o) {
		if(this.compareTo(o) > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public Boolean greaterOrEqualsTo(Score o) {
		if(this.compareTo(o) >= 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean sameValueAs(Score other) {
		if(other != null) {
			return  ComparationConcern.sameNumbers(this.value, other.value) ;
		}else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Score other = (Score) obj;
		return this.sameValueAs(other);
	}

	@Override
	public String toString() {
		return "Score [value=" + value + "]";
	}
}
