package model.domains;

import model.algorithm.State;

public class WordGameState extends State {

	public WordGameState(String state) {
		super(state);
	}

	@Override
	public int getEvaluation(State goal) {
		// returns how many characters are the same w.r.t goal
		int sum=0;
		for(int i=0;i<state.length();i++)
			if(state.charAt(i)==goal.getState().charAt(i))
				sum++;
		return sum;
	}

}
