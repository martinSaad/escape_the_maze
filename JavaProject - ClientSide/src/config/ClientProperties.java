package config;

import java.io.Serializable;

/**
 * The class defines the server properties (port number and number of clients). Implements Serializable.
 */
public class ClientProperties implements Serializable{
	private int port;
	private String ip; //server Ip
	
	
	//------ Constructors, Getters & Setters ------//
		public ClientProperties(int port, String ip) {
			super();
			this.port = port;
			this.ip = ip;
		}
		
	public ClientProperties() { }

	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
		
}
