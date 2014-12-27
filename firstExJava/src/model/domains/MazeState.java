package model.domains;

import model.Algorithm.State;

import java.lang.Math;
import java.math.BigInteger;


public class MazeState extends State{

	protected int x;
	protected int y;
	
	public MazeState(){
		
	}
	
	public MazeState(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	

	public double getEvaluation(State goal){
	MazeState goalDest = (MazeState)goal; // casting goal state to mazeState
	double Distance = Math.pow((this.getX() - goalDest.getX()),2.0) + Math.pow((this.getY() - goalDest.getY()),2.0);
	return Math.sqrt(Distance);
	}
	
	@Override
	public boolean equals(Object o){ 
		int x1 = this.getX();
		int y1 = this.getY();
		int x2 = ((MazeState)o).getX();
		int y2 = ((MazeState)o).getY();
		if(x1==x2 && y1==y2)
			return true;
		return false;
	}
	

	/*@Override
	public int compareTo(State o) {
		// TODO Auto-generated method stub
		return 0;
	}*/

	

}
