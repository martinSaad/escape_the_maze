package model.algorithm;

import java.util.ArrayList;
import java.util.HashMap;

public class HillClimbing extends CommonSearcher {

	@Override
	public ArrayList<Action> search(SearchDomain domain) {
		ArrayList<Action> actions=new ArrayList<Action>();
		State theBestStateSoFar=domain.getStartState();
		State goal=domain.getGoalState();
		boolean betterActionWasFound=true;
		// while the current state can get better...
		while(!theBestStateSoFar.equals(goal) && betterActionWasFound){
			State nextState=theBestStateSoFar;
			Action nextAction=null;
			// get all the neighbours of the current state
			HashMap<Action, State> neighbours=domain.getAllPossibleMoves(theBestStateSoFar);
			betterActionWasFound=false;
			// find the best action!			
			for(Action a : neighbours.keySet()){
				State n=neighbours.get(a);
				// if the state n's evaluation is better than that of the current state
				if (n.getEvaluation(goal)>nextState.getEvaluation(goal)){
					// then choose this action and this state as the next move
					nextState=n;
					nextAction=a;
					betterActionWasFound=true;
				}
			}
			if(betterActionWasFound){
				theBestStateSoFar=nextState;
				actions.add(nextAction);
			}
		}
		return actions;
	}

}
