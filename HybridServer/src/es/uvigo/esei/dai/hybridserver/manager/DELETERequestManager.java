package es.uvigo.esei.dai.hybridserver.manager;

import es.uvigo.esei.dai.hybridserver.http.HTTPRequest;
import es.uvigo.esei.dai.hybridserver.http.HTTPResponse;
import es.uvigo.esei.dai.hybridserver.http.HTTPResponseStatus;
import es.uvigo.esei.dai.hybridserver.model.dao.PagesDAO;

public class DELETERequestManager extends RequestManager {

	public DELETERequestManager(HTTPRequest request, HTTPResponse response, PagesDAO pagesDAO) {
		super(request, response, pagesDAO);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void manageHTMLRequest() {
		manageGeneralRequest("html", PagesDAO.HTML_TABLE_NAME);
	}

	@Override
	public void manageXSLTRequest() {
		manageGeneralRequest("xslt", PagesDAO.XSLT_TABLE_NAME);
	}

	@Override
	public void manageXMLRequest() {
		manageGeneralRequest("xml", PagesDAO.XML_TABLE_NAME);
	}

	@Override
	public void manageXSDRequest() {
		if (!request.getResourceParameters().isEmpty() & request.getResourceName().equals("xsd")
				& !request.getResourceParameters().isEmpty() & request.getResourceParameters().containsKey("uuid")) {
			String uuidRequested = request.getResourceParameters().get("uuid");
			if (pagesDAO.containsUuid(uuidRequested, PagesDAO.XSD_TABLE_NAME)) {
//				pagesDAO.deleteXSLTAssociated(uuidRequested);
				pagesDAO.delete(uuidRequested, PagesDAO.XSD_TABLE_NAME);
				response.setStatus(HTTPResponseStatus.S200);
				response.setContent("<p> uuid eliminado </p>");

			} else {
				response.setStatus(HTTPResponseStatus.S404);
				response.setContent("<p> uuid no encontrado </p>");
			}
		}
	}

	private void manageGeneralRequest(String requestType, String table) {
		if (!request.getResourceParameters().isEmpty() & request.getResourceName().equals(requestType)
				& !request.getResourceParameters().isEmpty() & request.getResourceParameters().containsKey("uuid")) {
			String uuidRequested = request.getResourceParameters().get("uuid");
			if (pagesDAO.containsUuid(uuidRequested, table)) {
				pagesDAO.delete(uuidRequested, table);
				response.setStatus(HTTPResponseStatus.S200);
				response.setContent("<p> uuid eliminado </p>");

			} else {
				response.setStatus(HTTPResponseStatus.S404);
				response.setContent("<p> uuid no encontrado </p>");
			}
		}
	}
}
