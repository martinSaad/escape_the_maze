import java.beans.XMLEncoder;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import config.ClientProperties;
import config.ServerProperties;

public class MainClass {

	private static final String FILE_NAME = "ClientProperties.xml";

	public static void main(String[] args) {
		//ServerProperties properties = new ServerProperties(8001, 1); //port 8000, 50 clients
		ClientProperties cProperties = new ClientProperties(8001, "127.0.0.1"); //port, ip
		
		XMLEncoder encoder = null;
		try {
			encoder = new XMLEncoder(new FileOutputStream(FILE_NAME));
			encoder.writeObject(cProperties);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			encoder.close();
		}		
	}

}
