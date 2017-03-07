/**
 * 
 */
package lindacare.jcunha.techtest.api.rest.junit;

import static com.jayway.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.jayway.restassured.RestAssured;

/**
 * @author José Cunha
 *
 */
public class TradeMessageConsumerTest {

	 @BeforeClass
	    public static void setup() {
	        String port = System.getProperty("server.port");
	        if (port == null) {
	            RestAssured.port = Integer.valueOf(8080);
	        }
	        else{
	            RestAssured.port = Integer.valueOf(port);
	        }


	        String basePath = System.getProperty("server.base");
	        if(basePath==null){
	            basePath = "/market-trade";
	        }
	        RestAssured.basePath = basePath;

	        String baseHost = System.getProperty("server.host");
	        if(baseHost==null){
	            baseHost = "http://localhost";
	        }
	        RestAssured.baseURI = baseHost;

	    }
	/**
	 * Test method for {@link lindacare.jcunha.techtest.api.rest.TradeMessageConsumer#consumeTradeMessage(lindacare.jcunha.techtest.entities.TradeMessage)}.
	 */
	@Ignore
	@Test
	public void testConsumeTradeMessage() {
		Map<String,Object> tradeMsg = new HashMap<>();
		tradeMsg.put("amountBuy", 1000L);
		tradeMsg.put("amountSell", 50L);
		tradeMsg.put("currencyFrom", "PT");
		tradeMsg.put("currencyTo", "GB");
		tradeMsg.put("originatingCountry", "Portugal");
		tradeMsg.put("rate", 10.2D);
		tradeMsg.put("timePlaced", "07-MAR-17 11:30:28");
		tradeMsg.put("userId", "jcunha");
		given()
		.authentication()
		.basic("admin", "admin")
        .contentType("application/json")
        .body(tradeMsg)
        .when().post("/market-trade/consumer/trade").then()
        .statusCode(401);
	}

}
