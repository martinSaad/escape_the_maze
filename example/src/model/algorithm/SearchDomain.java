package model.algorithm;

import java.util.HashMap;

// the expected functionality from a search problem 
public interface SearchDomain {

	State getStartState();
	State getGoalState();
	HashMap<Action,State> getAllPossibleMoves(State current);	
	String getProblemDescription();
}
