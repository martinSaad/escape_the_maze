package model1;

import java.util.HashMap;

import model.Algorithm.Astar;
import model.Algorithm.Searcher;
import model.Algorithm.Dijkstra;

public class SearchAlgorithmsFactory {
	private HashMap<String, AlgorithmCreator> algorithms;
	
	
	public SearchAlgorithmsFactory()
	{
		algorithms = new HashMap<String, AlgorithmCreator>();
		algorithms.put("Dijkstra", new DijkstraCreator());
		algorithms.put("Astar", new AStarCreator());
	}
	
	public Searcher createAlgorithm(String algorithmName) //the final method, this actually creat's the BFS/Astar (type of Searcher)
	{
		AlgorithmCreator creator = algorithms.get(algorithmName);
		Searcher searcher = null;
		if (creator != null)  {
			searcher = creator.create();			
		}
		return searcher;
	}
	
	
	private interface AlgorithmCreator //the main interface
	{
		Searcher create();
	}
	
	
	private class DijkstraCreator implements AlgorithmCreator
	{
		@Override
		public Searcher create() {
			return new Dijkstra();
		}		
	}
	
	private class AStarCreator implements AlgorithmCreator
	{
		@Override
		public Searcher create() {
			return new Astar();
		}		
	}
}
