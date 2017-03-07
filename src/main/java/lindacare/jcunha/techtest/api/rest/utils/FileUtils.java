package lindacare.jcunha.techtest.api.rest.utils;

import java.io.File;

/**
 * Utility class for file processing
 * @author José Cunha
 *
 */
public class FileUtils {

	
	public File getFile(String fileName) {
		
		//Get file from resources folder
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());	

		return file;

	  }
}
