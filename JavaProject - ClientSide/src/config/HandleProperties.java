package config;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * The class handle the client properties.
 */
public class HandleProperties {
	private static String fileName;
	//private static final String FILE_NAME = "/users/tomeraronovsky/Desktop/martinWorkspace/GenerateProperties/ClientProperties.xml";
	
	public static void setFileName(String fileName2) {
		fileName = fileName2;
	}
	/**
	 * The method reads the client properties (port number and server's IP) for an XML file.
	 * @return ServerProperties
	 */
	public static ClientProperties readProperties() {
		XMLDecoder decoder = null;
		
		try {
			decoder = new XMLDecoder(new FileInputStream(fileName));
			ClientProperties properties = (ClientProperties)decoder.readObject();
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
