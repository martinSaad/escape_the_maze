package view;

import java.util.Observable;
import java.util.Scanner;

import model1.SolutionManager;
import model.Algorithm.Action;
import model1.Solution;

public class MyConsoleView extends Observable implements View {

	private String action;
	
	@Override
	public void start() 
	{
		System.out.println("Hello & Welcome to my Java project. I wrote two games - Maze & Numbers Game.\rFirst of All, you need to choose which game you want to play. Maze OR NumbersGame.\rTo play Maze please write Domain Maze, To play Numbers Game please write Domain NumbersGame.\rAfter you choose game, please follow the game directions. \rThan you need to choose the algorithm - Astar OR Dijsktra.\rTo use your game with Astar algorithm please write Algorithm Astar,\rTo use your game with Dijkstra algorithm please write Algorithm Dijkstra.\rThat's All! now you can run your game - the last thing that you need to do is to write Solve.\rEnjoy your time! to finish the game please use 'exit'");
		//action="";
		Scanner scanner = new Scanner(System.in);
		int tempCommands = 3;
		while(tempCommands >=1){
			if(tempCommands == 3){
			System.out.print("Enter The Game you want to play: ");
			action = scanner.nextLine();
			if (!(action.equals("exit")))
			{
				this.setChanged();
				this.notifyObservers();
			} else if(action.equals("exit"))
				break;
				 
			tempCommands--;
			}
			
			if(tempCommands == 2){
				System.out.print("Enter The Algorithm you want to use: ");
				action = scanner.nextLine();
				if (!(action.equals("exit")))
				{
					this.setChanged();
					this.notifyObservers();
				} else if(action.equals("exit"))
					break;
				tempCommands--;
				}
			
			if(tempCommands == 1){
				System.out.print("Ok, Let's play!: ");
				action = scanner.nextLine();
				if (!(action.equals("exit")))
				{
					this.setChanged();
					this.notifyObservers();
				} else if(action.equals("exit"))
					break;
				tempCommands--;
				tempCommands = 3;
				}	
		}
		
		scanner.close(); 
		SolutionManager.getInstance().saveSolutionsInFile();
	}

	@Override
	public void displayCurrentState() {
		// TODO Auto-generated method stub

	}

	@Override
	public void displaySolution(Solution solution) {
			if(solution.getActions() == null)
				System.out.println("There is no movements from your start state to your goal state. please play again");
			else {	
				for(Action a : solution.getActions())
					System.out.println(a);
			}		
	}
	

	@Override
	public String getUserAction() {		
		return action;
	}

}
