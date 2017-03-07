package lindacare.jcunha.techtest.api.rest.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingMessage;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

/**
 * Class that provides utilities to validate JSON objects against a schema
 * 
 * @author José Cunha
 * @since 07/03/2017
 * 
 *
 */
public class JSONValidationsUtils {

	public static final String JSON_V4_SCHEMA_IDENTIFIER = "http://json-schema.org/draft-04/schema#";
	public static final String JSON_SCHEMA_IDENTIFIER_ELEMENT = "$schema";

	/**
	 * Polymorphic method that gets JsonNode object from a jsonText
	 * 
	 * @param jsonText
	 * @return
	 * @throws IOException
	 */
	public static JsonNode getJsonNode(String jsonText) throws IOException {
		return JsonLoader.fromString(jsonText);
	}

	/**
	 * Polymorphic method that gets JsonNode object from a jsonFile
	 * 
	 * @param jsonFile
	 * @return
	 * @throws IOException
	 */
	public static JsonNode getJsonNode(File jsonFile) throws IOException {
		return JsonLoader.fromFile(jsonFile);
	}

	/**
	 * Polymorphic method that gets JsonNode object from a URL
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static JsonNode getJsonNode(URL url) throws IOException {
		return JsonLoader.fromURL(url);
	}
	
	/**
	 * Polymorphic method that gets JsonNode object from a resource
	 * @param resource
	 * @return
	 * @throws IOException
	 */
	public static JsonNode getJsonNodeFromResource(String resource) throws IOException {
		return JsonLoader.fromResource(resource);
	} // getJsonNode(Resource) ends

	/**
	 * Polymorphic method that gets JsonSchema object from a schemaText
	 * @param schemaText
	 * @return
	 * @throws IOException
	 * @throws ProcessingException
	 */
	public static JsonSchema getSchemaNode(String schemaText) throws IOException, ProcessingException {
		final JsonNode schemaNode = getJsonNode(schemaText);
		return _getSchemaNode(schemaNode);
	}

	/**
	 * Polymorphic method that gets JsonSchema object from a schemaFile
	 * @param schemaFile
	 * @return
	 * @throws IOException
	 * @throws ProcessingException
	 */
	public static JsonSchema getSchemaNode(File schemaFile) throws IOException, ProcessingException {
		final JsonNode schemaNode = getJsonNode(schemaFile);
		return _getSchemaNode(schemaNode);
	}

	/**
	 * Polymorphic method that gets JsonSchema object from a URL
	 * @param schemaFile
	 * @return
	 * @throws IOException
	 * @throws ProcessingException
	 */
	public static JsonSchema getSchemaNode(URL schemaFile) throws IOException, ProcessingException {
		final JsonNode schemaNode = getJsonNode(schemaFile);
		return _getSchemaNode(schemaNode);
	}

	/**
	 * Polymorphic method that gets JsonSchema object from a resource
	 * @param resource
	 * @return
	 * @throws IOException
	 * @throws ProcessingException
	 */
	public static JsonSchema getSchemaNodeFromResource(String resource) throws IOException, ProcessingException {
		final JsonNode schemaNode = getJsonNodeFromResource(resource);
		return _getSchemaNode(schemaNode);
	}

	/**
	 * Method that validates a jsonNode against a jsonSchemaNode
	 * @param jsonSchemaNode
	 * @param jsonNode
	 * @throws ProcessingException
	 */
	public static void validateJson(JsonSchema jsonSchemaNode, JsonNode jsonNode) throws ProcessingException {
		ProcessingReport report = jsonSchemaNode.validate(jsonNode);
		if (!report.isSuccess()) {
			for (ProcessingMessage processingMessage : report) {
				throw new ProcessingException(processingMessage);
			}
		}
	}

	/**
	 * Method that verifies if a jsonNode is valid.
	 * @param jsonSchemaNode
	 * @param jsonNode
	 * @return
	 * @throws ProcessingException
	 */
	public static boolean isJsonValid(JsonSchema jsonSchemaNode, JsonNode jsonNode) throws ProcessingException {
		ProcessingReport report = jsonSchemaNode.validate(jsonNode);
		return report.isSuccess();
	} 

	/**
	 * Method that verifies if a jsonText is valid. It uses a schemaText string.
	 * @param schemaText
	 * @param jsonText
	 * @return
	 * @throws ProcessingException
	 * @throws IOException
	 */
	public static boolean isJsonValid(String schemaText, String jsonText) throws ProcessingException, IOException {
		final JsonSchema schemaNode = getSchemaNode(schemaText);
		final JsonNode jsonNode = getJsonNode(jsonText);
		return isJsonValid(schemaNode, jsonNode);
	}

	/**
	 * Method that verifies if a jsonText is valid. It uses a schemaText File.
	 * @param schemaText
	 * @param jsonText
	 * @return
	 * @throws ProcessingException
	 * @throws IOException
	 */
	public static boolean isJsonValid(File schemaText, String jsonText) throws ProcessingException, IOException {
		final JsonSchema schemaNode = getSchemaNode(schemaText);
		final JsonNode jsonNode = getJsonNode(jsonText);
		return isJsonValid(schemaNode, jsonNode);
	}

	/**
	 * Method that verifies if a Json File is valid.
	 * @param schemaFile
	 * @param jsonFile
	 * @return
	 * @throws ProcessingException
	 * @throws IOException
	 */
	public static boolean isJsonValid(File schemaFile, File jsonFile) throws ProcessingException, IOException {
		final JsonSchema schemaNode = getSchemaNode(schemaFile);
		final JsonNode jsonNode = getJsonNode(jsonFile);
		return isJsonValid(schemaNode, jsonNode);
	}

	/**
	 * Method that verifies if JSON URL is valid against a schema URL
	 * @param schemaURL
	 * @param jsonURL
	 * @return
	 * @throws ProcessingException
	 * @throws IOException
	 */
	public static boolean isJsonValid(URL schemaURL, URL jsonURL) throws ProcessingException, IOException {
		final JsonSchema schemaNode = getSchemaNode(schemaURL);
		final JsonNode jsonNode = getJsonNode(jsonURL);
		return isJsonValid(schemaNode, jsonNode);
	}

	/**
	 * Method that validates a JSON string against a schema string.
	 * @param schemaText
	 * @param jsonText
	 * @throws IOException
	 * @throws ProcessingException
	 */
	public static void validateJson(String schemaText, String jsonText) throws IOException, ProcessingException {
		final JsonSchema schemaNode = getSchemaNode(schemaText);
		final JsonNode jsonNode = getJsonNode(jsonText);
		validateJson(schemaNode, jsonNode);
	}

	/**
	 * Method that validates a Json File against a schema File.
	 * @param schemaFile
	 * @param jsonFile
	 * @throws IOException
	 * @throws ProcessingException
	 */
	public static void validateJson(File schemaFile, File jsonFile) throws IOException, ProcessingException {
		final JsonSchema schemaNode = getSchemaNode(schemaFile);
		final JsonNode jsonNode = getJsonNode(jsonFile);
		validateJson(schemaNode, jsonNode);
	}

	/**
	 * Method that validates a Json URL against a schema JSON.
	 * @param schemaDocument
	 * @param jsonDocument
	 * @throws IOException
	 * @throws ProcessingException
	 */
	public static void validateJson(URL schemaDocument, URL jsonDocument) throws IOException, ProcessingException {
		final JsonSchema schemaNode = getSchemaNode(schemaDocument);
		final JsonNode jsonNode = getJsonNode(jsonDocument);
		validateJson(schemaNode, jsonNode);
	}

	/**
	 * Method that validates a jsonResouce against a schemaResouce
	 * @param schemaResource
	 * @param jsonResource
	 * @throws IOException
	 * @throws ProcessingException
	 */
	public static void validateJsonResource(String schemaResource, String jsonResource)
			throws IOException, ProcessingException {
		final JsonSchema schemaNode = getSchemaNode(schemaResource);
		final JsonNode jsonNode = getJsonNodeFromResource(jsonResource);
		validateJson(schemaNode, jsonNode);
	}
	

	/**
	 * Auxiliar method to get a json schema from a jsonode
	 * @param jsonNode
	 * @return
	 * @throws ProcessingException
	 */
	private static JsonSchema _getSchemaNode(JsonNode jsonNode) throws ProcessingException {
		final JsonNode schemaIdentifier = jsonNode.get(JSON_SCHEMA_IDENTIFIER_ELEMENT);
		if (null == schemaIdentifier) {
			((ObjectNode) jsonNode).put(JSON_SCHEMA_IDENTIFIER_ELEMENT, JSON_V4_SCHEMA_IDENTIFIER);
		}

		final JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
		return factory.getJsonSchema(jsonNode);
	}

}
