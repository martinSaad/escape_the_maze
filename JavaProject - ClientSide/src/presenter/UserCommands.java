package presenter;

import java.util.HashMap;

import tasks.TaskRunnable;
import model1.MyModel;
import model1.Model;

/**
 * User commands activate the following commands. Using Factory pattern.
 *
 */
public class UserCommands {

	private HashMap<String, Command> commands = 
			new HashMap<String,Command>();
	
	public UserCommands(){
		commands.put("Domain", new SelectDomainCommand());
		commands.put("Algorithm", new SelectAlgorithmCommand());
		commands.put("Solve", new SolveDomainInThread());
		commands.put("Display", new Display());	
		commands.put("exit", new exitProgram());	
	}
	
	/**
	 * Sets what the command does
	 *
	 */
	public interface Command{
		Model doCommand(Model model, String args);
	}
	
	
	/**
	 * Creates the command and does it,
	 * @param model
	 * @param commandName
	 * @param args
	 */
	public void doCommand(Model model, String commandName, String args){
		
		Command command = commands.get(commandName);
		if (command != null){
			command.doCommand(model, args);
		}
	}
	
	
	private class SelectDomainCommand implements Command{
		
		@Override
		public Model doCommand(Model model, String args) {
			Model m = new MyModel();
			m.selectDomain(args);	
			return m;
		}		
	}
	
	public Command selectCommand(String commandName){
		
		Command command = commands.get(commandName);
		return command;
	}
	
	private class SelectAlgorithmCommand implements Command{
		
		@Override
		public Model doCommand(Model model, String args) {
			model.selectAlgorithm(args);
			return model;
		}		
	}

	private class exitProgram implements Command{

		@Override
		public Model doCommand(Model model, String args) {
			((MyModel)model).getClient().stopSolving();
			return model;
		}
		
	}
	
	
	private class SolveDomainInThread implements Command{
		
		@Override
		public Model doCommand(Model model, String args) {	
			Thread t = new Thread(new TaskRunnable(model));
			t.start();
			return model; 
		}
		
	}
	
	private class Display implements Command{
		
		@Override
		public Model doCommand(Model model, String args) {
			((MyModel)model).displayBoard();
			return model;
		}
		
	}

}
