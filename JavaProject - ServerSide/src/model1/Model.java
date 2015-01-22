package model1;

import java.util.Observer;

import model.domains.MazeState;
import tasks.Task;

/**
 * Model is a part of the MVP.<p>
 * The interface defines the functionality of any model
 *
 */

public interface Model extends Task{
	
	/**
	 * Creates the domain using Factory pattern
	 * @param domainName
	 */
	void selectDomain(String domainName);
	
	/**
	 * Creates the algorithm using Factory pattern
	 * @param algorithmName
	 */
	void selectAlgorithm(String algorithmName);
	
	/**
	 * Receives a problem from the client and solves it
	 */
	void solveDomain();
	
	/**
	 * 
	 * @return Solution
	 */
	Solution getSolution();
	
	
	void addObserver(Observer o);
	
	/**
	 * 
	 * @return MazeState[][]
	 */
	MazeState[][] getMatrix();
	}
