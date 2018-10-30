package es.uvigo.esei.dai.hybridserver.model.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapPagesDAO implements PagesDAO {

	private Map<String, String> pages;

	public MapPagesDAO(Map<String, String> pages) {
		this.pages = pages;
	}

	@Override
	public List<String> getHTMLPages() {
		List<String> toret = new ArrayList<>();
		for (String page : pages.keySet()) {
			toret.add(page);
		}
		return toret;
	}

	@Override
	public String getValue(String key) {
		return this.pages.get(key);
	}

	@Override
	public void addPage(String uuid, String content) {
		this.pages.put(uuid, content);
	}

	public boolean containsUuid(String uuid) {
		return pages.containsKey(uuid);
	}

	@Override
	public void delete(String uuidRequested) {
		this.pages.remove(uuidRequested);
	}

}
