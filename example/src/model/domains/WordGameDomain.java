package model.domains;

import java.util.HashMap;

import model.algorithm.Action;
import model.algorithm.SearchDomain;
import model.algorithm.State;

public class WordGameDomain implements SearchDomain{
	
	
	WordGameState start,goal;
	
	public WordGameDomain() {}
	
	//public WordGameDomain(String start,String goal) {
	public WordGameDomain(String args) {
		String[] arr = args.split(",");
		this.start= new WordGameState(arr[0]);
		this.goal= new WordGameState(arr[1]);		
	}

	@Override
	public State getStartState() {
		return start;
	}

	@Override
	public State getGoalState() {
		return goal;
	}

	@Override
	public HashMap<Action, State> getAllPossibleMoves(State current) {
		
		HashMap<Action, State> moves=new HashMap<>();

		// char arrays to work on
		char[] chars=current.getState().toCharArray();
		char[] tmpChars=chars.clone();

		for(int i=0;i<chars.length;i++)
			for(int j=i+1;j<chars.length;j++){
								
				switchChars(tmpChars, i, j);
				
				// generate the new action and resulting state
				Action a=new Action("switch '"+chars[i]+"'("+i+") and '"+chars[j]+"'("+j+")");	
				a.setPrice(j - i + 1);
				WordGameState newState=new WordGameState(new String(tmpChars));
				// put them in the hash map
				moves.put(a, newState);
				
				// switch back to original order
				switchChars(tmpChars, i, j);
			}
		
		return moves;
	}
	
	// switch two chars in array
	private void switchChars(char[] chars,int i, int j){
		char tmp=chars[i];
		chars[i]=chars[j];
		chars[j]=tmp;
	}

	@Override
	public String getProblemDescription() {
		// TODO Auto-generated method stub
		return "start word: " + getStartState() + ", final word: " + getGoalState(); 
	}

}
