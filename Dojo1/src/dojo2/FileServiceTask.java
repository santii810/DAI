package dojo2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class FileServiceTask implements Runnable {
	private Socket socket;

	public FileServiceTask(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try (Socket socket = this.socket) {
			InputStream inputStream = socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			String fileName;
			while ((fileName = br.readLine()) != null && fileName.equals("quit")) {
				File file = new File(fileName);
				DataOutputStream dataOutputStrem = new DataOutputStream(socket.getOutputStream());
				if (file.isFile() && file.canRead()) {
					dataOutputStrem.writeLong(file.length());

					try (FileInputStream fileIn = new FileInputStream(file)) {

						int read;
						while ((read = fileIn.read()) != -1) {
							dataOutputStrem.write(read);
						}
					}
				} else {
					dataOutputStrem.writeLong(-1);
				}
				dataOutputStrem.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
