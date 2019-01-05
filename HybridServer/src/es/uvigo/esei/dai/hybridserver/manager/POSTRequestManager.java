package es.uvigo.esei.dai.hybridserver.manager;

import java.util.UUID;

import es.uvigo.esei.dai.hybridserver.http.HTTPRequest;
import es.uvigo.esei.dai.hybridserver.http.HTTPResponse;
import es.uvigo.esei.dai.hybridserver.http.HTTPResponseStatus;
import es.uvigo.esei.dai.hybridserver.model.Page;
import es.uvigo.esei.dai.hybridserver.model.dao.PagesDAO;

public class POSTRequestManager extends RequestManager {

	public POSTRequestManager(HTTPRequest request, HTTPResponse response, P2PManager p2pManager) {
		super(request, response, p2pManager);
	}

	private void manageGeneralRequest(String requestType, String table) {
		if (!request.getResourceParameters().isEmpty() & request.getResourceName().equals(requestType)
				& !request.getResourceParameters().isEmpty()
				& request.getResourceParameters().containsKey(requestType)) {
			UUID randomUuid = UUID.randomUUID();
			Page page = new Page(randomUuid.toString(), request.getResourceParameters().get(requestType));
			p2pManager.addPage(page, table);

			response.setContent("<a href=\"" + requestType + "?uuid=" + randomUuid.toString() + "\">"
					+ randomUuid.toString() + "</a>");
			response.setStatus(HTTPResponseStatus.S200);
		} else {
			response.setStatus(HTTPResponseStatus.S400);
		}
	}

	@Override
	public void manageHTMLRequest() {
		manageGeneralRequest("html", PagesDAO.HTML_TABLE_NAME);
	}

	@Override
	public void manageXMLRequest() {
		manageGeneralRequest("xml", PagesDAO.XML_TABLE_NAME);
	}

	@Override
	public void manageXSDRequest() {
		manageGeneralRequest("xsd", PagesDAO.XSD_TABLE_NAME);
	}

	@Override
	public void manageXSLTRequest() {
		if (!request.getResourceParameters().isEmpty() & request.getResourceName().equals("xslt")
				& !request.getResourceParameters().isEmpty() & request.getResourceParameters().containsKey("xslt")
				& request.getResourceParameters().containsKey("xsd")) {
			if (p2pManager.containsUuid(request.getResourceParameters().get("xsd"), PagesDAO.XSD_TABLE_NAME)) {
				UUID randomUuid = UUID.randomUUID();
				Page page = new Page(randomUuid.toString(), request.getResourceParameters().get("xslt"),
						request.getResourceParameters().get("xsd"));
				p2pManager.addPage(page, PagesDAO.XSLT_TABLE_NAME);

				response.setContent(
						"<a href=\"xslt?uuid=" + randomUuid.toString() + "\">" + randomUuid.toString() + "</a>");
				response.setStatus(HTTPResponseStatus.S200);
			} else {
				response.setStatus(HTTPResponseStatus.S404);
				response.setContent(buildHtml("<p> xsd no encontrado</p>"));

			}
		} else {
			response.setStatus(HTTPResponseStatus.S400);
		}
	}
}
