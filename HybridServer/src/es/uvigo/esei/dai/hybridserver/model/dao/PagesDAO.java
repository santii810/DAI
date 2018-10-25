package es.uvigo.esei.dai.hybridserver.model.dao;

public interface PagesDAO {
	String getHTMLPages();

	String getValue(String key);

	void addPage(String key, String value);
}
