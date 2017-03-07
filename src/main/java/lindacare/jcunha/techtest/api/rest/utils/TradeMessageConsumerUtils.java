package lindacare.jcunha.techtest.api.rest.utils;

import java.io.File;
import java.io.IOException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.apache.log4j.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import lindacare.jcunha.techtest.api.rest.utils.JSONValidationsUtils;
import lindacare.jcunha.techtest.entities.TradeMessage;
import lindacare.jcunha.techtest.messageprocessor.MessagesProcessor;

/**
 * Class that contains utility methods to deal with a TradeMessageConsumer request
 * @author José Cunha
 *
 */
public class TradeMessageConsumerUtils {

	private final static Logger logger = Logger.getLogger(TradeMessageConsumerUtils.class);
	
	/**
	 * Method that routes the message to the MessageProcessor
	 * @param tradeMessage
	 */
	public void routesMessage(TradeMessage tradeMessage){
		try {
			MessagesProcessor.sendToAll(this.parseMessage2String(tradeMessage));
		} catch (IOException e) {
			logger.error("Error while sending message");
		}
	}

	/**
	 * Private method that handles the response being sent to the client
	 * @param tradeMessage
	 * @return
	 */
	public Response handleResponse(TradeMessage tradeMessage) {
		String jsonStr = null;
		jsonStr = this.parseMessage2String(tradeMessage);
		Response r = Response.status(Status.BAD_REQUEST).build();
		if (jsonStr != null) {
			boolean jsonValid = false;
			try {
				File schema = this.getSchemaFile("schema.json");				
				jsonValid = JSONValidationsUtils.isJsonValid(schema, jsonStr);
				if (jsonValid)
					r = Response.status(Status.CREATED).build();
				else
					r = Response.status(Status.BAD_REQUEST).build();
			} catch (ProcessingException | IOException e) {
				logger.error("INTERNAL SERVER ERROR. Error Message: "+e.getMessage());
				r = Response.status(Status.INTERNAL_SERVER_ERROR).build();
			}

		}
		return r;
	}
	
	/**
	 * Auxiliar method that translates de TradeMessage instance to a JSON String
	 * @param tradeMessage
	 * @return
	 */
	private String parseMessage2String(TradeMessage tradeMessage){
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = null;
		if (tradeMessage != null) {
			try {
				jsonInString = mapper.writeValueAsString(tradeMessage);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				logger.error("error processing object. error: "+e.getMessage());
			}
		}		
		return jsonInString;
		
	}
	
	

	/**
	 * Auxiliar method that gets the schema file from the resources
	 * @param fileName
	 * @return
	 */
	private File getSchemaFile(String fileName) {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		return file;

	}
	
}
