import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class dojo1 {
	static String FILE_NAME = "Dojo0_pg2000";

	public static void main(String[] args) {
		// Levantamos servidor
		try (ServerSocket serverSocket = new ServerSocket()) {
			while (true) {
				try {
					// Aceptamos peticion
					Socket clientSocket = serverSocket.accept();
					// Recojemos nombre de fichero
					DataInputStream input = new DataInputStream(clientSocket.getInputStream());
					DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
					File fichero = new File(input.readUTF());

					if (fichero.exists() && fichero.isFile()) {
						output.writeLong(fichero.length());
						output.flush();
						FileInputStream fileInput = new FileInputStream(input.readUTF());
						int aux;
						while ((aux = fileInput.read()) != -1) {
							output.write(aux);
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
