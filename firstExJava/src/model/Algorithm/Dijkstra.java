package model.Algorithm;


import java.util.ArrayList;
import java.util.HashMap;

import model.Algorithm.CommonSearcher;
import model.Algorithm.SearchDomain;
import model.Algorithm.State;


public class Dijkstra extends CommonSearcher {

	
	@Override
	public ArrayList<Action> search(SearchDomain domain) {
		State startState = domain.getStartState();
		State goalState = domain.getGoalState();
		this.openList.add(startState);
		
		while(!(openList.isEmpty()))
		{
			State tmp = openList.poll();
			this.closedList.add(tmp);
			
			if(tmp.equals(goalState)) // if our state (Node) is the GOAL - so now return the moves now
			{
				ArrayList<Action> action = pathToGoal(tmp);
				return action;
			}
			//if the function didn't stop here - its mean that we are not in the end state
			
			HashMap<Action, State> nextStates = domain.getAllPossibleMoves(tmp);// get all the neighbors of the current state
			for(Action a: nextStates.keySet())
			{
				State nextState = nextStates.get(a);
				double newPrice = tmp.getPrice() + a.getPrice(); // the new price include the actions till now and the next action
				if(!(openList.contains(nextState)) && !(closedList.contains(nextState)))
				{
					nextState.setCameFrom(tmp);
					nextState.setCameWithAction(a);
					nextState.setPrice(newPrice);
					openList.add(nextState);
				}
				else
				{
					if (newPrice < nextState.getPrice())
					{
						if (!openList.contains(nextState))
							openList.add(nextState);
						else
						{
							nextState.setPrice(newPrice);
							openList.remove(nextState);
							openList.add(nextState);
						}
						}
				}
			}
			
			
		}
		
		return null;
	}

}

