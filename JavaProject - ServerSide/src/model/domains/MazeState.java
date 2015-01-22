package model.domains;

import model.algorithm.State;

import java.io.Serializable;
import java.lang.Math;

/**
 * A state in maze is represented by x,y (location) and 4 booleans (if there is a wall)
 *
 */
public class MazeState extends State implements Serializable{

	protected int x,y,ok,wall;
	private boolean upWall,downWall,leftWall,rightWall;
	private char[] tmp;
	
	//-----Constructs------
	public MazeState(){
		this.upWall = true;
		this.downWall = true;
		this.rightWall = true;
		this.leftWall = true;
		char[] tmp = new char[4]; 
		tmp[0] = 'x';
		tmp[1] = 'x';
		tmp[2] = 'x';
		tmp[3] = 'x';
		this.tmp = tmp;
	}
	
	public MazeState(MazeState state){
		this.x = state.getX();
		this.y = state.getY();
		this.wall = state.getWall();
		this.ok = state.getOk();
		this.upWall = state.isUpWall();
		this.downWall = state.isDownWall();
		this.leftWall = state.isLeftWall();
		this.rightWall = state.isRightWall();
		this.tmp = new char[4]; 
		for (int i=0; i<4; i++)
			this.tmp[i] = state.getTmp()[i];
	}
	
	public MazeState(int x, int y){
		this.x = x;
		this.y = y;
		this.upWall = true;
		this.downWall = true;
		this.rightWall = true;
		this.leftWall = true;
		char[] tmp = {'x','x','x','x'};
		this.tmp = tmp;
	}
	
	
	//----getters and setters-------
	public boolean isUpWall() {
		return upWall;
	}

	public void setUpWall(boolean upWall) {
		this.upWall = upWall;
	}

	public boolean isDownWall() {
		return downWall;
	}

	public void setDownWall(boolean downWall) {
		this.downWall = downWall;
	}

	public boolean isLeftWall() {
		return leftWall;
	}

	public void setLeftWall(boolean leftWall) {
		this.leftWall = leftWall;
	}

	public boolean isRightWall() {
		return rightWall;
	}

	public void setRightWall(boolean rightWall) {
		this.rightWall = rightWall;
	}

	public int getOk() {
		return ok;
	}

	public void setOk(int ok) {
		this.ok = ok;
	}

	public int getWall() {
		return wall;
	}

	public void setWall(int wall) {
		this.wall = wall;
	}	
	
	public char[] getTmp() {
		return tmp;
	}

	public void setTmp(char[] tmp) {
		this.tmp = tmp;
	}
	
	public void setTmp(char letter){
		if (letter=='u')
			this.tmp[0] = 'u';
		if (letter=='d')
			this.tmp[1] = 'd';
		if (letter=='l')
			this.tmp[2] = 'l';
		if (letter=='r')
			this.tmp[3] = 'r';
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
	

}
