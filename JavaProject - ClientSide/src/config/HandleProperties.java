package config;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import tasks.Task;

public class HandleProperties {
private static final String FILE_NAME = "C:/Users/Martin Saad/git/GenerateProperties/ClientProperties.xml";
	
	public static ClientProperties readProperties() {
		XMLDecoder decoder = null;
		
		try {
			decoder = new XMLDecoder(new FileInputStream(FILE_NAME));
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
