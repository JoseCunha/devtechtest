package lindacare.jcunha.techtest.api.rest.utils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

/**
 * Class for deserialize date formats in JSON objects that are being sent to the server.
 * @author José Cunha
 *
 */
public class JsonDateDeserializer extends JsonDeserializer<Date> {
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy HH:mm:ss");

	@Override
	public Date deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
		String str = parser.getText().trim();
        try {
            return dateFormat.parse(str);
        } catch (ParseException e) {
        	
        }
        return context.parseDate(str);
	}

}
