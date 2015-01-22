package model1;

import java.util.HashMap;

import model.algorithm.Astar;
import model.algorithm.Searcher;
import model.algorithm.Dijkstra;

/**
 * The class creates search algorithms using Factory pattern
 *
 */
public class SearchAlgorithmsFactory {
	private HashMap<String, AlgorithmCreator> algorithms;
	
	
	public SearchAlgorithmsFactory()
	{
		algorithms = new HashMap<String, AlgorithmCreator>();
		algorithms.put("Dijkstra", new DijkstraCreator());
		algorithms.put("Astar", new AStarCreator());
	}
	
	
	/**
	 * Finds the algorithm name in the hashMap the creates it
	 * @param algorithmName
	 * @return Searcher
	 */
	public Searcher createAlgorithm(String algorithmName) //the final method, this actually creat's the BFS/Astar (type of Searcher)
	{
		AlgorithmCreator creator = algorithms.get(algorithmName);
		Searcher searcher = null;
		if (creator != null)  {
			searcher = creator.create();			
		}
		return searcher;
	}
	
	/**
	 * 	Defines the common functionality of any type of algorithm creator
	 *
	 */
	private interface AlgorithmCreator //the main interface
	{
		/**
		 * Creates a specific search algorithm
		 * @return Searcher
		 */
		Searcher create();
	}
	
	
	/**
	 * Creates Dijkstra algorithm using Factory pattern
	 *
	 */
	private class DijkstraCreator implements AlgorithmCreator
	{
		@Override
		public Searcher create() {
			return new Dijkstra();
		}		
	}
	
	
	/**
	 * Creates A star algorithm using Factory pattern
	 *
	 */
	private class AStarCreator implements AlgorithmCreator
	{
		@Override
		public Searcher create() {
			return new Astar();
		}		
	}
}
