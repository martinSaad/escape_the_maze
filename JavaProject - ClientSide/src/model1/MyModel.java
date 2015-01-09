package model1;

import java.util.Observable;

import networking.Client;

public class MyModel extends Observable implements Model {
	
	private Problem problem;
	private Solution solution;
	private Client client;

	
	public Client getClient() {
		return client;
	}

	public MyModel()
	{
		problem = new Problem();
	}
	
	public void setProblemDescription(String problemDescreption){
		problem.setDomainArgs(problemDescreption);
	}
	

	@Override
	public void selectDomain(String args) {			
		problem.setDomainName(args);
	}

	@Override
	public void selectAlgorithm(String algorithmName) {
		problem.setAlgorithmName(algorithmName);
	}

	@Override
	public void solveDomain() {	
		
		client = new Client();
		solution = client.getSolution(problem);
		
		this.setChanged();
		this.notifyObservers();
	}

	@Override
	public Solution getSolution() {
		return solution;
	}

	@Override
	public void doTask() {
		solveDomain();
	}

}
