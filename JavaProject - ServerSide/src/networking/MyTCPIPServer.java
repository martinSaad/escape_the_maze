package networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import config.HandleProperties;
import config.ServerProperties;
import tasks.TaskRunnable;

public class MyTCPIPServer {
	private ServerSocket server;
	private ExecutorService executor;
	private Thread thread;
	private int port;
	private int numOfClients;
		

	
	public MyTCPIPServer(int port, int numOfClients) {
		this.port = port;
		this.numOfClients = numOfClients;
	}
	
	public MyTCPIPServer() {
		ServerProperties properties = HandleProperties.readProperties();
		this.port = properties.getPort();
		this.numOfClients = properties.getNumOfClients();
	}

	public void startServer() {
		try {
			server = new ServerSocket(port);			
			server.setSoTimeout(1000000);
			
			executor = Executors.newFixedThreadPool(numOfClients);
		
			thread = new Thread(new Runnable() {

			int i = 1;	
			@Override
			public void run() {
				while(!Thread.interrupted()) {
					try {
						Socket socket = server.accept();
						System.out.println("Got new connection, client number "+i);
						
						if (socket != null) {
							ClientHandler handler = new ClientHandler(socket);
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
	
	public void stopServer() {		
		try {
			thread.interrupt();
			executor.shutdownNow();
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}