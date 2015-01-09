package model.domains;

import java.util.HashMap;
import java.util.Scanner;

import model.algorithm.Action;
import model.algorithm.SearchDomain;
import model.algorithm.State;

public class NumbersGameDomain implements SearchDomain{

	NumbersGameState start, goal;
	
	/*---- Constructors, Setters & Getters ----*/ 
	
	public NumbersGameDomain()
	{
		this.start = new NumbersGameState();
		this.goal = new NumbersGameState();
	}
	
	public void setStartState(String startState)
	{
	/*	int flag=0;
		long num;
		String sn = null;
		while(flag==0){
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter original number. The number must started with 1-9 and not '0' and not longer then 8 digits");
		sn=sc.nextLine();
		if(sn.charAt(0)!='0')
			flag=1;
		}
		num=Long.parseLong(sn);
		start.setNumber(num);
		*/
		
		long num = Long.parseLong(startState);
		start.setNumber(num);
	}
	
	
	public void setGoalState(String goalState)
	{
	/*	int flag=0;
		long num;
		String sn = null;
		while(flag==0){
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter messed number. The number must started with 1-9 and not '0', and not longer then 8 digits ");
		sn=sc.nextLine();
		if(sn.charAt(0)!='0')
			flag=1;
		}
		num=Long.parseLong(sn);
		goal.setNumber(num);
*/
		
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
	public String[][] getPrintedMatrix() {
		// TODO Auto-generated method stub
		return null;
	}

}
