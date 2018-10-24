package es.uvigo.esei.dai.hybridserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import es.uvigo.esei.dai.hybridserver.model.dao.HTMLpagesDAO;
import es.uvigo.esei.dai.hybridserver.model.dao.PagesDAO;
import es.uvigo.esei.dai.hybridserver.threadmanagement.ServiceThread;

public class HybridServer {
	private static final int SERVICE_PORT = 8888;
	private Thread serverThread;
	private boolean stop;
	private int maxClients;
	private PagesDAO pagesDAO;

	public HybridServer() {
		this.maxClients = 50;
	}

	public HybridServer(Map<String, String> pages) {
		this.maxClients = 50;
		pagesDAO = new HTMLpagesDAO(pages);
	}

	public HybridServer(Properties properties) {
		this.maxClients = 50;
	}

	public int getPort() {
		return SERVICE_PORT;
	}

	public void start() {
		this.serverThread = new Thread() {
			@Override
			public void run() {
				try (final ServerSocket serverSocket = new ServerSocket(SERVICE_PORT)) {
					ExecutorService threadPool = Executors.newFixedThreadPool(maxClients);
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

		try (Socket socket = new Socket("localhost", SERVICE_PORT)) {
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
	}

	private PagesDAO getPagesDAO() {
		return pagesDAO;
	}
}
