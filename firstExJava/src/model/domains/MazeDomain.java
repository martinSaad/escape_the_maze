package model.domains;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

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
	
	public HashMap<Action, State> getAllPossibleMoves2(State state) // this function return all the possible neighbors of state
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
				if((this.matrix[i][j] == 0) && (i!=spesificRow || j!=specificColumn))
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
		matrix = new int[tmpRows][tmpColumns];
		
		generateMaze();
		
		
		/*	for(int i=0;i<this.matrix.length;i++)
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
		*/
	}
	
	public void intializeMaze(){
		for(int i=0;i<matrix.length;i++)
		{
			for(int j=0;j<matrix[i].length;j++)
			{
				if(i==0 || i==matrix.length-1)
					matrix[i][j] = -1;

				else if(j==0 || j==matrix[i].length-1)
					matrix[i][j] = -1;
				
				else
					matrix[i][j] = 0;
			}
		}
	}
	
	public ArrayList<MazeState> getNeighbors(MazeState state){
		ArrayList<MazeState> neighbors = new ArrayList<MazeState>();
		//check up
		if (matrix[state.getX()-1][state.getY()] == 0)
			neighbors.add(new MazeState(state.getX()-1, state.getY()));
		//check down
		if (matrix[state.getX()+1][state.getY()] == 0)
			neighbors.add(new MazeState(state.getX()+1, state.getY()));
		//check right
		if (matrix[state.getX()][state.getY()+1] == 0)
			neighbors.add(new MazeState(state.getX(), state.getY()+1));
		//check left
		if (matrix[state.getX()][state.getY()-1] == 0)
			neighbors.add(new MazeState(state.getX(), state.getY()-1));
		
		return neighbors;
	}
	
	public void generateMaze(){
		intializeMaze();
		Stack cellStack = new Stack<MazeState>();
		int totalCells = (rows-2)*(columns-2);
		Random random = new Random();
		MazeState currentCell = new MazeState(random.nextInt(rows-2)+1, random.nextInt(columns-2)+1);
		matrix[currentCell.getX()][currentCell.getY()]=1;
		int visitedCells = 1;
		while (visitedCells<totalCells){
			HashMap<Action, State>  neighbors = new HashMap<Action, State>();
			neighbors = getAllPossibleMoves2(currentCell);
			if (neighbors.size()!=0){
				int[] r = new int[4];
				r = randomAction(neighbors);
				int rand = 1;
				do{
					rand = random.nextInt(4);
				}while(r[rand]==0);
				/*
				 * 1 = moveUp
				 * 2 = moveDown
				 * 3 = moveRight
				 * 4 = moveLeft
				 */
				MazeState randomCell = null;
				HashMap<Action, State> backTrace = new HashMap<Action, State>();
				if (r[rand] == 1){
					randomCell = new MazeState(currentCell.getX()-1, currentCell.getY());
					backTrace.put(moveUp, randomCell);
				}
				else if (r[rand] == 2){
					randomCell = new MazeState(currentCell.getX()+1, currentCell.getY());
					backTrace.put(moveDown, randomCell);
				}
				else if (r[rand] == 3){
					randomCell = new MazeState(currentCell.getX(), currentCell.getY()+1);
					backTrace.put(moveRight, randomCell);
				}
				else if (r[rand] == 4){
					randomCell = new MazeState(currentCell.getX(), currentCell.getY()-1);
					backTrace.put(moveLeft, randomCell);
				}
				
				matrix[randomCell.getX()][randomCell.getY()] = 1;
				cellStack.push(currentCell);
				currentCell = randomCell;
				visitedCells++;				
			}
			else{
				currentCell = (MazeState) cellStack.pop();
			}
		}
	}
	
	public int[] randomAction(HashMap<Action, State> neighbors){
		int[] array = new int[4];
		if (neighbors.get(moveUp) != null){
			array[0] = 1;
			}
		if (neighbors.get(moveDown) != null){
				array[1] = 2;
			}
		if (neighbors.get(moveRight) != null){
				array[2] = 3;
			}
		if (neighbors.get(moveLeft) != null){
				array[3] = 4;
			}
		return array;
	}
	
	
	public void randomBoardValues() // '0' = wall, '1' = free. I decide calculate how many walls as the average of rows+columns
	{
		Random random = new Random();
		for (int i=1; i<rows-1; i++){
			for (int j=1; j<columns-1; j++){
					matrix[i][j] = random.nextInt(2); //generate random number 0 or 1
			}
		}
		
		/*	Random rn = new Random();
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
		*/
	}

	@Override
	public String getProblemDescription() {
		return "start state: " + start.getX()+","+start.getY() + ", final state: " + goal.getX()+","+goal.getY()+"and the maze is: "+ getMatrix();
	}
	
	
	
}
