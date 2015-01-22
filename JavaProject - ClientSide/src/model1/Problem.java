package model1;

import java.io.Serializable;

/**
 * Problem implements Serializable.<p> 
 * The class represents a problem being passed between the client and the server.<p> *
 */

public class Problem implements Serializable {
	private String domainName;
	private String domainArgs;
	private String algorithmName;
	
	
	// ----- Getters & Setters ----- //
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public String getDomainArgs() {
		return domainArgs;
	}
	public void setDomainArgs(String domainArgs) {
		this.domainArgs = domainArgs;
	}
	public String getAlgorithmName() {
		return algorithmName;
	}
	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}
}
