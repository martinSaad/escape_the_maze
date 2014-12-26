package model.domains;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import model.Algorithm.Action;
import model.Algorithm.SearchDomain;
import model.Algorithm.State;

public class MazeDomain implements SearchDomain{


	MazeState start,goal;
	public int rows;
	public int columns;
	public int [][] matrix; 
	public Action moveUp = new Action("Move Up");
	public Action moveDown = new Action("Move Down");
	public Action moveLeft = new Action("Move Left");
	public Action moveRight = new Action("Move Right");
	
	
	/*---- Constructors, Setters & Getters ----*/ 
	

	public MazeDomain() {
		this.start = new MazeState();
		this.goal = new MazeState();
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public int[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}
	
	public void setStartState()
	{
		int tempX, tempY;
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter start state.	NOTE: the first row & column is 1");
		System.out.print("enter row: ");
		tempX = sc.nextInt();
		System.out.print("enter column: ");
		tempY = sc.nextInt();
		while(this.matrix[tempX][tempY] == 0) //if the start state is block, the user must enter new state
		{
			System.out.println("Your start State is BLOCK. Please enter new start state.");
			System.out.print("enter row: ");
			tempX = sc.nextInt();
			System.out.print("enter column: ");
			tempY = sc.nextInt();
		}
		start.setX(tempX);
		start.setY(tempY);
	}
	
	
	public void setGoalState()
	{
		int tempX, tempY;
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter goal state. NOTE: the first row & column is 1");
		System.out.print("enter row: ");
		tempX = sc.nextInt();
		System.out.print("enter column: ");
		tempY = sc.nextInt();
		while(this.matrix[tempX][tempY] == 0) //if the goal state is block, the user must enter new state
		{
			System.out.println("Your goal State is BLOCK. Please enter new goal state.");
			System.out.print("enter row: ");
			tempX = sc.nextInt();
			System.out.print("enter column: ");
			tempY = sc.nextInt();
		}
		goal.setX(tempX);
		goal.setY(tempY);
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
	public HashMap<Action, State> getAllPossibleMoves(State state) // this function return all the possible neighbors of state
	{
		MazeState current = (MazeState)state;
		int spesificRow = current.getX();
		int specificColumn = current.getY();
		
		HashMap<Action, State> allNeighbors = new HashMap<Action, State>();
	
		MazeState tmpUp = new MazeState();
		MazeState tmpDown = new MazeState();
		MazeState tmpRight = new MazeState();
		MazeState tmpLeft = new MazeState();
		//all possible actions: right, left, up, down
		
		for(int i=spesificRow-1; i<=spesificRow +1; i++)
		{
			for(int j=specificColumn-1; j<=specificColumn +1; j++)
			{
				if((this.matrix[i][j] == 1) && (i!=spesificRow || j!=specificColumn))
				{
					if(spesificRow<i && specificColumn==j)
					{
						tmpDown.setX(i);
						tmpDown.setY(j);
						tmpDown.setPrice(1);
						allNeighbors.put(moveDown,tmpDown);
					}
					
					else if(spesificRow>i && specificColumn==j)
					{
						tmpUp.setX(i);
						tmpUp.setY(j);
						tmpUp.setPrice(1);
						allNeighbors.put(moveUp,tmpUp);
					}
					
					else if(specificColumn<j && spesificRow==i)
					{
						tmpRight.setX(i);
						tmpRight.setY(j);
						tmpRight.setPrice(1);
						allNeighbors.put(moveRight,tmpRight);
					}
					
					else if(specificColumn>j && spesificRow==i)
					{
						tmpLeft.setX(i);
						tmpLeft.setY(j);
						tmpLeft.setPrice(1);
						allNeighbors.put(moveLeft,tmpLeft);
					}
						
					
					
				}
			}
		}
		
		return allNeighbors;
	}

	
	
	/*---- Board Functions - print, random & more  ----*/
	
	
	public void printMaze() // print maze(as matrix) to the screen
	{
		for(int i=1;i<this.matrix.length-1;i++)
		{
			for(int j=1;j<this.matrix[i].length-1;j++)
			{
				System.out.print(" "+this.matrix[i][j]);
			}
			System.out.println(" ");
		}
	}
	
	
	
	public void boardInfo() //This Function roll is to manage the board info. important! I put -1 in the first and last rows and columns 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter how many rows you want in the board:");
		int tmpRows = sc.nextInt();
		
		System.out.println("Please enter how many columns you want in the board?");
		int tmpColumns = sc.nextInt();
		
		tmpRows=tmpRows+2; // update rows size
		tmpColumns=tmpColumns+2; // update column size
		
		setRows(tmpRows);
		setColumns(tmpColumns); 
		this.matrix = new int[tmpRows][tmpColumns];
		
		for(int i=0;i<this.matrix.length;i++)
		{
			for(int j=0;j<this.matrix[i].length;j++)
			{
				if(i==0 || i==this.matrix.length-1)
					this.matrix[i][j] = -1;
				if(j==0 || j==this.matrix[i].length-1)
					this.matrix[i][j] = -1;
			}
		}
		
		this.randomBoardValues();
	}
	
	
	public void randomBoardValues() // '0' = wall, '1' = free. I decide calculate how many walls as the average of rows+columns
	{
		Random rn = new Random();
		int counterZeros=0;
		int avg = (this.rows+this.columns)/2;
		for(int i=0; i<this.rows; i++)
		{
			for(int j=0; j<this.columns;j++)
			{
				if(this.matrix[i][j] == -1)
					continue;
				else if(this.matrix[i][j]==0 && counterZeros<=avg){
					this.matrix[i][j] = rn.nextInt(2);
					counterZeros++; 
					}
				else this.matrix[i][j] = 1;
			}
		}
	}

	@Override
	public String getProblemDescription() {
		return "start state: " + start.getX()+","+start.getY() + ", final state: " + goal.getX()+","+goal.getY()+"and the maze is: "+ getMatrix();
	}
	
	
	
}
