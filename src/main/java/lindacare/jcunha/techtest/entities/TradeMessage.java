package lindacare.jcunha.techtest.entities;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import lindacare.jcunha.techtest.api.rest.utils.JsonDateSerializer;
import lindacare.jcunha.techtest.api.rest.utils.JsonDateDeserializer;

/**
 * POJO class for TradeMessage entity.
 * @author José Cunha
 *
 */
public class TradeMessage {
	
	private String userId;
	private String currencyFrom;
	private String currencyTo;
	private Long amountSell;
	private Long amountBuy;
	private Double rate;
	private Date timePlaced;
	private String originatingCountry;
	
	public TradeMessage(){
		
	}
	
	public TradeMessage(String userId, String currencyFrom, String currencyTo, Long amountSell, Long amountBuy,
			Double rate, Date timePlaced, String originatingCountry) {
		super();
		this.userId = userId;
		this.currencyFrom = currencyFrom;
		this.currencyTo = currencyTo;
		this.amountSell = amountSell;
		this.amountBuy = amountBuy;
		this.rate = rate;
		this.timePlaced = timePlaced;
		this.originatingCountry = originatingCountry;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCurrencyFrom() {
		return currencyFrom;
	}

	public void setCurrencyFrom(String currencyFrom) {
		this.currencyFrom = currencyFrom;
	}

	public String getCurrencyTo() {
		return currencyTo;
	}

	public void setCurrencyTo(String currencyTo) {
		this.currencyTo = currencyTo;
	}

	public Long getAmountSell() {
		return amountSell;
	}

	public void setAmountSell(Long amountSell) {
		this.amountSell = amountSell;
	}

	public Long getAmountBuy() {
		return amountBuy;
	}

	public void setAmountBuy(Long amountBuy) {
		this.amountBuy = amountBuy;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	@JsonSerialize(using=JsonDateSerializer.class)
	public String getTimePlaced() {
		return timePlaced.toString();
	}
	
	@JsonDeserialize(using=JsonDateDeserializer.class)
	public void setTimePlaced(Date timeplaced) {
		this.timePlaced = timeplaced;
	}

	public String getOriginatingCountry() {
		return originatingCountry;
	}

	public void setOriginatingCountry(String originatingCountry) {
		this.originatingCountry = originatingCountry;
	}

	@Override
	public String toString() {
		return "TradeMessagePOJO [userId=" + userId + ", currencyFrom=" + currencyFrom + ", currencyTo=" + currencyTo
				+ ", amountSell=" + amountSell + ", amountBuy=" + amountBuy + ", rate=" + rate + ", timeplaced="
				+ timePlaced + ", originatingCountry=" + originatingCountry + "]";
	}
	
	

}
