package es.uvigo.esei.dai.hybridserver.model.dao;

import java.util.List;

public interface PagesDAO {
	List<String> getHTMLPages();

	String getValue(String uuid);

	void addPage(String uuid, String content);

	boolean containsUuid(String uuid);

	void delete(String uuid);
}
