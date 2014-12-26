package model.domains;

import model.Algorithm.State;

public class NumbersGameState extends State {

	protected long number;
	protected boolean flag;
	
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public NumbersGameState() {
		// TODO Auto-generated constructor stub
	}
	public NumbersGameState(long num) {
		this.number = num;
	}
	public long getNumber() {
		return number;
	}
	public void setNumber(long number) {
		this.number = number;
	}
	
	private NumbersGameState NumbersGameState(long number2) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public double getEvaluation(State goal) {	
		int sum=0;
		NumbersGameState goal1 = ((NumbersGameState)goal);
		long tmpGoal = goal1.getNumber();
		String snGoal = Long.toString(tmpGoal);
		String snStart = Long.toString(this.getNumber());
		
		for(int i=0;i<snGoal.length();i++)
			if(snStart.charAt(i)==snGoal.charAt(i))
				sum++;
	
		
		return sum;
	}
	
	
	@Override
	public boolean equals(Object o){ 
		if(this.getNumber() == ((NumbersGameState)o).getNumber())
			return true;
		return false;
			
	}

}
