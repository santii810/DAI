package es.uvigo.esei.dai.hybridserver.html;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.params.HttpParams;

import es.uvigo.esei.dai.hybridserver.http.HTTPHeaders;
import es.uvigo.esei.dai.hybridserver.http.HTTPRequest;
import es.uvigo.esei.dai.hybridserver.http.HTTPResponse;
import es.uvigo.esei.dai.hybridserver.http.HTTPResponseStatus;
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

	public void sendResponse() {
		response.setVersion(HTTPHeaders.HTTP_1_1.getHeader());
		try {
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
		} catch (RuntimeException except) {
			response.setStatus(HTTPResponseStatus.S500);
			response.setContent(except.toString());
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

					response.setContent(showPagesList(pagesDAO.getHTMLPages()));
				} else if (request.getResourceParameters().containsKey("uuid")) {
					String uuidRequested = request.getResourceParameters().get("uuid");
					if (pagesDAO.containsUuid(uuidRequested)) {
						// uuid correcto
						response.setStatus(HTTPResponseStatus.S200);
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

	private String showPagesList(List<String> list) {
		StringBuilder htmlString = new StringBuilder("<ul>");
		for (String page : list) {
			htmlString.append("<li><a href=\"http://localhost:8888/html?uuid="+page    +"\">");
			htmlString.append(page);
			htmlString.append("</a></li>\r\n");
		}
		htmlString.append("</ul>\r\n");
		return this.buildHtml(htmlString.toString());
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

	public static void responseBadRequest(HTTPResponse response) {
		response.setVersion(HTTPHeaders.HTTP_1_1.getHeader());
		response.setStatus(HTTPResponseStatus.S400);
	}

}
