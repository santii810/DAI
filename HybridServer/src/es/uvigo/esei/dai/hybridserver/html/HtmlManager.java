package es.uvigo.esei.dai.hybridserver.html;

import java.io.OutputStream;

import es.uvigo.esei.dai.hybridserver.http.HTTPHeaders;
import es.uvigo.esei.dai.hybridserver.http.HTTPRequest;
import es.uvigo.esei.dai.hybridserver.http.HTTPRequestMethod;
import es.uvigo.esei.dai.hybridserver.http.HTTPResponse;
import es.uvigo.esei.dai.hybridserver.http.HTTPResponseStatus;
import es.uvigo.esei.dai.hybridserver.model.dao.HTMLpagesDAO;
import es.uvigo.esei.dai.hybridserver.model.dao.PagesDAO;

public class HtmlManager {
	private HTTPRequest request;
	private HTTPResponse response;
	private PagesDAO pagesDAO;

	public HtmlManager(HTTPRequest request, HTTPResponse response, PagesDAO pagesDAO) {
		this.request = request;
		this.response = response;
		this.pagesDAO = pagesDAO;
	}

	public void response() {
		response.setVersion(HTTPHeaders.HTTP_1_1.getHeader());

		switch (request.getMethod()) {
		case GET:
			manageGETRequest();
			break;
		case POST:
			managePOSTRequest();
			break;
		case DELETE:
			manageDELETERequest();
			break;

		default:
			System.out.println("Request method not comtemplated");
			break;
		}

	}

	private void manageDELETERequest() {
		if (request.getResourceName().isEmpty()) {
			welcomePage();
		}
	}

	private void managePOSTRequest() {
		// TODO Auto-generated method stub

	}

	private void manageGETRequest() {
		// TODO Auto-generated method stub
		welcomePage();
	}

	private void welcomePage() {
		response.setStatus(HTTPResponseStatus.S200);
//		String webPage = "<html>" + "<head>" + "<meta charset=\"utf-8\">" + "</head>" + "<body>"
//				+ "<h1>Hybrid Server</h1>" + "<h2>Santiago Gomez Vilar</h2>" + "<h2>Milagos Somoza Salinas</h2>"
//				+ "</body>" + "</html>";
		String webPage = "<head>" + "<meta charset=\"utf-8\">" + "</head>" + "<body>" + "<h1>Hybrid Server</h1>"
				+ "<h2>Santiago Gomez Vilar</h2>" + "<h2>Milagos Somoza Salinas</h2>" + "</body>";
		
		
		
		response.setContent(webPage + pagesDAO.getHTMLPages());
	}
}
