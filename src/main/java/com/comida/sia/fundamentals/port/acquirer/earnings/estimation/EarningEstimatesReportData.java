package com.comida.sia.fundamentals.port.acquirer.earnings.estimation;

import java.text.ParseException;
import java.util.Date;

import com.comida.sia.sharedkernel.TranslationConcern;
import com.comida.sia.sync.supervision.domain.model.Direction;
import com.comida.sia.sync.supervision.domain.model.WaterMark;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EarningEstimatesReportData extends WaterMark{
	
	private String tickerSymbol;
	private String date;										//": "2026-12-31",
    private String horizon;										//": "next fiscal year",
    private String eps_estimate_average;						//": "11.8541",
    private String eps_estimate_high;							//": "12.7400",
    private String eps_estimate_low;							//": "10.8000",
    private String eps_estimate_analyst_count;					//": "20.0000",
    private String eps_estimate_average_7_days_ago;				//": "11.8687",
    private String eps_estimate_average_30_days_ago;			//": "11.6526",
    private String eps_estimate_average_60_days_ago;			//": "11.6096",
    private String eps_estimate_average_90_days_ago;			//": "11.6134",
    private String eps_estimate_revision_up_trailing_7_days;	//": "14.0000",
    private String eps_estimate_revision_down_trailing_7_days;	//": null,
    private String eps_estimate_revision_up_trailing_30_days;	//": "16.0000",
    private String eps_estimate_revision_down_trailing_30_days;	//": "2.0000",
    private String revenue_estimate_average;					//": "69567925500.00",
    private String revenue_estimate_high;						//": "70787666650.00",
    private String revenue_estimate_low;						//": "68408000000.00",
    private String revenue_estimate_analyst_count;				//": "20.00"
    
    public EarningEstimatesReportData() {
    	super(Direction.ASCENDING);
    }

	@Override
	public String calculateLevel() {
		try {
			return TranslationConcern.fillLeadingZeros(TranslationConcern.getConventer().convertToAlphaNumeric(TranslationConcern.getDateFrom(date)), 10);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Date occuranceTime() throws ParseException {
		return TranslationConcern.getDateFrom(date);
	}
	
	public void enrich(String additionalData) {
		this.tickerSymbol = additionalData;
	}

	@Override
	public String toString() {
		return "EarningEstimatesReportData [tickerSymbol=" + tickerSymbol + ", date=" + date + ", horizon=" + horizon
				+ ", eps_estimate_average=" + eps_estimate_average + ", eps_estimate_high=" + eps_estimate_high
				+ ", eps_estimate_low=" + eps_estimate_low + ", eps_estimate_analyst_count="
				+ eps_estimate_analyst_count + ", eps_estimate_average_7_days_ago=" + eps_estimate_average_7_days_ago
				+ ", eps_estimate_average_30_days_ago=" + eps_estimate_average_30_days_ago
				+ ", eps_estimate_average_60_days_ago=" + eps_estimate_average_60_days_ago
				+ ", eps_estimate_average_90_days_ago=" + eps_estimate_average_90_days_ago
				+ ", eps_estimate_revision_up_trailing_7_days=" + eps_estimate_revision_up_trailing_7_days
				+ ", eps_estimate_revision_down_trailing_7_days=" + eps_estimate_revision_down_trailing_7_days
				+ ", eps_estimate_revision_up_trailing_30_days=" + eps_estimate_revision_up_trailing_30_days
				+ ", eps_estimate_revision_down_trailing_30_days=" + eps_estimate_revision_down_trailing_30_days
				+ ", revenue_estimate_average=" + revenue_estimate_average + ", revenue_estimate_high="
				+ revenue_estimate_high + ", revenue_estimate_low=" + revenue_estimate_low
				+ ", revenue_estimate_analyst_count=" + revenue_estimate_analyst_count + "]";
	}
}
