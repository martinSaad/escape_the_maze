package presenter;

import java.util.HashMap;

import tasks.TaskRunnable;
import model1.MyModel;
import model1.Model;

public class UserCommands {

	private HashMap<String, Command> commands = 
			new HashMap<String,Command>();
	
	public UserCommands()
	{
		commands.put("Domain", new SelectDomainCommand());
		commands.put("Algorithm", new SelectAlgorithmCommand());
//		commands.put("Solve", new SolveDomainCommand());
		commands.put("Solve", new SolveDomainInThread());
		
	}
	
	public void doCommand(Model model, String commandName, String args)
	{
		Command command = commands.get(commandName);
		if (command != null)
		{
			command.doCommand(model, args);
		}
	}
	
	public interface Command
	{
		Model doCommand(Model model, String args);
	}
	
	private class SelectDomainCommand implements Command
	{
		@Override
		public Model doCommand(Model model, String args) {
			Model m = new MyModel();
			m.selectDomain(args);	
			return m;
		}		
	}
	
	public Command selectCommand(String commandName)
	{
		Command command = commands.get(commandName);
		return command;
	}
	
	private class SelectAlgorithmCommand implements Command
	{
		@Override
		public Model doCommand(Model model, String args) {
			model.selectAlgorithm(args);
			return model;
		}		
	}
	
/*	private class SolveDomainCommand implements Command
	{
		@Override
		public Model doCommand(Model model, String args) {
			model.solveDomain();	
			return model;
		}		
	}
*/	
	private class SolveDomainInThread implements Command
	{

		@Override
		public Model doCommand(Model model, String args) {
			((MyModel)model).setT(new Thread(new TaskRunnable(model)));
			System.out.println("////////////Algo Thread is alive//////////////////");
			((MyModel)model).doTask();
			//((MyModel)model).getT().start();
			return model;
		}
		
	}

}
