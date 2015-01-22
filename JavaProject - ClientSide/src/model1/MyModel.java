package model1;

import java.util.Observable;

import model.domains.MazeState;
import networking.Client;


/**
 * MyModel extends Observable and implements Model.<p>
 * The class receives a problem from the client, solves it and send the solution back to the client. 
 */
public class MyModel extends Observable implements Model {
	
	private Problem problem;
	private Solution solution;
	private Client client;
	private MazeState[][] matrix;

	
	
	//------ Constructors, Getters & Setters ------//
	public Problem getProblem() {
		return problem;
	}
	
	public Client getClient() {
		return client;
	}

	public MyModel(){
		problem = new Problem();
	}
	
	public void setProblemDescription(String problemDescreption){
		problem.setDomainArgs(problemDescreption);
	}
	
	//------ Override Functions ------//
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
	
	public void displayBoard(){
		client = new Client();
		matrix = client.getBoard(problem);
		solution = client.getSolution();
		this.setChanged();
		this.notifyObservers();
	}
	
	public MazeState[][] getMatrix(){
		return matrix;
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
