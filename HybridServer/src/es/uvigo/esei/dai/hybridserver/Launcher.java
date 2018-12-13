package es.uvigo.esei.dai.hybridserver;

import java.io.FileInputStream;
import java.util.Properties;

public class Launcher {

	public static void main(String[] args) {

		if (args.length > 1)
			System.out.println("Invalid number of arguments");
		if (args.length == 1) {
			String fileName = args[0];

			Properties properties = new Properties();
			try (FileInputStream inStream = new FileInputStream(fileName)) {
				properties.load(inStream);

				new HybridServer(properties).start();
			} catch (Exception e) {
				System.out.println("Error reading Properties file");
			}
		} else {
			new HybridServer().start();
		}

	}
}
