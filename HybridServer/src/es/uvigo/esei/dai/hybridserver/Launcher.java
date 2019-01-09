package es.uvigo.esei.dai.hybridserver;

import java.io.File;

public class Launcher {

	public static void main(String[] args) {

		if (args.length == 0)
			new HybridServer().start();
		if (args.length == 1) {
			runServer(args[0]);

			// Properties properties = new Properties();
			// try (FileInputStream inStream = new FileInputStream(fileName)) {
			// properties.load(inStream);
			// new HybridServer(properties).start();
			// } catch (Exception e) {
			// System.out.println("Error reading Properties file");
			// }

		}
		if (args.length > 1) {
			for (int i = 0; i < args.length; i++) {
				runServer(args[i]);
			}
		}
		if (args.length > 4)
			System.out.println("Invalid number of arguments");

	}

	private static void runServer(String fileName) {
		XMLConfigurationLoader configuration = new XMLConfigurationLoader();
		try {
			new HybridServer(configuration.load(new File(fileName))).start();
		} catch (Exception e1) {
			System.out.println("Error reading configuration file");
			e1.printStackTrace();
		}
	}
}
