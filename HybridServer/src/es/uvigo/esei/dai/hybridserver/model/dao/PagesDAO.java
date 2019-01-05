package es.uvigo.esei.dai.hybridserver.model.dao;

import java.util.List;

import es.uvigo.esei.dai.hybridserver.model.Page;

public interface PagesDAO {
	public static String HTML_TABLE_NAME = "HTML";
	public static String XML_TABLE_NAME = "XML";
	public static String XSD_TABLE_NAME = "XSD";
	public static String XSLT_TABLE_NAME = "XSLT";

	void addPage(Page page, String dbTable);

	boolean containsUuid(String uuid, String dbTable);

	void delete(String uuid, String dbTable);

	List<String> listUuidFromTable(String dbTable);

	Page getValue(String uuid, String dbTable);

}
