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
		if (!request.getResourceParameters().isEmpty() & request.getResourceName().equals("html")
				& !request.getResourceParameters().isEmpty() & request.getResourceParameters().containsKey("uuid")) {
			String uuidRequested = request.getResourceParameters().get("uuid");
			if (pagesDAO.containsUuid(uuidRequested, PagesDAO.HTML_TABLE_NAME)) {
				pagesDAO.delete(uuidRequested);
				response.setStatus(HTTPResponseStatus.S200);
				response.setContent("<p> uuid eliminado </p>");

			} else {
				response.setStatus(HTTPResponseStatus.S404);
				response.setContent("<p> uuid no encontrado </p>");
			}
		}

	}

	@Override
	public void manageXSDRequest() {
		// TODO Auto-generated method stub

	}

	@Override
	public void manageXSLTRequest() {
		// TODO Auto-generated method stub

	}

	@Override
	public void manageXMLRequest() {
		// TODO Auto-generated method stub

	}
}
