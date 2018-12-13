/**
 * 
 */
package es.uvigo.esei.dai.hybridserver.errors;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author santi
 *
 */
public class SimpleErrorHandler implements ErrorHandler {

	@Override
	public void warning(SAXParseException exception) throws SAXException {
		exception.printStackTrace();
	}
	
	@Override
	public void fatalError(SAXParseException exception) throws SAXException {
		throw exception;
	}
	
	@Override
	public void error(SAXParseException exception) throws SAXException {
		throw exception;
	}

}
