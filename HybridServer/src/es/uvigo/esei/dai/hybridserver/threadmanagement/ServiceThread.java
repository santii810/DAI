package es.uvigo.esei.dai.hybridserver.threadmanagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import es.uvigo.esei.dai.hybridserver.html.HtmlManager;
import es.uvigo.esei.dai.hybridserver.http.HTTPParseException;
import es.uvigo.esei.dai.hybridserver.http.HTTPRequest;
import es.uvigo.esei.dai.hybridserver.http.HTTPResponse;
import es.uvigo.esei.dai.hybridserver.model.dao.PagesDAO;

public class ServiceThread implements Runnable {

	private Socket socket;
	private PagesDAO pagesDAO;

	public ServiceThread(Socket socket, PagesDAO pagesDAO) {
		this.socket = socket;
		this.pagesDAO = pagesDAO;
	}

	@Override
	public void run() {
		try (Socket socket = this.socket) {
			BufferedReader br = new BufferedReader((new InputStreamReader(socket.getInputStream())));
			String line;

			HTTPRequest request = new HTTPRequest(br);
			HTTPResponse response = new HTTPResponse();

			HtmlManager manager = new HtmlManager(request, response, pagesDAO);
			try {
				manager.sendResponse();
			} catch (Exception e) {
				e.printStackTrace();
			}

			OutputStream outputStream = socket.getOutputStream();
			response.print(new OutputStreamWriter(outputStream));

		} catch (IOException | HTTPParseException e) {
			e.printStackTrace();
		}
	}

}
