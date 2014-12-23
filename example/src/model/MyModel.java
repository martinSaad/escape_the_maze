package model;

import java.util.ArrayList;
import java.util.Observable;

import tasks.Task;
import model.algorithm.Action;
import model.algorithm.SearchDomain;
import model.algorithm.Searcher;
import model.domains.WordGameDomain;

public class MyModel extends Observable implements Model {
	
	private SearchDomain domain;
	private Searcher algorithm;
	private SearchAlgorithmsFactory algorithmsFactory;
	private Solution solution;
	private SolutionManager solutionManager;
	
	public MyModel()
	{
		algorithmsFactory = new SearchAlgorithmsFactory();
		solutionManager = SolutionManager.getInstance();
	}

	@Override
	public void selectDomain(String args) {
		// TODO Auto-generated method stub
		// domain = domainFactory.createDomain(domainName)
		String[] arr = args.split(":");
		String domainName = arr[0];
		String domainArgs = arr[1];
		domain = new WordGameDomain(domainArgs);
	}

	@Override
	public void selectAlgorithm(String algorithmName) {
		// TODO Auto-generated method stub
		algorithm = algorithmsFactory.createAlgorithm(algorithmName);
	}

	@Override
	public void solveDomain() {	
		String problemDescription = domain.getProblemDescription();
		this.solution = solutionManager.getSolution(problemDescription);
		
		if (solution == null) {		
			ArrayList<Action> actions = algorithm.search(domain);
			solution = new Solution();
			solution.setActions(actions);
			solutionManager.addSolution(solution);
		}
		
		this.setChanged();
		this.notifyObservers();
	}

	@Override
	public Solution getSolution() {
		// TODO Auto-generated method stub
		return solution;
	}

	@Override
	public void doTask() {
		// TODO Auto-generated method stub
		solveDomain();
	}

}
