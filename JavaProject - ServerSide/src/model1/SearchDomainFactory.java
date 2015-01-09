package model1;

import java.util.HashMap;

import model.algorithm.SearchDomain;
import model.domains.MazeDomain;
import model.domains.NumbersGameDomain;


public class SearchDomainFactory {
	
	
	private interface DomainCreator //main interface
	{
		SearchDomain create(String startState, String goalState);
	}
	
	private HashMap<String, DomainCreator> domains;
	
		
	public SearchDomainFactory()
	{
		domains = new HashMap<String, DomainCreator>();
		domains.put("NumbersGame", new NumbersGameCreator());
		domains.put("Maze", new mazeDomainCreator());
	}
	
	public SearchDomain createDomain(String domainName, String startState, String goalState) //this function actually create the Numbers Game/Maze (they are type of SearcheDomain)
	{
		DomainCreator creator = domains.get(domainName);
		SearchDomain searcher = null;
		if (creator != null)  {
			searcher = creator.create(startState, goalState);			
		}
		return searcher;
	}
	
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
	
	private class mazeDomainCreator implements DomainCreator
	{
	
		@Override
		public SearchDomain create(String startState, String goalState) {
			
			MazeDomain newMaze = new MazeDomain(startState, goalState);
			//newMaze.printMaze(); //print the maze
			//newMaze.setStartState(); //set the start state
			//newMaze.setGoalState(); //set the goal state
			return newMaze;	
		}
		
		
		
	}
	

}
