package networking;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;



import config.ClientProperties;
import config.HandleProperties;
import model.domains.MazeState;
import model1.Problem;
import model1.Solution;
import model1.SolutionManager;

/**
 * The class represents a client. Connects to the server, send a problem and receives a solution.
 *
 */
public class Client {
	private String serverAddress;
	private int port;
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private boolean stop;
	private MazeState[][] matrix;
	private Solution solution;
	
	//------ Constructors, Getters & Setters ------//
	public Client() {
			ClientProperties properties = HandleProperties.readProperties();
			this.port = properties.getPort();
			this.serverAddress = properties.getIp();
			this.in = null;
			this.out = null;
			this.socket = null;
			this.stop = false;
	}
	

	public MazeState[][] getMatrix() {
		return matrix;
	}

	public Solution getSolution() {
		return solution;
	}

	
	
	
	/**
	 * Connects to the server, send a problem and receives a solution of the maze and the matrix itself
	 * @param problem
	 * @return MazeState[][]
	 */
	public MazeState[][] getBoard(Problem problem){
		
		System.out.println("Send new problem: " + problem.getDomainName());
		
		try {
			socket = new Socket(serverAddress, port);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
						
			out.writeObject(problem);			
		
			//if the client triggered the "stopSolving" - the program will exit here

			if (stop==true) 
				return null;
			
			solution = (Solution)in.readObject();
		//	System.out.println("Found solution: " + solution.getProblemDescription());
			
			
			matrix = (MazeState[][])in.readObject();
				//printBoard(matrix);
			
			return matrix;	
								
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
		return null;
	}
	
	private void printBoard(String[][] matrix) {
		for (int i=0; i<matrix.length; i++){
			for (int j=0; j<matrix[i].length; j++){
				System.out.print(matrix[i][j]);						
			}
			System.out.println("");
		}
		
	}

	/**
	 * Connects to the server, send a problem the receives a solution.
	 * @param problem
	 * @return Solution
	 */
	public Solution getSolution(Problem problem) {		

		if (problem.getDomainName().equals("Maze")) //if it's maze we already have the solution form "getBoard"
			return solution;
		else{
		
		System.out.println("Send new problem: " + problem.getDomainName());
		
		try {
			socket = new Socket(serverAddress, port);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
						
			out.writeObject(problem);			
		
			
			if (stop==true) //if the client triggered the "stopSolving" - the program will exit here
				return null;
			
			solution = (Solution)in.readObject();
			System.out.println("Found solution: " + solution.getProblemDescription());
			
			if (problem.getDomainName().equals("Maze")){
				matrix = (MazeState[][])in.readObject();
				for (int i=0; i<matrix.length; i++){
					for (int j=0; j<matrix[i].length; j++){
						System.out.print(matrix[i][j]);						
					}
					System.out.println("");
				}
			}
			
			return solution;	
								
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
		return null;
		}
	}
	
	
	public void stopSolving(){
		try {
			out.writeObject("exit");
			this.stop = true;
			out.close();
			in.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
