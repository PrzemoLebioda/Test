package com.comida.sia.intelligence.insidertransactions.port.acquirer;

import java.text.ParseException;
import java.util.Date;

import com.comida.sia.sharedkernel.TranslationConcern;
import com.comida.sia.sync.supervision.domain.model.Direction;
import com.comida.sia.sync.supervision.domain.model.WaterMark;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsiderTransactionData extends WaterMark{
	private String transaction_date;
	private String ticker;
	private String executive;
	private String executive_title;
	private String security_type;
	private String acquisition_or_disposal;
	private String shares;
	private String share_price;
	
	public InsiderTransactionData(){
		super(Direction.ASCENDING);
	}
	
	@Override
	public String calculateLevel() {
		String level = "";
		try {
			level = calculateTransactionDateMarkMark() + 
					calculateTransactionTypeMark() + 
					calculateSecurityTypeMark() +
					calculateExecutiveMark() +
					calculateSharesMark() +
					calculateSharePrice();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return level;
	}
	
	private String calculateTransactionDateMarkMark() throws ParseException {
		return TranslationConcern.fillLeadingZeros(TranslationConcern.getConventer().convertToAlphaNumeric(TranslationConcern.getDateFrom(transaction_date)), 10);
	}
	
	private String calculateTransactionTypeMark() {
		return TranslationConcern.fillLeadingZeros(acquisition_or_disposal, 1);
	}
	
	private String calculateSharesMark() {
		long sharesAmount = TranslationConcern.getNumberFrom(shares) == null ? 0L : TranslationConcern.getNumberFrom(shares).longValue();
		return TranslationConcern.fillLeadingZeros(TranslationConcern.getConventer().convertToAlphaNumeric(sharesAmount), 7);
	}
	
	private String calculateExecutiveMark() {
		return TranslationConcern.fillLeadingZeros(executive, 15);
	}
	
	private String calculateSharePrice() {
		long sharePrice = TranslationConcern.getNumberFrom(share_price) == null ? 0L : TranslationConcern.getNumberFrom(share_price).longValue();
		return TranslationConcern.fillLeadingZeros(TranslationConcern.getConventer().convertToAlphaNumeric(sharePrice), 7);
	}
	
	private String calculateSecurityTypeMark() {
		return(TranslationConcern.fillLeadingZeros(security_type, 10));
	}

	@Override
	public Date occuranceTime() throws ParseException {
		return TranslationConcern.getDateFrom(transaction_date);
	}

	@Override
	public String toString() {
		return "InsiderTransactionData [transactionDate=" + transaction_date + ", ticker=" + ticker + ", executive="
				+ executive + ", executiveTitle=" + executive_title + ", securityType=" + security_type
				+ ", acquisitionOrDisposal=" + acquisition_or_disposal + ", shares=" + shares + ", sharePrice="
				+ share_price + "]";
	}
	
}
