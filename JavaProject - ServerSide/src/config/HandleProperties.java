package config;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * The class handle the server properties.
 */

public class HandleProperties {
	private static final String FILE_NAME = "C:/Users/Martin Saad/Desktop/Java Workspace/GenerateProperties/ServerProperties.xml";
	
	/**
	 * The method reads the server properties (port number and number of clients) for an XML file.
	 * @return ServerProperties
	 */
	public static ServerProperties readProperties() {
		XMLDecoder decoder = null;
		
		try {
			decoder = new XMLDecoder(new FileInputStream(FILE_NAME));
			ServerProperties properties = (ServerProperties)decoder.readObject();
			return properties;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			decoder.close();
		}
		return null;
	}
}
