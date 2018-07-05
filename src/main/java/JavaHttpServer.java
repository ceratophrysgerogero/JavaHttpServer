package main.java;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class JavaHttpServer {

	private static int PORT = 8080;

	public static void main(String[] args) throws IOException{

		ServerSocket serverSocket = new ServerSocket(PORT);
		RequestParser parser = new RequestParser();
		RequestHandler handler = new RequestHandler();

		System.out.print("HTTP Server Start Listening at"+PORT+"!");

		while (true) {
			try {
				Socket socket = serverSocket.accept();
				Thread worker  = new WorkerThread(socket,parser,handler);
				worker.start();
			}catch (IOException e) {
				System.out.println("Failed to dispatch:"+e.getMessage());
			}
		}

	}

}
