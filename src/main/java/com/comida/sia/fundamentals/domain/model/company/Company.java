/**
 * 
 */
package com.comida.sia.fundamentals.domain.model.company;

import java.util.Date;
import java.util.Objects;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.CountrySymbol;
import com.comida.sia.sharedkernel.CurrencySymbol;
import com.comida.sia.sharedkernel.domain.DomainEntity;
import com.comida.sia.sync.supervision.domain.model.TimeSeries;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;


/**
 * 
 */
@Entity	(name = "Company")
@Table	(name = "companies",
		 indexes = {
				 @Index(name = "companies_exchange_idx", columnList = "exchange"),
				 @Index(name = "companies_sector_idx", columnList = "sector"),
				 @Index(name = "companies_industry_idx", columnList = "industry")
		}
)
public class Company implements DomainEntity<Company>, TimeSeries {
	@Id
	@Column(name = "ticker_symbol")
	@Getter private String tickerSymbol;
	
	@Column(name = "latest_quarter", columnDefinition = "date", nullable = true)
	@Getter private Date latestQuarter;
	
	@Column(name = "cik", nullable = true)
	@Getter private String cik;	//Central Index Key is a 10-digit number used on the Securities and Exchange Commission's computer systems to identify corporations and individuals who have filed disclosure with the SEC.
	
	@Column(name = "description", columnDefinition = "TEXT", nullable = true)
	@Getter private String description;
	
	@Column(name = "exchange", nullable = false)
	@Enumerated(EnumType.STRING)
	@Getter private Exchange exchange;
	
	@Column(name = "currency", nullable = true)
	@Enumerated(EnumType.STRING)
	@Getter private CurrencySymbol currency;
	
	@Column(name = "country", nullable = true)
	@Enumerated(EnumType.STRING)
	@Getter private CountrySymbol country;
	
	@Column(name = "sector", nullable = true)
	@Enumerated(EnumType.STRING)
	@Getter private Sector sector;
	
	@Column(name = "industry", nullable = true)
	@Enumerated(EnumType.STRING)
	@Getter private Industry industry;
	
	@Column(name = "address", nullable = true)
	@Getter private String address;
	
	@Column(name = "official_site", nullable = true)
	@Getter private String officialSite;
	
	@Column(name = "fiscal_year_end", nullable = true)
	@Getter private String fiscalYearEnd;
	
	@Column(name = "asset_type", nullable = true)
	@Enumerated(EnumType.STRING)
	@Getter private AssetType assetType;
	
	@OneToOne(
			fetch = FetchType.EAGER,
			cascade = CascadeType.ALL,
			orphanRemoval = true
		)   
	@JoinColumn(name = "ticker_symbol", referencedColumnName = "ticker_symbol")
	private CompanyKeyMetricsRegister keyMetricsRegister;
	
	
	public Company() {
		super();
	}
	
	public Company(Builder builder) {
		this.tickerSymbol = builder.tickerSymbol;
		this.latestQuarter = builder.latestQuarter;
		this.cik = builder.cik;
		this.description = builder.description;
		this.exchange = builder.exchange;
		this.currency = builder.currency;
		this.country = builder.country;
		this.sector = builder.sector;
		this.industry = builder.industry;
		this.address = builder.address;
		this.officialSite = builder.officialSite;
		this.fiscalYearEnd = builder.fiscalYearEnd;
		this.assetType = builder.assetType;
	}
	
	public void update(Company company) {
		this.setDescription(company.description);
		this.setExchange(company.exchange);
		this.setCountry(company.country);
		this.setSector(company.sector);
		this.setIndustry(company.industry);
		this.setAddress(company.address);
		this.setOfficialSite(company.officialSite);
		this.setFiscalYearEnd(company.fiscalYearEnd);
	}
	
	public void updateLatestQuarter(Date latestQuarter) {
		AssertionConcern.assertNotNull(latestQuarter, "Compay details: latest quarter date can't be null or empty");
		AssertionConcern.assertBefore(this.latestQuarter, latestQuarter, "New last quarter must be after the courrent one.");
		
		this.latestQuarter = latestQuarter;
	}

	private void setDescription(String description) {
		this.description = description;
	}

	private void setExchange(Exchange exchange) {
		AssertionConcern.assertNotNull(exchange, "Exchange can't be null or empty");
		this.exchange = exchange;
	}

	private void setCountry(CountrySymbol country) {
		AssertionConcern.assertNotNull(country, "Country symbol can't be null or empty");
		this.country = country;
	}

	private void setSector(Sector sector) {
		AssertionConcern.assertNotNull(sector, "Country symbol can't be null or empty");
		this.sector = sector;
	}

	private void setIndustry(Industry industry) {
		AssertionConcern.assertNotNull(industry, "Industry can't be null or empty");
		this.industry = industry;
	}

	private void setAddress(String address) {
		AssertionConcern.assertNotEmpty(address, "Address can't be null or empty");
		this.address = address;
	}

	private void setOfficialSite(String officialSite) {
		AssertionConcern.assertNotEmpty(officialSite, "OfficialSite can't be null or empty");
		this.officialSite = officialSite;
	}

	private void setFiscalYearEnd(String fiscalYearEnd) {
		AssertionConcern.assertNotEmpty(fiscalYearEnd, "FiscalYearEnd can't be null or empty");
		this.fiscalYearEnd = fiscalYearEnd;
	}
	
	public void registerKeyMetrics(CompanyKeyMetrics keyMetrics) throws CloneNotSupportedException {
		AssertionConcern.assertNotNull(keyMetrics, "Key metrics of the company must exists in order to register them in the company key metrics register");
		getCompanyKeyMetricsRegister().registerKeyMetrics(keyMetrics);
		this.latestQuarter = keyMetrics.getLatestQuarter();
	}
	
	private CompanyKeyMetricsRegister getCompanyKeyMetricsRegister() {
		AssertionConcern.assertNotNull(this.tickerSymbol, "Ticker symbol of the company is necessary for using company key metrics register.");
		
		if(this.keyMetricsRegister == null) {
			this.keyMetricsRegister = new CompanyKeyMetricsRegister(this.getTickerSymbol()); 
		}
		
		return this.keyMetricsRegister;
	}
	
	@Override
	public Date occuranceTime() {
		return latestQuarter;
	}


	@Override
	public boolean sameIdentityAs(Company other) {
		if(other == null) {
			return false;
		}
		
		return tickerSymbol.equals(other.tickerSymbol);
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, cik, country, currency, description, exchange, fiscalYearEnd, industry,
				keyMetricsRegister, officialSite, sector, tickerSymbol);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		return this.sameIdentityAs(other);
	}

	@Override
	public String toString() {
		return "CompanyDetails [tickerSymbol=" + tickerSymbol + ", cik=" + cik + ", description=" + description
				+ ", exchange=" + exchange + ", currency=" + currency + ", country=" + country + ", sector=" + sector
				+ ", industry=" + industry + ", address=" + address + ", officialSite=" + officialSite
				+ ", fiscalYearEnd=" + fiscalYearEnd + ", assetType=" + assetType + ", keyMetricsRegister="
				+ keyMetricsRegister + "]";
	}

	public static class Builder {
		private String tickerSymbol;
		private Date latestQuarter;
		private String cik;
		private String description;
		private Exchange exchange;
		private CurrencySymbol currency;
		private CountrySymbol country;
		private Sector sector;
		private Industry industry;
		private String address;
		private String officialSite;
		private String fiscalYearEnd;
		private AssetType assetType;
		
		public Builder(String tickerSymbol) {
			setTickerSymbol(tickerSymbol);
		}
		
		private void setTickerSymbol(String tickerSymbol) {
			AssertionConcern.assertNotEmpty(tickerSymbol, "Ticker symbol is mandatory in order to create Company");
			this.tickerSymbol = tickerSymbol;
		}
		
		public Builder latestQuarter(Date latestQuarter) {
			AssertionConcern.assertNotNull(latestQuarter, "Compay details: latest quarter date can't be null or empty");
			this.latestQuarter = latestQuarter;
			return this;
		}

		public Builder cik(String cik) {
			AssertionConcern.assertNotEmpty(cik, "CIK can't be null or empty");
			this.cik = cik;
			return this;
		}

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Builder exchange(Exchange exchange) {
			AssertionConcern.assertNotNull(exchange, "Exchange can't be null or empty");
			this.exchange = exchange;
			return this;
		}

		public Builder currency(CurrencySymbol currency) {
			AssertionConcern.assertNotNull(currency, "Currency symbol can't be null or empty");
			this.currency = currency;
			return this;
		}

		public Builder country(CountrySymbol country) {
			AssertionConcern.assertNotNull(country, "Country symbol can't be null or empty");
			this.country = country;
			return this;
		}

		public Builder sector(Sector sector) {
			AssertionConcern.assertNotNull(sector, "Country symbol can't be null or empty");
			this.sector = sector;
			return this;
		}

		public Builder industry(Industry industry) {
			AssertionConcern.assertNotNull(industry, "Industry can't be null or empty");
			this.industry = industry;
			return this;
		}

		public Builder address(String address) {
			AssertionConcern.assertNotEmpty(address, "Address can't be null or empty");
			this.address = address;
			return this;
		}

		public Builder officialSite(String officialSite) {
			AssertionConcern.assertNotEmpty(officialSite, "OfficialSite can't be null or empty");
			this.officialSite = officialSite;
			return this;
		}

		public Builder fiscalYearEnd(String fiscalYearEnd) {
			AssertionConcern.assertNotEmpty(fiscalYearEnd, "FiscalYearEnd can't be null or empty");
			this.fiscalYearEnd = fiscalYearEnd;
			return this;
		}

		public Builder assetType(AssetType assetType) {
			this.assetType = assetType;
			return this;
		}
		
		public Company build() {
			return new Company(this);
		}
	}
	
	/**
	 * Teraz trzeba się zastanowić nad strukturą agregatu. Czy w ogóle taki agregat ma rację bytu. W sumie to całe zamieszanie
	 * sprowadza się do tego, że my tylko dane zaciągamy i zapisujemy w bazie danych. Jednak za każdym razem, kiedy pobierany
	 * jest jakiś raport, to key metrics muszą być zaktualizowane. Zatem odpowiedzialnością Company będzie zebranie danych z 
	 * raportów i zaktualizowanie key metrics. Sama odpowiedzialność realizowana będzie przez wywołanie właściwego api.
	 * Jedynym problemem będzie zaplanowanie zdarzeń ale to już odpowiedzialność czegoś innego. 
	 * Czy dodanie nowej firmy ma skutkować jeszcze utworzeniem nowych partycji dla szeregów czasowych związanych z notowaniami?
	 * 
	 * Company overview
	 * 	- key metrics: it is updated on the same day a company reports its latest earnings and other financial reports. 
	 * 	  We can collect historical metrics as well unless it is possible to calculate them using other reports.
	 * 
	 * Income statement: annual and quarterly statements for the company of interest - current and historical periods 
	 * Balance sheet: annual and quarterly balance sheets for the company of interest - current and historical periods
	 * Cash flow: annual and quarterly cash flow for the company of interest - current and historical periods
	 * 
	 * Earnings: annual and quarterly earnings (EPS) for the company of interest - current and historical periods
	 * 
	 * Tutaj trudnością będzie wyznaczenie momentu, w którym należy odpytać serwis, aby mieć pewność, że będzie jakiś przyrost rekordów.
	 * Earnings calendar: list of company earnings expected in the next 3, 6, or 12 months. 
	 * Corporate actions - dividends: historical and future (declared) dividend distributions.
	 * Corporate actions - split: historical split events
	 * 
	 * IPO calendar: list of IPOs expected in the next 3 months.
	 */
	
}
