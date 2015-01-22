package networking;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import model.domains.MazeState;
import model1.Model;
import model1.MyModel;
import model1.Problem;
import model1.Solution;
import tasks.Task;


/**
 * Open socket, receives a problem and sends the solution.
 */
public class ClientHandler implements Task {
	private Socket socket;
	
	public ClientHandler(Socket socket)
	{
		this.socket = socket;
	}

	@Override
	public void doTask() {
		ObjectInputStream in = null;
		ObjectOutputStream out = null;
		
		try {
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
			
			Problem problem = (Problem)in.readObject();
			System.out.println("Got new problem: " + problem.getDomainName());
			
			Model model = new MyModel(problem);
			model.selectDomain(problem.getDomainName());
			model.selectAlgorithm(problem.getAlgorithmName());
			model.doTask();
			//model.solveDomain();
			

		
			Solution solution = model.getSolution();
			System.out.println("Found solution: " + solution.getProblemDescription());
		
			if (problem.getDomainName().equals("Maze")){
				MazeState[][] matrix = (model.getMatrix());
				
				out.writeObject(solution);
				out.writeObject(matrix);				
				
			}
			else
				out.writeObject(solution);			
		} catch (IOException e) {			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				out.close();
				in.close();
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}	
	}	
}
