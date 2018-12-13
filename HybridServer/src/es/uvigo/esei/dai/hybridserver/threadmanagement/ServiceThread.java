package es.uvigo.esei.dai.hybridserver.threadmanagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import es.uvigo.esei.dai.hybridserver.http.HTTPParseException;
import es.uvigo.esei.dai.hybridserver.http.HTTPRequest;
import es.uvigo.esei.dai.hybridserver.http.HTTPResponse;
import es.uvigo.esei.dai.hybridserver.http.HTTPResponseStatus;
import es.uvigo.esei.dai.hybridserver.manager.DELETERequestManager;
import es.uvigo.esei.dai.hybridserver.manager.GETRequestManager;
import es.uvigo.esei.dai.hybridserver.manager.POSTRequestManager;
import es.uvigo.esei.dai.hybridserver.manager.RequestManager;
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

			HTTPResponse response = new HTTPResponse();

			try {
				HTTPRequest request = new HTTPRequest(br);
				RequestManager manager = null;

				try {
					switch (request.getMethod()) {
					case GET:
						manager = new GETRequestManager(request, response, pagesDAO);
						break;
					case POST:
						manager = new POSTRequestManager(request, response, pagesDAO);
						break;
					case DELETE:
						manager = new DELETERequestManager(request, response, pagesDAO);
						break;
					default:
						break;
					}
				} catch (RuntimeException except) {
					response.setStatus(HTTPResponseStatus.S500);
					response.setContent(except.toString());
				}

				try {
					manager.sendResponse();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (HTTPParseException e) {
				RequestManager.responseBadRequest(response);
			} finally {
				OutputStream outputStream = socket.getOutputStream();
				response.print(new OutputStreamWriter(outputStream));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
