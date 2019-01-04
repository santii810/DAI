/**
 * 
 */
package es.uvigo.esei.dai.hybridserver.xml;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import es.uvigo.esei.dai.hybridserver.errors.SimpleErrorHandler;

/**
 * @author santi
 *
 */
public class XMLValidator {

	public static Document validateXML(File xml, File xsd)
			throws SAXException, ParserConfigurationException, IOException {
		// Construcción del schema
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = schemaFactory.newSchema(xsd);
		// Construcción del parser del documento. Se establece el esquema y se activa la
		// validación y comprobación de namespaces
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(false);
		factory.setNamespaceAware(true);
		factory.setSchema(schema);
		// Se añade el manejador de errores
		DocumentBuilder builder = factory.newDocumentBuilder();
		builder.setErrorHandler(new SimpleErrorHandler());
		return builder.parse(xml);
	}

	/**
	 * @param xml XML to be validated
	 * @param xsd XSL to validate
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public static void validateXML(String xml, String xsd)
			throws SAXException, ParserConfigurationException, IOException {
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

}
