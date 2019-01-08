package es.uvigo.esei.dai.hybridserver.manager;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import es.uvigo.esei.dai.hybridserver.http.HTTPRequest;
import es.uvigo.esei.dai.hybridserver.http.HTTPResponse;
import es.uvigo.esei.dai.hybridserver.http.HTTPResponseStatus;
import es.uvigo.esei.dai.hybridserver.http.MIME;
import es.uvigo.esei.dai.hybridserver.model.Page;
import es.uvigo.esei.dai.hybridserver.model.dao.PagesDAO;
import es.uvigo.esei.dai.hybridserver.xml.XMLValidator;
import es.uvigo.esei.dai.hybridserver.xml.XSLTTransformer;

public class GETRequestManager extends RequestManager {

	public GETRequestManager(HTTPRequest request, HTTPResponse response, P2PManager p2pManager) {
		super(request, response, p2pManager);
	}

	@Override
	public void manageHTMLRequest() {
		manageRequest(PagesDAO.HTML_TABLE_NAME, MIME.TEXT_HTML);
	}

	private void manageRequest(String dbTable, MIME mime) {
		response.putParameter("Content-Type", mime.getMime());

		if (request.getResourceParameters().isEmpty()) {
			// Muestro lista e paginas
			response.setStatus(HTTPResponseStatus.S200);
			response.setContent(showPagesMap(p2pManager.listUuidFromTable(dbTable)));
		} else if (request.getResourceParameters().containsKey("uuid")) {
			String uuidRequested = request.getResourceParameters().get("uuid");
			if (p2pManager.containsUuid(uuidRequested, dbTable)) {
				// uuid correcto
				response.setStatus(HTTPResponseStatus.S200);
				response.setContent(p2pManager.getValue(uuidRequested, dbTable).getContent());
			} else {
				// uuid no conocido
				response.setStatus(HTTPResponseStatus.S404);
				response.setContent(buildHtml("<p> uuid no encontrado</p>"));
			}
		} else {
			// Los parametros no contienen uuid
			response.setStatus(HTTPResponseStatus.S400);
		}
	}

	@Override
	public void manageXMLRequest() {
		response.putParameter("Content-Type", MIME.APPLICATION_XML.getMime());

		if (request.getResourceParameters().isEmpty()) {
			// Muestro lista e paginas
			response.setStatus(HTTPResponseStatus.S200);
			response.setContent(showPagesMap(p2pManager.listUuidFromTable(PagesDAO.XML_TABLE_NAME)));
		} else if (request.getResourceParameters().containsKey("uuid")) {
			String uuidRequested = request.getResourceParameters().get("uuid");
			if (p2pManager.containsUuid(uuidRequested, PagesDAO.XML_TABLE_NAME)) {
				if (request.getResourceParameters().containsKey("xslt")) {
					// Si contiene XSLT procesamos la peticion con XSD y XSLT
					String xsltRequested = request.getResourceParameters().get("xslt");
					if (p2pManager.containsUuid(xsltRequested, PagesDAO.XSLT_TABLE_NAME)) {

						Page parameters = p2pManager.getValue(xsltRequested, PagesDAO.XSLT_TABLE_NAME);
						String xml = p2pManager.getValue(uuidRequested, PagesDAO.XML_TABLE_NAME).getContent();
						String xslt = parameters.getContent();
						String xsd = p2pManager.getValue(parameters.getXsd(), PagesDAO.XSD_TABLE_NAME).getContent();
						String xmlTransformed;

						try {
							XMLValidator.validateXML(xml, xsd);
							xmlTransformed = XSLTTransformer.transform(xml, xslt);
							response.setStatus(HTTPResponseStatus.S200);
							response.setContent(xmlTransformed);
							response.putParameter("Content-Type", MIME.TEXT_HTML.getMime());
						} catch (SAXException | ParserConfigurationException | IOException e) {
							
							e.printStackTrace();
							response.setStatus(HTTPResponseStatus.S400);
						} catch (TransformerException e) {
							System.out.println("problema");
							response.setStatus(HTTPResponseStatus.S500);
							e.printStackTrace();
						}catch (Exception e) {
							//TODO cambiar
							System.out.println("exception no controlada");
							e.printStackTrace();
						}
					} else {
						// No existe el xslt pedido
						response.setStatus(HTTPResponseStatus.S404);
						response.setContent(buildHtml("<p> xslt no encontrado</p>"));
					}
				} else {
					// uuid correcto

					response.setStatus(HTTPResponseStatus.S200);
					response.setContent(p2pManager.getValue(uuidRequested, PagesDAO.XML_TABLE_NAME).getContent());
				}
			} else {
				// uuid no conocido
				response.setStatus(HTTPResponseStatus.S404);
				response.setContent(buildHtml("<p> uuid no encontrado</p>"));
			}
		} else {
			// Los parametros no contienen uuid
			response.setStatus(HTTPResponseStatus.S400);
		}

	}

	@Override
	public void manageXSDRequest() {
		manageRequest(PagesDAO.XSD_TABLE_NAME, MIME.APPLICATION_XML);

	}

	@Override
	public void manageXSLTRequest() {
		manageRequest(PagesDAO.XSLT_TABLE_NAME, MIME.APPLICATION_XML);

	}

}
