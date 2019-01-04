/**
 * 
 */
package es.uvigo.esei.dai.hybridserver.xml;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * @author santi
 *
 */
public class XSLTTransformer {
	/**
	 * Transform XML using XSLT
	 * 
	 * @param xml  XML to be transformed
	 * @param xslt XSLT to transform
	 * @throws TransformerException
	 */
	public static String transform(String xml, String xslt) throws TransformerException {
		Source sourceXSLT = new StreamSource(new StringReader(xslt));
		Source sourceXML = new StreamSource(new StringReader(xml));

		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer(sourceXSLT);
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
		transformer.transform(sourceXML, result);
		return writer.toString();
	}
}
