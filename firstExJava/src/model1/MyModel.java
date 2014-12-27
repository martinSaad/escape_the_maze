package model1;

import java.util.ArrayList;
import java.util.Observable;

import model1.SolutionManager;
import model.Algorithm.Action;
import model.Algorithm.SearchDomain;
import model.Algorithm.Searcher;

public class MyModel extends Observable implements Model {
	
	private SearchDomain domain;
	private Searcher algorithm;
	private SearchAlgorithmsFactory algorithmsFactory;
	private SearchDomainFactory domainsFactory;
	private Solution solution;
	private SolutionManager solutionManager;
	private Thread t;
		
	
	public Thread getT() {
		return t;
	}

	public void setT(Thread t) {
		this.t = t;
	}

	public MyModel()
	{
		algorithmsFactory = new SearchAlgorithmsFactory();
		domainsFactory = new SearchDomainFactory();
		solutionManager = SolutionManager.getInstance();
	}

	@Override
	public void selectDomain(String args) {
		domain = domainsFactory.createDomain(args);
	}

	@Override
	public void selectAlgorithm(String algorithmName) {
		algorithm = algorithmsFactory.createAlgorithm(algorithmName);
	}

	public SearchDomain getDomain() {
		return domain;
	}
	

	@Override
	public void solveDomain() {		
		String problemDescription = domain.getProblemDescription();
		this.solution = solutionManager.getSolution(problemDescription);
		
		//if there is no such solution in the file
		if (solution == null) {		
			ArrayList<Action> actions = algorithm.search(domain);
			solution = new Solution();
			solution.setActions(actions);
			solution.setProblemDescription(problemDescription);
			solutionManager.addSolution(solution);
		}
		
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
