package es.uvigo.esei.dai.hybridserver.manager;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

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
					String[] parameters = pagesDAO.getXSLTParameters(request.getResourceParameters().get("xslt"));

					String xml = pagesDAO.getValue(uuidRequested, PagesDAO.XML_TABLE_NAME);
					String xslt = parameters[0];
					String xsd = pagesDAO.getValue(parameters[1], PagesDAO.XSD_TABLE_NAME);
					String xmlTransformed;

					try {
						validateXML(xml, xsd);
						xmlTransformed = transform(xml, xslt);
						response.setStatus(HTTPResponseStatus.S200);
						response.setContent(xmlTransformed);

					} catch (SAXException | ParserConfigurationException | IOException e) {
						e.printStackTrace();
						response.setStatus(HTTPResponseStatus.S400);
					} catch (TransformerException e) {
						response.setStatus(HTTPResponseStatus.S500);
						e.printStackTrace();
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

	/**
	 * Transform XML using XSLT
	 * 
	 * @param xml  XML to be transformed
	 * @param xslt XSLT to transform
	 * @throws TransformerException
	 */
	private String transform(String xml, String xslt) throws TransformerException {
		Source sourceXSLT = new StreamSource(new StringReader(xslt));
		Source sourceXML = new StreamSource(new StringReader(xml));

		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer(sourceXSLT);
		StreamResult result = null;
		transformer.transform(sourceXML, result);
		return result.toString();
	}

	/**
	 * @param xml XML to be validated
	 * @param xsd XSL to validate
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	private void validateXML(String xml, String xsd) throws SAXException, ParserConfigurationException, IOException {
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Source source = new StreamSource(new StringReader(xsd));
		Schema schema = schemaFactory.newSchema(source);

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		dbf.setNamespaceAware(true);
		dbf.setSchema(schema);

		DocumentBuilder builder = dbf.newDocumentBuilder();
		builder.setErrorHandler(new SimpleErrorHandler());
		builder.parse(new InputSource(new StringReader(xml)));
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
