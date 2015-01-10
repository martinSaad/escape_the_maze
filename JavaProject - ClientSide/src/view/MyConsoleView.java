package view;

import java.util.Observable;
import java.util.Scanner;
import model1.SolutionManager;
import model.algorithm.Action;
import model1.Solution;

public class MyConsoleView extends Observable implements View {

	private String action;
	private static boolean firstTimeStart = false;
	private static boolean exit = false;
	private String startState, goalState;
	
	
	public static boolean isExit() {
		return exit;
	}

	public static void setExit(boolean exit) {
		MyConsoleView.exit = exit;
	}

	public static boolean isFirstTimeStart() {
		return firstTimeStart;
	}

	public static void setFirstTimeStart(boolean firstTimeStart) {
		MyConsoleView.firstTimeStart = firstTimeStart;
	}


	// ---- Override Functions from View ---- //
	//View's Main Function - Start Function is the 'Menu'   
	@Override
	public void start() throws Exception //Throw exception if user press wrong key or finish the function if user press exit 
	{
		if(!firstTimeStart){ //'Welcome' only one Time
			System.out.println("Welcome to our Java project. Follow The steps below to play Maze OR Numbers Game.press 'exit' To finish");
			setFirstTimeStart(true);
		}	
	
		Scanner scanner = new Scanner(System.in);
	
		///User choose game
		System.out.print("1. Choose Game: Domain Maze OR Domain NumbersGame - ");
		action = scanner.nextLine();
		
		if (action.equals("Domain NumbersGame"))
			domainNumbersGame();
		else if (action.equals("Domain Maze"))
			domainMaze();
		else if(action.equals("exit")){
			//stopSolving();
			scanner.close();
			return;
		}
		else{
			scanner.close();
			throw new Exception("Wrong"); //Throw Exception if user press wrong key
		}
			
		
		///User choose algorithm
		System.out.print("2. Choose Algorithm: Algorithm Astar OR Algorithm Dijkstra - ");
		action = scanner.nextLine();
		if((action.equals("Algorithm Astar") || action.equals("Algorithm Dijkstra"))){
				this.setChanged();
				this.notifyObservers();
		}
		else if(action.equals("exit")){
			scanner.close();
			return; 
		}
		else{
			scanner.close();
			throw new Exception("Wrong");  //Throw Exception if user press wrong key
		}
	
		
		//Solve Game
		System.out.print("3. Let's Play! Please Write Solve - ");
		action = scanner.nextLine();
		if (action.equals("Solve")){
			this.setChanged();
			this.notifyObservers();
		} 
		else if(action.equals("exit")){
			scanner.close();
			return; 
		}
		else{
			scanner.close();
			throw new Exception("Wrong");  //Throw Exception if user press wrong key 	
		}
		
		System.out.println("if you would like to stop now - enter exit");
		action = scanner.nextLine();
		if (action.equals("exit")){
			this.setChanged();
			this.notifyObservers(); //in this situation we would like to stop the solving
			System.out.println("game over");
		}
		
		
		scanner.close(); 
		SolutionManager.getInstance().saveSolutionsInFile(); 
	}
	
	
	
	
	
	
	
	public void domainMaze(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter how many rows are in the board?");
		startState = scanner.nextLine(); //in this case startState represent the Rows of the maze
		System.out.println("Please enter how many columns are in the board?");
		goalState = scanner.nextLine(); //in this case goalState represent the Columns of the maze
		this.setChanged();
		this.notifyObservers();
	}
	
	public void domainNumbersGame(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter original number. The number must started with 1-9 and not '0' and not longer then 8 digits");
		startState = scanner.nextLine();
		System.out.println("Please enter messed number. The number must started with 1-9 and not '0', and not longer then 8 digits ");
		goalState = scanner.nextLine();
		this.setChanged();
		this.notifyObservers();
	}
	

	@Override
	public void displayCurrentState() {
		// TODO Auto-generated method stub

	}

	@Override
	public void displaySolution(Solution solution) {
			if(solution.getActions() == null)
				System.out.println("There is no solution");
			else {	
				for(Action a : solution.getActions())
					System.out.println(a);
			}		
	}
	
	

	@Override
	public String getUserAction() {		
		return action;
	}

	
	@Override
	public void doTask() {
		boolean exceptionOrNot = true;
		while(exceptionOrNot){
			exceptionOrNot = false;
		try {
			start();
		} catch (Exception e) {
			System.out.println("You Entered wrong input, Let's Try Again");
			exceptionOrNot = true;
		}
	}
	}

	public String getProblemDescreption() {
		return startState+" "+goalState;
	}

}
