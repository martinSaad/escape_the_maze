package presenter;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import presenter.UserCommands.Command;
import model1.Model;
import model1.MyModel;
import model1.Solution;
import view.MyConsoleView;
import view.View;

public class Presenter implements Observer {
	
	private Model model;
	private View view;
	private UserCommands commands;
	private ArrayList<Model> models; // all running models
	
	public Presenter(Model model, View view)
	{
		this.model = model;
		this.view = view;
		commands = new UserCommands();
		models = new ArrayList<Model>();
		models.add(model);
	}

	@Override
	public void update(Observable observable, Object arg1) {
		if (observable instanceof Model)
		{
			int answer = 2;
			do{
			System.out.println("do you want keep playing or get solution? 1 for keep playing, 2 for solution");
			Scanner in = new Scanner (System.in);
			answer = in.nextInt();
			if (answer==2){   ////////////////need to add exception
				System.out.println("please enter the number of the game you wish to get solution");
				int index = in.nextInt();
				if (((MyModel)(models.get(index))).getT().isAlive()){  //if the thread of the model the user asked for is alive
					System.out.println("there is no solution yet, please keep playing or wait");
				}
				else{
						Solution solution = ((Model)observable).getSolution();
						view.displaySolution(solution);
					}
				}
			}while(answer==2);
		}
		else if (observable == view)
		{
			String action = view.getUserAction();
			String[] arr = action.split(" ");
			
			String commandName = arr[0];
			
			String args = null;
			if (arr.length > 1)
				args = arr[1];
			
			Command command = commands.selectCommand(commandName);
			Model m = command.doCommand(model, args);
			
			// Check if we got a new model from the command
			if (m != model) {
				this.model = m;
				models.add(m);
				m.addObserver(this);
			}
		}
	}
	
	public static void main(String[] args) {
		MyModel model = new MyModel();
		MyConsoleView view = new MyConsoleView();
		Presenter presenter = new Presenter(model, view);
		
		model.addObserver(presenter);
		view.addObserver(presenter);
		
		view.start();
	}
	
}
