package networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import config.HandleProperties;
import config.ServerProperties;
import tasks.TaskRunnable;

/**
 * This class represents the server.<p> 
 * The server uses TCP.
 */

public class MyTCPIPServer {
	private ServerSocket server;
	private ExecutorService executor;
	private Thread thread;
	private int port;
	private int numOfClients;
	private ClientHandler[] arrayOfClients;
	private int i=0;
		

	// ----- Constructors ----- //
	public MyTCPIPServer(int port, int numOfClients) {
		this.port = port;
		this.numOfClients = numOfClients;
	}
	
	public MyTCPIPServer() {
		ServerProperties properties = HandleProperties.readProperties();
		this.port = properties.getPort();
		this.numOfClients = properties.getNumOfClients();
		arrayOfClients = new ClientHandler[numOfClients];
	}

	
	/**
	 * Start the activity of the server.
	 */
	public void startServer() {
		try {
			server = new ServerSocket(port);			
			server.setSoTimeout(1000000);
			
			executor = Executors.newFixedThreadPool(numOfClients);
		
			thread = new Thread(new Runnable() {
				
			@Override
			public void run() {
				while(!Thread.interrupted()) {
					try {
						Socket socket = server.accept();
						System.out.println("Got new connection, client number "+(i+1));
						
						if (socket != null) {
							ClientHandler handler = new ClientHandler(socket);
							arrayOfClients[i] = handler;
							executor.submit(new TaskRunnable(handler));
							i++;
						}
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	catch (Exception e) {
						
					}
				}				
			}			
		});
		thread.start();
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
	}
	
	

	
	public ClientHandler getArrayOfClients(int client) {
		return arrayOfClients[client];
	}
	
	
	
	/**
	 * Stops the server activity.	
	 */
	public void stopServer() {		
		try {
		
			thread.interrupt();
			executor.shutdownNow();
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}