package model.algorithm;

import java.util.ArrayList;
import java.util.HashMap;

import model.algorithm.CommonSearcher;
import model.algorithm.SearchDomain;
import model.algorithm.State;

/**
 * A star is a greedy algorithm to find the shortest path from A to B. The class extends CommonSearcher.<p>
 * A* uses a best-first search and finds a least-cost path from a given initial node to one goal node (out of one or more possible goals). As A* traverses the graph, it follows a path of the lowest expected total cost or distance, keeping a sorted priority queue of alternate path segments along the way.
 * It uses a knowledge-plus-heuristic cost function of node x (usually denoted f(x)) to determine the order in which the search visits nodes in the tree. The cost function is a sum of two functions:
 * the past path-cost function, which is the known distance from the starting node to the current node x (usually denoted g(x))
 * a future path-cost function, which is an admissible "heuristic estimate" of the distance from x to the goal (usually denoted h(x)).
 * The h(x) part of the f(x) function must be an admissible heuristic; that is, it must not overestimate the distance to the goal. Thus, for an application like routing, h(x) might represent the straight-line distance to the goal, since that is physically the smallest possible distance between any two points or nodes.
 * If the heuristic h satisfies the additional condition h(x) \le d(x,y) + h(y) for every edge (x, y) of the graph (where d denotes the length of that edge), then h is called monotone, or consistent. In such a case, A* can be implemented more efficientlyóroughly speaking, no node needs to be processed more than once (see closed set below)óand A* is equivalent to running Dijkstra's algorithm with the reduced cost d'(x, y) := d(x, y) + h(y) - h(x).
 */
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
