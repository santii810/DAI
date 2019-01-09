package es.uvigo.esei.dai.hybridserver.manager;

import java.util.HashMap;
import java.util.List;

import com.sun.javafx.collections.MappingChange.Map;

import es.uvigo.esei.dai.hybridserver.http.HTTPHeaders;
import es.uvigo.esei.dai.hybridserver.http.HTTPRequest;
import es.uvigo.esei.dai.hybridserver.http.HTTPResponse;
import es.uvigo.esei.dai.hybridserver.http.HTTPResponseStatus;
import es.uvigo.esei.dai.hybridserver.http.MIME;

public abstract class RequestManager implements FileRequest {
	public static void responseBadRequest(HTTPResponse response) {
		response.setVersion(HTTPHeaders.HTTP_1_1.getHeader());
		response.setStatus(HTTPResponseStatus.S400);
	}

	protected P2PManager p2pManager;
	protected HTTPRequest request;

	protected HTTPResponse response;

	public RequestManager(HTTPRequest request, HTTPResponse response, P2PManager p2pManager) {
		this.request = request;
		this.response = response;
		this.p2pManager = p2pManager;
	}

	protected String buildHtml(String content) {
		response.putParameter("Content-Type", MIME.TEXT_HTML.getMime());
		return "<html><head><meta charset=\"utf-8\"/></head><body>\r\n" + content + "</body></html>";
	}

	public void sendResponse() {
		this.response.setVersion(HTTPHeaders.HTTP_1_1.getHeader());
		try {
			if (request.getResourceName().isEmpty()) {
				this.welcomePage();
			} else {
				switch (request.getResourceName()) {
				case "html":
					this.manageHTMLRequest();
					break;
				case "xsd":
					this.manageXSDRequest();
					break;
				case "xslt":
					this.manageXSLTRequest();
					break;
				case "xml":
					this.manageXMLRequest();
					break;
				default:
					response.setStatus(HTTPResponseStatus.S400);
					break;
				}
			}
		} catch (RuntimeException except) {
			except.printStackTrace();
			response.setStatus(HTTPResponseStatus.S400);
			response.setContent(except.toString());
		}
	}

	protected String showPagesMap(HashMap<String, List<String>> map) {
		StringBuilder htmlString = new StringBuilder();
		for (String key : map.keySet()) {
			htmlString.append("<h2>" + key + "</h2>");
			htmlString.append(showPagesList(map.get(key)));
			htmlString.append("<p>");
		}
		return htmlString.toString();
	}

	private String showPagesList(List<String> list) {
		StringBuilder htmlString = new StringBuilder("<ul>");
		for (String page : list) {
			htmlString.append("<li><a href=\"http://localhost:8888/html?uuid=" + page + "\">");
			htmlString.append(page);
			htmlString.append("</a></li>\r\n");
		}

		htmlString.append("</ul>\r\n");
		return this.buildHtml(htmlString.toString());
	}

	protected void welcomePage() {
		response.setStatus(HTTPResponseStatus.S200);
		String webPage = buildHtml(
				"<h1>Hybrid Server</h1>" + "<h2>Santiago Gomez Vilar</h2>" + "<h2>Milagros Somoza Salinas</h2>");
		response.setContent(webPage);
	}

}
