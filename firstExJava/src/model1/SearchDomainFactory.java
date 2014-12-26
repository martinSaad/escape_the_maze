package model1;

import java.util.HashMap;

import model.Algorithm.SearchDomain;
import model.domains.MazeDomain;
import model.domains.NumbersGameDomain;


public class SearchDomainFactory {
	
	
	private interface DomainCreator //main interface
	{
		SearchDomain create();
	}
	
	private HashMap<String, DomainCreator> domains;
	
		
	public SearchDomainFactory()
	{
		domains = new HashMap<String, DomainCreator>();
		domains.put("NumbersGame", new NumbersGameCreator());
		domains.put("Maze", new mazeDomainCreator());
	}
	
	public SearchDomain createDomain(String domainName) //this function actually create the Numbers Game/Maze (they are type of SearcheDomain)
	{
		DomainCreator creator = domains.get(domainName);
		SearchDomain searcher = null;
		if (creator != null)  {
			searcher = creator.create();			
		}
		return searcher;
	}
	
	private class NumbersGameCreator implements DomainCreator
	{
		@Override
		public SearchDomain create() {
			NumbersGameDomain newGame = new NumbersGameDomain();
			newGame.setStartState(); //set the start state
			newGame.setGoalState(); //set the goal state
			return newGame;
		}		
	}
	
	private class mazeDomainCreator implements DomainCreator
	{
	
		@Override
		public SearchDomain create() {
			
			MazeDomain newMaze = new MazeDomain();
			newMaze.boardInfo(); 
			newMaze.printMaze(); //print the maze
			newMaze.setStartState(); //set the start state
			newMaze.setGoalState(); //set the goal state
			return newMaze;	
		}
		
		
		
	}
	

}
