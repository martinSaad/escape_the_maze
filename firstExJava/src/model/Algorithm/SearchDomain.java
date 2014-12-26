package model.Algorithm;

import java.util.HashMap;

public interface SearchDomain {


	State getStartState();
	State getGoalState();
	HashMap<Action,State> getAllPossibleMoves(State current);
	//get all the neighbors of the current state - Will implement this function in every Domain specific
	
	String getProblemDescription();
	
}
