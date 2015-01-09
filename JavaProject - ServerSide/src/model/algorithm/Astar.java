package model.algorithm;

import java.util.ArrayList;
import java.util.HashMap;

import model.algorithm.CommonSearcher;
import model.algorithm.SearchDomain;
import model.algorithm.State;

public class Astar extends CommonSearcher{
	
	@Override
	public ArrayList<Action> search(SearchDomain domain)
	{
		State startState = domain.getStartState();
		State goalState = domain.getGoalState();
		startState.setPrice(0);
		startState.setGFunc(0);
		startState.setCameFrom(null);
		openList.add(startState); 
		exit = false;
		
		
		
		while(!openList.isEmpty() && exit == false)
		{
			 State current = openList.poll();
			 if(current.equals(goalState))
			 {
				 ArrayList<Action> action = pathToGoal(current);
				 return action;
			 }
			
			 closedList.add(current);
			 HashMap<Action, State> nextStates = domain.getAllPossibleMoves(current);// get all the neighbors of the current state
			 
			 if(nextStates.isEmpty())
				 return null;
			 
			 for(Action a: nextStates.keySet())
			 {
				State nextState = nextStates.get(a);
				double tentativeG = current.getGFunc() + a.getPrice(); // G Function
				double fFunction = current.getGFunc() + a.getPrice() + nextState.getEvaluation(goalState); // g+h = f function
				if(!openList.contains(nextState) && !closedList.contains(nextState))
				{
					nextState.setCameFrom(current); // parent
					nextState.setPrice(fFunction);  // f
					nextState.setGFunc(tentativeG); // g
					nextState.setCameWithAction(a);
					openList.add(nextState);
				}
				
				else {
					
					if (fFunction < nextState.getPrice()) // Debugging!!
					{
						if (!openList.contains(nextState))
							openList.add(nextState);
						else
						{
							nextState.setPrice(fFunction);
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
