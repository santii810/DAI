package es.uvigo.esei.dai.hybridserver.manager;

import java.util.UUID;

import es.uvigo.esei.dai.hybridserver.http.HTTPRequest;
import es.uvigo.esei.dai.hybridserver.http.HTTPResponse;
import es.uvigo.esei.dai.hybridserver.http.HTTPResponseStatus;
import es.uvigo.esei.dai.hybridserver.model.dao.PagesDAO;

public class POSTRequestManager extends RequestManager {

	public POSTRequestManager(HTTPRequest request, HTTPResponse response, PagesDAO pagesDAO) {
		super(request, response, pagesDAO);
	}

	@Override
	public void manageHTMLRequest() {
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
