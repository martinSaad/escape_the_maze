package model1;

import java.util.HashMap;

import model.algorithm.SearchDomain;
import model.domains.MazeDomain;
import model.domains.NumbersGameDomain;

/**
 * The class creates search domains using Factory pattern
 *
 */
public class SearchDomainFactory {
	
	
	private HashMap<String, DomainCreator> domains;
	
	public SearchDomainFactory()
	{
		domains = new HashMap<String, DomainCreator>();
		domains.put("NumbersGame", new NumbersGameCreator());
		domains.put("Maze", new mazeDomainCreator());
	}
	
	
	/**
	 * 	Defines the common functionality of any type of domain creator
	 *
	 */
	
	private interface DomainCreator //main interface
	{
		/**
		 * Creates a specific domain
		 * @param startState
		 * @param goalState
		 * @return SearchDomain
		 */
		SearchDomain create(String startState, String goalState);
	}
	
	
		
	
	/**
	 * Finds the domain name in the hashMap the creates it
	 * @param domainName
	 * @param startState
	 * @param goalState
	 * @return Searcher
	 */
	public SearchDomain createDomain(String domainName, String startState, String goalState) //this function actually create the Numbers Game/Maze (they are type of SearcheDomain)
	{
		DomainCreator creator = domains.get(domainName);
		SearchDomain searcher = null;
		if (creator != null)  {
			searcher = creator.create(startState, goalState);			
		}
		return searcher;
	}
	
	
	/**
	 * Creates NumbersGame domain using Factory pattern
	 *
	 */
	private class NumbersGameCreator implements DomainCreator
	{
		@Override
		public SearchDomain create(String startState, String goalState) {
			NumbersGameDomain newGame = new NumbersGameDomain();
			newGame.setStartState(startState); //set the start state
			newGame.setGoalState(goalState); //set the goal state
			return newGame;
		}		
	}
	
	
	/**
	 * Creates Maze domain using Factory pattern
	 *
	 */
	private class mazeDomainCreator implements DomainCreator
	{
	
		@Override
		public SearchDomain create(String startState, String goalState) {
			
			MazeDomain newMaze = new MazeDomain(startState, goalState);
			return newMaze;	
		}
		
		
		
	}
	

}
