package model.algorithm;

import java.util.ArrayList;
import java.util.PriorityQueue;

import model.algorithm.Searcher;
import model.algorithm.State;

/**
 * Abstract class - all common things for a type of searcher.<p> Implements Searcher *
 */
public abstract class CommonSearcher implements Searcher{
// all the common things for A* and BFS
	
	protected PriorityQueue<State> openList = new PriorityQueue<State>();
	// openList contains the states that we still didn't handle
	protected PriorityQueue<State> closedList = new PriorityQueue<State>();
	//closedList contains the states that we finished handling (rich all neighbors)
	public boolean exit;
	public void setExit(boolean exit) {
			this.exit = exit;
	}
	
	/**
	 * The function will calculate all the moves from the start to goal
	 * @param state
	 * @return ArrayList<Action>
	 */
	public ArrayList<Action> pathToGoal(State state)
	{
		ArrayList<Action> newAction = new ArrayList<Action>();
		while (state.cameFrom != null)
		{
			newAction.add(0, state.cameWithAction);
			state = state.cameFrom; 
		}
		
		return newAction;
	}


}
