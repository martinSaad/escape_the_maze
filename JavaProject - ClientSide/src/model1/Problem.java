package model1;

import java.io.Serializable;

public class Problem implements Serializable {
	private String domainName;
	private String domainArgs;
	private String algorithmName;

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
