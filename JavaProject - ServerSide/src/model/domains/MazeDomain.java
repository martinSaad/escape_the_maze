package model.domains;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Stack;

import model.algorithm.Action;
import model.algorithm.SearchDomain;
import model.algorithm.State;

/**
 * Maze Domain is a game where the start state is the location (1,1) and the end state is (n,n).<p>
 * The purpose is to get from the start to the end in the shortest path.
 *
 */
public class MazeDomain implements SearchDomain {

	public MazeState start,goal;
	public MazeState[][] matrix;
	public int boardRows,boardColumns; 
	public Action up=new Action("up");
	public Action down=new Action("down");
	public Action left=new Action("left");
	public Action right=new Action("right");
	
	public MazeDomain(String rows, String columns){
		setBoardRows(Integer.parseInt(rows)+2);
		setBoardColumns(Integer.parseInt(columns)+2); 
		start = new MazeState(1,1);
		goal = new MazeState(boardRows-2, boardColumns-2);
		generateMaze();
	}
	
	public MazeDomain()
	{
		generateMaze();
	}
	
	//-------getters and setters----------
	
	public int getBoardRows() {
		return boardRows;
	}

	public void setBoardRows(int boardRows) {
		this.boardRows = boardRows;
	}


	public void setBoardColumns(int boardColumns) {
		this.boardColumns = boardColumns;
	}
	
	public int getBoardColumns() {
		return boardColumns;
	}	

	
	public MazeState[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(MazeState cell) {
		this.matrix[cell.getX()][cell.getY()] = cell;
	}
	
	public State getStartState() {
		return start;
	}
		

	public State getGoalState() {
		return goal;
	}
	
	
	public void printMaze(){
		for(int i=1;i<matrix.length-1;i++){
			for(int j=1;j<matrix[i].length-1;j++){
				System.out.print("("+matrix[i][j].getOk()+","+Arrays.toString(matrix[i][j].getTmp())+")");
				//System.out.print("("+matrix[i][j].getOk()+","+matrix[i][j].getWall()+")");
			}
			System.out.println("");
		}
	}
	

	public HashMap<Action, State> getAllPossibleMoves(State state){
		HashMap<Action,State> allNeighbours = new HashMap<Action, State>();
		
		int x = ((MazeState)state).getX();
		int y = ((MazeState)state).getY();
		
		if (matrix[x][y].isUpWall() == false){
			matrix[x-1][y].setPrice(1);
			allNeighbours.put(up, matrix[x-1][y]);
		}
		if (matrix[x][y].isDownWall() == false){
			matrix[x+1][y].setPrice(1);
			allNeighbours.put(down, matrix[x+1][y]);
		}
		if (matrix[x][y].isLeftWall() == false){
			matrix[x][y-1].setPrice(1);
			allNeighbours.put(left, matrix[x][y-1]);
		}
		if  (matrix[x][y].isRightWall() == false){
			matrix[x][y+1].setPrice(1);
			allNeighbours.put(right, matrix[x][y+1]);
		}
		
		return allNeighbours;
	}
	
	

	/**
	 * Given a state the method finds all possible moves where is a wall.<p>
	 * Used for generation of the Maze.
	 * @param state
	 * @return HashMap<Action, State>
	 */
	public HashMap<Action, State> getAllPossibleMovesByWalls(State state){
		
		HashMap<Action, State> allNeighbors = new HashMap<Action, State>();
		
		int x = ((MazeState)state).getX();
		int y = ((MazeState)state).getY();
		
		if (matrix[x-1][y].getOk() == 0){
			matrix[x-1][y].setPrice(1);
			allNeighbors.put(up, matrix[x-1][y]);
		}
		if (matrix[x+1][y].getOk() == 0){
			matrix[x+1][y].setPrice(1);
			allNeighbors.put(down, matrix[x+1][y]);
		}
		if (matrix[x][y-1].getOk() == 0){
			matrix[x][y-1].setPrice(1);
			allNeighbors.put(left, matrix[x][y-1]);
		}
		if (matrix[x][y+1].getOk() == 0){
			matrix[x][y+1].setPrice(1);
			allNeighbors.put(right, matrix[x][y+1]);
		}
		
		return allNeighbors;
	}
	

	/**
	 * Initializing new maze, round walls with '-1' and the grids with '0'
	 */
	public void initializeMaze(){
		matrix = new MazeState[getBoardRows()][getBoardColumns()]; 
		for(int i=0;i<getBoardRows();i++)
		{
			for(int j=0;j<getBoardColumns();j++)
			{
				matrix[i][j]= new MazeState();
				if(i==0 || i==matrix.length-1){
					matrix[i][j].setOk(-1);
					matrix[i][j].setX(i);
					matrix[i][j].setY(j);	
					}
				

				else if(j==0 || j==matrix[i].length-1){
					matrix[i][j].setOk(-1);
					matrix[i][j].setX(i);
					matrix[i][j].setY(j);
					}
				
				else{ //setting all walls to blocked at the start
					matrix[i][j].setOk(0);
					matrix[i][j].setUpWall(true);
					matrix[i][j].setDownWall(true);
					matrix[i][j].setLeftWall(true);
					matrix[i][j].setRightWall(true);
					matrix[i][j].setX(i);
					matrix[i][j].setY(j);
				}
			}
		}
	}
	
			
	/**
	 * generate a perfect maze using DFS with the next algorithm:<p>
	 * 1) Start at a random cell in the grid.  <p>
	 * 2) Look for a random neighbor cell you haven't been to yet.<p> 
	 * 3) If you find one, move there, knocking down the wall between the cells. If you don't find one, back up to the previous cell.<p>  
	 * 4) Repeat steps 2 and 3 until you've been to every cell in the grid.<p>
	 */
	public void generateMaze(){
		initializeMaze();
		Stack<MazeState> cellStack = new Stack<MazeState>();
		int totalCells = (boardRows-2)*(boardColumns-2);
		Random random = new Random();
		MazeState currentCell = new MazeState(random.nextInt(boardRows-2)+1, random.nextInt(boardColumns-2)+1);
		int visitedCells = 1;
		while (visitedCells<totalCells){
			currentCell.setOk(1);
			matrix[currentCell.getX()][currentCell.getY()].setOk(1);
			HashMap<Action, State>  neighbors = new HashMap<Action, State>();
			neighbors = getAllPossibleMovesByWalls(currentCell);
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
	
				
				MazeState randomCell = new MazeState();
				HashMap<Action, State> backTrace = new HashMap<Action, State>();
				if (r[rand] == 1){
					randomCell.setX(currentCell.getX()-1);
					randomCell.setY(currentCell.getY());
					randomCell.setDownWall(false);
					randomCell.setTmp('d');
					backTrace.put(up, randomCell);
					currentCell.setUpWall(false);
					currentCell.setTmp('u');
				}
				else if (r[rand] == 2){
					randomCell.setX(currentCell.getX()+1);
					randomCell.setY(currentCell.getY());
					randomCell.setUpWall(false);
					randomCell.setTmp('u');
					backTrace.put(down, randomCell);
					currentCell.setDownWall(false);
					currentCell.setTmp('d');
				}
				else if (r[rand] == 3){
					randomCell.setX(currentCell.getX());
					randomCell.setY(currentCell.getY()+1);
					randomCell.setLeftWall(false);
					randomCell.setTmp('l');
					backTrace.put(right, randomCell);
					currentCell.setRightWall(false);
					currentCell.setTmp('r');
				}
				else if (r[rand] == 4){
					randomCell.setX(currentCell.getX());
					randomCell.setY(currentCell.getY()-1);
					randomCell.setRightWall(false);
					randomCell.setTmp('r');
					backTrace.put(left, randomCell);
					currentCell.setLeftWall(false);
					currentCell.setTmp('l');
				}
				
				randomCell.setOk(1);
				cellStack.push(currentCell);
				setMatrix(currentCell);
				setMatrix(randomCell);
				currentCell = randomCell;
				visitedCells++;				
			}
			else{
				currentCell = cellStack.pop();
			}
		}
	}
	
	/**
	 * Giving an array of all possible moves.
	 * @param neighbors
	 * @return int[]
	 */
	public int[] randomAction(HashMap<Action, State> neighbors){
		int[] array = new int[4];
		if (neighbors.get(up) != null){
				array[0] = 1;
			}
		if (neighbors.get(down) != null){
				array[1] = 2;
			}
		if (neighbors.get(right) != null){
				array[2] = 3;
			}
		if (neighbors.get(left) != null){
				array[3] = 4;
			}
		return array;
	}
	
	@Override
	public String getProblemDescription() {
		return "start state: " + start.getX()+","+start.getY() + ", goal state: " + goal.getX()+ "," +goal.getY() + "and the maze is: "+getMatrix(); 
	}
}
