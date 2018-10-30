package es.uvigo.esei.dai.hybridserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import es.uvigo.esei.dai.hybridserver.model.dao.DBPagesDAO;
import es.uvigo.esei.dai.hybridserver.model.dao.MapPagesDAO;
import es.uvigo.esei.dai.hybridserver.model.dao.PagesDAO;
import es.uvigo.esei.dai.hybridserver.threadmanagement.ServiceThread;

public class HybridServer {
	private static final String DEFAULT_PASS = "hsdbpass";
	private static final String DEFAULT_USER = "hsdb";
	private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/hstestdb";
	private static final int DEFAULT_SERVICE_PORT = 8888;
	private Thread serverThread;
	private boolean stop;
	private int numClients;
	private PagesDAO pagesDAO;
	private ExecutorService threadPool;
	private int port;
	private String dbUrl;
	private String dbUser;
	private String dbPassword;

	public HybridServer() {
		this.numClients = 50;
		this.port = DEFAULT_SERVICE_PORT;
		this.dbUrl = DEFAULT_URL;
		this.dbUser = DEFAULT_USER;
		this.dbPassword = DEFAULT_PASS;
		this.pagesDAO = new DBPagesDAO(dbUrl, dbUser, dbPassword);
	}

	public HybridServer(Map<String, String> pages) {
		this.numClients = 50;
		this.port = DEFAULT_SERVICE_PORT;
		pagesDAO = new MapPagesDAO(pages);
	}

	public HybridServer(Properties properties) {
		this.numClients = 50;
		this.port = Integer.parseInt(properties.getProperty("port", "8888"));
		this.dbUrl = properties.getProperty("db.url", DEFAULT_URL);
		this.dbUser = properties.getProperty("db.user", DEFAULT_USER);
		this.dbPassword = properties.getProperty("db.password", DEFAULT_PASS);
		this.pagesDAO = new DBPagesDAO(dbUrl, dbUser, dbPassword);
	}

	public int getPort() {
		return port;
	}

	public void start() {
		this.serverThread = new Thread() {

			@Override
			public void run() {
				try (final ServerSocket serverSocket = new ServerSocket(port)) {
					threadPool = Executors.newFixedThreadPool(numClients);
					while (true) {
						Socket socket = serverSocket.accept();
						if (stop)
							break;
						threadPool.execute(new ServiceThread(socket, pagesDAO));
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		this.stop = false;
		this.serverThread.start();
	}

	public void stop() {
		this.stop = true;

		try (Socket socket = new Socket("localhost", getPort())) {
			// Esta conexión se hace, simplemente, para "despertar" el hilo servidor
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		try {
			this.serverThread.join();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		this.serverThread = null;

		threadPool.shutdownNow();

		try {
			threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private PagesDAO getPagesDAO() {
		return pagesDAO;
	}
}
