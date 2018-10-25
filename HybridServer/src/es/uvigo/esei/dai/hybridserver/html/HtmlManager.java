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
	}

	private void managePOSTRequest() {
		if (!request.getResourceParameters().isEmpty() & request.getResourceName().equals("html")
				& !request.getResourceParameters().isEmpty() & request.getResourceParameters().containsKey("uuid")) {
			String uuidRequested = request.getResourceParameters().get("uuid");
			if (pagesDAO.getHTMLPages().contains(uuidRequested)) {
				
			}else {
				pagesDAO.addPage(request.getResourceParameters().get("uuid"));
			}


		} else {
			response.setStatus(HTTPResponseStatus.S400);
		}
	}

	private void manageGETRequest() {
		if (request.getResourceName().isEmpty()) {
			welcomePage();
		} else {
			if (request.getResourceName().equals("html")) {
				if (request.getResourceParameters().isEmpty()) {
					// Muestro lista e paginas
					response.setStatus(HTTPResponseStatus.S200);
					response.setContent(this.buildHtml(pagesDAO.getHTMLPages()));
				} else if (request.getResourceParameters().containsKey("uuid")) {
					String uuidRequested = request.getResourceParameters().get("uuid");
					if (pagesDAO.getHTMLPages().contains(uuidRequested)) {
						// uuid correcto
						response.setStatus(HTTPResponseStatus.S200);
						response.setContent(buildHtml("<p>" + pagesDAO.getValue(uuidRequested) + "</p>"));
					} else {
						// uuid no conocido
						response.setStatus(HTTPResponseStatus.S404);
						response.setContent(buildHtml("<p> uuid no encontrado</p>"));
					}
				} else {
					// Los parametros no contienen uuid
					response.setStatus(HTTPResponseStatus.S400);
				}
			} else {
				// Peticion distinta a html
				response.setStatus(HTTPResponseStatus.S400);
			}
		}
	}

	private void welcomePage() {
		response.setStatus(HTTPResponseStatus.S200);

		System.out.println(response.getStatus().getCode());

		String webPage = buildHtml(
				"<h1>Hybrid Server</h1>" + "<h2>Santiago Gomez Vilar</h2>" + "<h2>Milagros Somoza Salinas</h2>");

		response.setContent(webPage);
//		response.setContent(webPage + pagesDAO.getHTMLPages());
	}

	private String buildHtml(String content) {
		return "<html><head><meta charset=\"utf-8\"></head><body>\r\n" + content + "</body></html>";
	}

}
