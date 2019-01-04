package es.uvigo.esei.dai.hybridserver.manager;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import es.uvigo.esei.dai.hybridserver.errors.SimpleErrorHandler;
import es.uvigo.esei.dai.hybridserver.http.HTTPRequest;
import es.uvigo.esei.dai.hybridserver.http.HTTPResponse;
import es.uvigo.esei.dai.hybridserver.http.HTTPResponseStatus;
import es.uvigo.esei.dai.hybridserver.http.MIME;
import es.uvigo.esei.dai.hybridserver.model.dao.PagesDAO;
import es.uvigo.esei.dai.hybridserver.xml.XMLValidator;
import es.uvigo.esei.dai.hybridserver.xml.XSLTTransformer;

public class GETRequestManager extends RequestManager {

	public GETRequestManager(HTTPRequest request, HTTPResponse response, PagesDAO pagesDAO) {
		super(request, response, pagesDAO);
	}

	@Override
	public void manageHTMLRequest() {
		manageRequest(PagesDAO.HTML_TABLE_NAME, MIME.TEXT_HTML);
	}

	@Override
	public void manageXSDRequest() {
		manageRequest(PagesDAO.XSD_TABLE_NAME, MIME.APPLICATION_XML);

	}

	@Override
	public void manageXSLTRequest() {
		manageRequest(PagesDAO.XSLT_TABLE_NAME, MIME.APPLICATION_XML);

	}

	@Override
	public void manageXMLRequest() {
		response.putParameter("Content-Type", MIME.APPLICATION_XML.getMime());

		if (request.getResourceParameters().isEmpty()) {
			// Muestro lista e paginas
			response.setStatus(HTTPResponseStatus.S200);
			response.setContent(showPagesList(pagesDAO.getUuidFromTable(PagesDAO.XML_TABLE_NAME)));
		} else if (request.getResourceParameters().containsKey("uuid")) {
			String uuidRequested = request.getResourceParameters().get("uuid");
			if (pagesDAO.containsUuid(uuidRequested, PagesDAO.XML_TABLE_NAME)) {
				if (request.getResourceParameters().containsKey("xslt")) {
					// Si contiene XSLT procesamos la peticion con XSD y XSLT
					String xsltRequested = request.getResourceParameters().get("xslt");
					if (pagesDAO.containsUuid(xsltRequested, PagesDAO.XSLT_TABLE_NAME)) {

						String[] parameters = pagesDAO.getXSLTParameters(xsltRequested);
						String xml = pagesDAO.getValue(uuidRequested, PagesDAO.XML_TABLE_NAME);
						String xslt = parameters[0];
						String xsd = pagesDAO.getValue(parameters[1], PagesDAO.XSD_TABLE_NAME);
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
							response.setStatus(HTTPResponseStatus.S500);
							e.printStackTrace();
						}
					} else {
						// No existe el xslt petido
						response.setStatus(HTTPResponseStatus.S404);
						response.setContent(buildHtml("<p> xslt no encontrado</p>"));
					}
				} else {
					// uuid correcto

					response.setStatus(HTTPResponseStatus.S200);
					response.setContent(pagesDAO.getValue(uuidRequested, PagesDAO.XML_TABLE_NAME));
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

	



	private void manageRequest(String dbTable, MIME mime) {
		response.putParameter("Content-Type", mime.getMime());

		if (request.getResourceParameters().isEmpty()) {
			// Muestro lista e paginas
			response.setStatus(HTTPResponseStatus.S200);
			response.setContent(showPagesList(pagesDAO.getUuidFromTable(dbTable)));
		} else if (request.getResourceParameters().containsKey("uuid")) {
			String uuidRequested = request.getResourceParameters().get("uuid");
			if (pagesDAO.containsUuid(uuidRequested, dbTable)) {
				// uuid correcto
				response.setStatus(HTTPResponseStatus.S200);
				response.setContent(pagesDAO.getValue(uuidRequested, dbTable));
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

}
