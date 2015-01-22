package model.algorithm;

import java.util.HashMap;

import model.domains.MazeState;

/**
 * Defines a type of search domain.
 *
 */
public interface SearchDomain {


	State getStartState();
	State getGoalState();
	
	/**
	 * Given a state the method finds all possible moves. Used for search algorithm
	 * @param current
	 * @return HashMap<Action, State>
	 */
	HashMap<Action, State> getAllPossibleMoves(State current);
	
	String getProblemDescription();
	MazeState[][] getMatrix();
	
}
