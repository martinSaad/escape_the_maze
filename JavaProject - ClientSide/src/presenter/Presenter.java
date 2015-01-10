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
	
	private Model model; // the current model
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
			Solution solution = ((Model)observable).getSolution();
			if (solution!=null)
				view.displaySolution(solution);
			else
				System.out.println("stoped solving solution");
	
		}
		else if (observable instanceof View)
		{
			String action = view.getUserAction();
			
			if (action.equals("exit")){
				((MyModel)models.get(models.size()-1)).getClient().stopSolving(); //accessing the last model in "models" and stopping the solving
				return;
			}
			String[] arr = action.split(" ");
			
			String problemDescreption = view.getProblemDescreption();
			
			String commandName = arr[0];
			
			String args = null;
			if (arr.length > 1)
				args = arr[1];
			
			Command command = commands.selectCommand(commandName);
			Model m = command.doCommand(model, args);
			m.setProblemDescription(problemDescreption);
			
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
		
		try {
			view.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
