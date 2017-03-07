package lindacare.jcunha.techtest.api.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import lindacare.jcunha.techtest.api.rest.utils.TradeMessageConsumerUtils;
import lindacare.jcunha.techtest.entities.TradeMessage;


@Path("consumer")
public class TradeMessageConsumer implements TradeAPIInterface {
	private TradeMessageConsumerUtils tradeConsumerUtils;
	
	/* (non-Javadoc)
	 * @see lindacare.jcunha.techtest.api.rest.TradeAPIInterface#consumeTradeMessage(lindacare.jcunha.techtest.entities.TradeMessage)
	 */
	@Override
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Path("trade")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response consumeTradeMessage(TradeMessage tradeMessage) {		
		Response r = null;
		
		if(tradeMessage != null){
			r =  getTradeConsumerUtils().handleResponse(tradeMessage);			
			if(r!=null && r.getStatus()==201)
				getTradeConsumerUtils().routesMessage(tradeMessage);
		}else{
			r = Response.status(Status.BAD_REQUEST).build();
		}		
		return r;
	}	

	/**
	 * Get instance of utility class
	 * @return
	 */
	private TradeMessageConsumerUtils getTradeConsumerUtils() {
		if(tradeConsumerUtils == null){
			tradeConsumerUtils = new TradeMessageConsumerUtils();
		}
		return tradeConsumerUtils;
	}
	
	

}
