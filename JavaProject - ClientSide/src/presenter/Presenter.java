package presenter;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import model1.Model;
import model1.MyModel;
import model1.Solution;
import presenter.UserCommands.Command;
import view.SelectGameWindow;
import view.View;

/**
 * The main part of the MVP model. Receives notifications form the model/view and handle it.<p>
 * The class have a main method, used to run a client.
 */
public class Presenter implements Observer {
	
	private Model model; // the current model
	private View view;
	private UserCommands commands;
	private ArrayList<Model> models; // all running models
	
	//------- Constructors, Getters & Setters -------//
	
	public Presenter(Model model, View view){
		this.model = model;
		this.view = view;
		commands = new UserCommands();
		models = new ArrayList<Model>();
		models.add(model);
	}

	public Presenter(MyModel model2) {
		this(model2, null);
	}

	
	//------- Override Functions -------//
	@Override
	public void update(Observable observable, Object arg1) {
		if (observable instanceof Model)
		{	
			if (((MyModel)observable).getProblem().getDomainName().equals("Maze")){
				view.displayCurrentState(((MyModel)model).getMatrix());
			}
			Solution solution = ((Model)observable).getSolution();
			view.displaySolution(solution);
		}
		
		else if(observable instanceof SelectGameWindow)
		{
			view = (View) arg1;
			((Observable) view).addObserver(this);
		}
		
		else if (observable instanceof View)
		{
			String action = ((View)observable).getUserAction();
			
			String[] arr = action.split(" "); //making array of strings and split the words by "space" - for example: Domain NumbersGame>> arr[0] = Domain, arr[1] = NumbersGame
			
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
	
	
	
	//------- Main Function -------//
	public static void main(String[] args) {
		
		
		MyModel model = new MyModel();
		SelectGameWindow view = new SelectGameWindow(600, 400, "Welcome to Our Project");
		Presenter presenter = new Presenter(model);
		model.addObserver(presenter);
		view.addObserver(presenter);
		view.run();

	}
	
	}
//}
