package config;

import java.io.Serializable;

public class ClientProperties implements Serializable{
	private int port;
	private String ip; //ip of server
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
	public ClientProperties(int port, String ip) {
		super();
		this.port = port;
		this.ip = ip;
	}
	
	public ClientProperties() { }


}
