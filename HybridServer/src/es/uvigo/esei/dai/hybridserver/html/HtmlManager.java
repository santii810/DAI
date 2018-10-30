package es.uvigo.esei.dai.hybridserver.html;

import java.io.OutputStream;
import java.util.UUID;

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
			break;
		}

	}

	private void manageDELETERequest() {
		if (!request.getResourceParameters().isEmpty() & request.getResourceName().equals("html")
				& !request.getResourceParameters().isEmpty() & request.getResourceParameters().containsKey("uuid")) {
			String uuidRequested = request.getResourceParameters().get("uuid");
			if (pagesDAO.containsUuid(uuidRequested)) {
				pagesDAO.delete(uuidRequested);
				response.setStatus(HTTPResponseStatus.S200);
				response.setContent("<p> uuid eliminado </p>");
			} else {
				response.setStatus(HTTPResponseStatus.S404);
				response.setContent("<p> uuid no encontrado </p>");
			}
		}

	}

	private void managePOSTRequest() {
		System.out.println(!request.getResourceParameters().isEmpty());
		System.out.println(request.getResourceName().equals("html"));
		System.out.println(!request.getResourceParameters().isEmpty());

		if (!request.getResourceParameters().isEmpty() & request.getResourceName().equals("html")
				& !request.getResourceParameters().isEmpty() & request.getResourceParameters().containsKey("html")) {
			UUID randomUuid = UUID.randomUUID();
			pagesDAO.addPage(randomUuid.toString(), request.getResourceParameters().get("html"));

			response.setContent(
					"<a href=\"html?uuid=" + randomUuid.toString() + "\">" + randomUuid.toString() + "</a>");
			response.setStatus(HTTPResponseStatus.S200);
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
					if (pagesDAO.containsUuid(uuidRequested)) {
						// uuid correcto
						response.setStatus(HTTPResponseStatus.S200);
//						response.setContent(buildHtml("<p>" + pagesDAO.getValue(uuidRequested) + "</p>"));
						response.setContent(pagesDAO.getValue(uuidRequested));
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
		String webPage = buildHtml(
				"<h1>Hybrid Server</h1>" + "<h2>Santiago Gomez Vilar</h2>" + "<h2>Milagros Somoza Salinas</h2>");
		response.setContent(webPage);
	}

	private String buildHtml(String content) {
		return "<html><head><meta charset=\"utf-8\"></head><body>\r\n" + content + "</body></html>";
	}

}
