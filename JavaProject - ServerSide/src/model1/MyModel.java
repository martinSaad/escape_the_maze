package model1;

import java.util.ArrayList;
import java.util.Observable;

import model1.SolutionManager;
import model.algorithm.Action;
import model.algorithm.CommonSearcher;
import model.algorithm.SearchDomain;
import model.algorithm.Searcher;
import model.domains.MazeState;

/**
 * MyModel extends Observable and implements Model.<p>
 * The class receives a problem from the client, solves it and send the solution back to the client. 
 */

public class MyModel extends Observable implements Model {
	
	private Searcher algorithm;
	private SearchAlgorithmsFactory algorithmsFactory;
	private Solution solution;
	private SolutionManager solutionManager;
	private Thread t;
	private Problem problem;
	private SearchDomain domain;
	private SearchDomainFactory domainsFactory;
	private boolean stopSolving;

	
	// ----- Constructors, Getters & Setters ----- //
	public MyModel(Problem problem)
	{
		domainsFactory = new SearchDomainFactory();
		algorithmsFactory = new SearchAlgorithmsFactory();
		solutionManager = SolutionManager.getInstance();
		this.problem = problem;
		this.stopSolving = false;
	}
		
	public Searcher getAlgorithm() {
		return algorithm;
	}
	
	public Thread getT() {
		return t;
	}

	public void setT(Thread t) {
		this.t = t;
	}
	
	public String getStartState(){		
		String[] start = problem.getDomainArgs().split(" "); 
		return start[0];
	}
	
	public String getgoalState(){		
		String[] start = problem.getDomainArgs().split(" "); 
		return start[1];
	}

	

	// ------ Override Functions ------ //
	@Override
	public void selectDomain(String domainName) {
		domain = domainsFactory.createDomain(domainName, getStartState(), getgoalState());		
	}
	
	@Override
	public void selectAlgorithm(String algorithmName) {
		algorithm = algorithmsFactory.createAlgorithm(algorithmName);
	}

	

	@Override
	public void solveDomain() {		
		
		//if there is a solution for this problem already, get it from the solution file
		String problemDescription = problem.getDomainArgs();
		this.solution = solutionManager.getSolution(problemDescription);
		
		//if there is no such solution in the file
		if (solution == null) {		
			ArrayList<Action> actions = algorithm.search(domain);
			
			//if the client decided to exit the program
			if (stopSolving == true){
				((CommonSearcher)algorithm).setExit(true);
				return;
			}
			solution = new Solution();
			solution.setActions(actions);
			solution.setProblemDescription(problemDescription);
			solutionManager.addSolution(solution);
		}
		
		
	
		this.setChanged();
		this.notifyObservers();
	}

	public void setStopSolving(boolean stopSolving) {
		this.stopSolving = stopSolving;
	}

	@Override
	public Solution getSolution() {
		return solution;
	}

	@Override
	public void doTask() {
		solveDomain();
	}

	@Override
	public MazeState[][] getMatrix() {
		return domain.getMatrix();
	}


	
}
