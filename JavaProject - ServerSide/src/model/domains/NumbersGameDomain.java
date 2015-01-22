package model.domains;

import java.util.HashMap;
import model.algorithm.Action;
import model.algorithm.SearchDomain;
import model.algorithm.State;

/**
 * Numbers game. Give an original number, a messed number and the solution would be the minimum "steps" to switch the messed number to the original one.
 *
 */
public class NumbersGameDomain implements SearchDomain{

	NumbersGameState start, goal;
	
	/*---- Constructors, Setters & Getters ----*/ 
	
	public NumbersGameDomain()
	{
		this.start = new NumbersGameState();
		this.goal = new NumbersGameState();
	}
	
	public void setStartState(String startState){		
		long num = Long.parseLong(startState);
		start.setNumber(num);
	}
	
	
	public void setGoalState(String goalState){
		long num = Long.parseLong(goalState);
		goal.setNumber(num);
	}
	
	public long getStartNumber(){
		return start.getNumber();
	}
	
	public long getFinalNumber(){
		return goal.getNumber();
	}
	
	/*---- Override Functions from Interface ----*/ 
	
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
		long temp = ((NumbersGameState)current).getNumber();
		String sn = Long.toString(temp);
		char[] array = sn.toCharArray(); // now we have the number in array of chars
		char[] tmpChars=array.clone();
		
		for(int i=0;i<array.length;i++)
		{
			for(int j=i+1;j<array.length;j++)
			{
				switchChars(tmpChars, i, j);
			
				// generate the new action and resulting state
				Action a=new Action("switch '"+array[i]+"'("+i+") and '"+array[j]+"'("+j+")");	
				a.setPrice(j - i + 1);
				long num = Long.parseLong(new String(tmpChars));
				NumbersGameState newState=new NumbersGameState(num);
				// put them in the hash map
				moves.put(a, newState);
				
				// switch back to original order
				switchChars(tmpChars, i, j);
			}
		}
		
		return moves;
	}
	
	private void switchChars(char[] chars,int i, int j){
		char tmp=chars[i];
		chars[i]=chars[j];
		chars[j]=tmp;
	}

	@Override
	public String getProblemDescription() {
		return "start number: " + getStartNumber() + ", final number: " + getFinalNumber(); 
	}


	@Override
	public MazeState[][] getMatrix() {
		// TODO Auto-generated method stub
		return null;
	}

}
