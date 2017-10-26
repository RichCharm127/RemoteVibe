import java.io.IOException;
import java.net.*;
import java.io.*;

public class main {
	static ServerSocket serverSock;
	static Socket controller, motor;
	static OutputStream commandTo;
	static InputStream commandFrom;
	
	public static void main(String[] args) {
		try {
			serverSock = new ServerSocket(59595);
		} catch (IOException e) {
			System.out.println("Server Socket not made. Exiting.");
			System.exit(0);
		}
		
		
		try {
			System.out.println("Waiting for Controller");
			controller = serverSock.accept();
			commandFrom = controller.getInputStream();
			System.out.println("Controller connected to server @ " + controller.getRemoteSocketAddress());
			
			
			System.out.println("Waiting for Motor");
			motor = serverSock.accept();
			commandTo = motor.getOutputStream();
			System.out.println("Motor connected to server @ " + motor.getRemoteSocketAddress());
		} catch (IOException e) {
			System.exit(2);
		}
		
		while(true){
			try {
				running();
			} catch (IOException e) {
				System.out.println("Write Error. Closing.");
				System.exit(0);
			}
		}
	}
	
	static void running() throws IOException{
		int val = commandFrom.read();
		System.out.println("Received " + val);
		commandTo.write((byte)val);
		commandTo.flush();
		System.out.println("Command sent");
	}
}
