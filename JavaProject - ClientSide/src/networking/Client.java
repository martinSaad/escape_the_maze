package networking;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


import java.util.Arrays;

import config.ClientProperties;
import config.HandleProperties;
import model1.Problem;
import model1.Solution;

public class Client {
	private String serverAddress;
	private int port;
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private boolean stop;

	public Client() {
		ClientProperties properties = HandleProperties.readProperties();
		this.port = properties.getPort();
		this.serverAddress = properties.getIp();
		this.in = null;
		this.out = null;
		this.socket = null;
		this.stop = false;
	}
	
	public Solution getSolution(Problem problem) {		
		
		
		
		System.out.println("Send new problem: " + problem.getDomainName());
		
		try {
			socket = new Socket(serverAddress, port);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
						
			out.writeObject(problem);			
		
			
			if (stop==true) //if the client triggered the "stopSolving" - the program will exit here
				return null;
			
			Solution solution = (Solution)in.readObject();
			System.out.println("Found solution: " + solution.getProblemDescription());
			
			if (problem.getDomainName().equals("Maze")){
				String[][] matrix = (String[][])in.readObject();
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
	
	public void stopSolving(){
		try {
			this.stop = true;
			out.writeObject("stop");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
