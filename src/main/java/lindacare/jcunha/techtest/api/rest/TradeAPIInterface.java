package lindacare.jcunha.techtest.api.rest;

import javax.ws.rs.core.Response;

import lindacare.jcunha.techtest.entities.TradeMessage;

public interface TradeAPIInterface {

	Response consumeTradeMessage(TradeMessage tradeMessage);

}