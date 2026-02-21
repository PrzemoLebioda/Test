package com.comida.sia.fundamentals.domain.model.stock;

import java.util.Date;
import java.util.Objects;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.domain.DomainEntity;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sync.supervision.domain.model.TimeSeries;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;

@Entity	(name = "Stock")
@Table	(name = "stocks",
		 indexes = {
				 @Index(name = "stocks_name_idx", columnList = "name"),
				 @Index(name = "stocks_asset_type_idx", columnList = "asset_type"),
				 @Index(name = "stocks_status_idx", columnList = "listing_status")
		}
)
@Getter
public class Stock implements DomainEntity<Stock>, TimeSeries {
	@Transient
	private Notifier notifier;
	
	@Id
	@Column(name = "ticker_symbol", nullable = false)
	private String tickerSymbol;
	
	@Column(name = "name", nullable = true)
	private String name;
	
	@Column(name = "ipo_date", columnDefinition = "date", nullable = false)
	private Date ipoDate;
	
	@Column(name = "delisting_date", columnDefinition = "date", nullable = true)
	private Date delistingDate;
	
	@Column(name = "asset_type", nullable = true)
	@Enumerated(EnumType.STRING)
	private AssetType assetType;
	
	@Column(name = "listing_status", nullable = false)
	@Enumerated(EnumType.STRING)
	private ListingStatus status;
	
	public Stock() {
		super();
	}
	
	public Stock(String tickerSymbol, String name) {
		super();
		setTickerSymbol(tickerSymbol);
		setName(name);
	}
	
	public Stock(String tickerSymbol, String name, Date ipoDate) {
		super();
		setTickerSymbol(tickerSymbol);
		setName(name);
		setIpoDate(ipoDate);
	}
	
	private void setTickerSymbol(String tickerSymbol) {
		AssertionConcern.assertNotEmpty(tickerSymbol, "Ticker symbol is mandatory in order to create company");
		this.tickerSymbol = tickerSymbol;
	}
	
	private void setName(String name) {
		//AssertionConcern.assertNotEmpty(name, "Name is mandatory in order to create company");
		this.name = name;
	}
	
	private void setIpoDate(Date ipoDate) {
		AssertionConcern.assertNotNull(ipoDate, "IPO date is mandatory");
		this.ipoDate = ipoDate;
	}
	
	public void setAssetType(AssetType assetType) {
		this.assetType = assetType;
	}
	
	public Stock withNotifier(Notifier notifier) {
		this.notifier = notifier;
		return this;
	}
	
	public void list(Date listingDate) {
		AssertionConcern.assertNotNull(listingDate, "Listing date is mandatory in order to list the company with ticker: " + tickerSymbol);
		AssertionConcern.assertNotNull(notifier, "Notifier is mandatory in order to list stock");
		
		this.ipoDate = listingDate;
		this.status = ListingStatus.ACTIVE;
		
		notifier.notify(new StockListedDomainEvent(tickerSymbol, ipoDate, assetType));
	}
	
	public void delist(Date delistingDate) {
		AssertionConcern.assertNotNull(delistingDate, "Delisting date is mandatory in order to delist the company with ticker: " + tickerSymbol);
		AssertionConcern.assertNotNull(notifier, "Notifier is mandatory in order to delist stock");
		
		this.delistingDate = delistingDate;
		this.status = ListingStatus.DELISTED;
		
		notifier.notify(new StockDelistedDomainEvent(tickerSymbol, delistingDate, assetType));
	}

	@Override
	public boolean sameIdentityAs(Stock other) {
		if(other == null) {
			return false;
		}
		
		return tickerSymbol.equals(other.tickerSymbol);
	}

	@Override
	public int hashCode() {
		return Objects.hash(delistingDate, ipoDate, name, status, tickerSymbol);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		return sameIdentityAs(other);
	}

	@Override
	public String toString() {
		return "Company [tickerSymbol=" + tickerSymbol + ", name=" + name + ", ipoDate=" + ipoDate + ", delistingDate="
				+ delistingDate + ", status=" + status + "]";
	}

	@Override
	public Date occuranceTime() {
		if(ListingStatus.ACTIVE.equals(status)) {
			return ipoDate;
		}
		
		if(ListingStatus.DELISTED.equals(status)) {
			return delistingDate;
		}
	
		return null;
	}
}
