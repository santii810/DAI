package dojo2;

import java.io.IOException;
import java.net.ServerSocket;

public class dojo2 {
	public static void main(String args[]) {
		try (ServerSocket serverSocket = new ServerSocket(54321)) {
			while (true) {
					new Thread(new FileServiceTask(serverSocket.accept())).start();
					
				}
			}
		} catch (IOException e) {
			System.out.println("Error en el servicor: " + e.getMessage());
		}
	}

}
