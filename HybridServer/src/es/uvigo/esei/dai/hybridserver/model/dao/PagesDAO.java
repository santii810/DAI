package es.uvigo.esei.dai.hybridserver.model.dao;

import java.util.List;

public interface PagesDAO {
	List<String> getUuidFromTable(String dbTable);

	String getValue(String uuid, String dbTable);

	void addPage(String uuid, String content, String dbTable);

	boolean containsUuid(String uuid, String dbTable);

	void delete(String uuid, String dbTable);

	String[] getXSLTParameters(String uuid);

	void deleteXSLTAssociated(String xsdAssociated);

	public static String HTML_TABLE_NAME = "HTML";
	public static String XSD_TABLE_NAME = "XSD";
	public static String XML_TABLE_NAME = "XML";
	public static String XSLT_TABLE_NAME = "XSLT";
	/**
	 * @param string
	 * @param string2
	 * @param string3
	 */
	void addXSLTPage(String xsltUuid, String xsltContent, String xsdAssociated);

}
