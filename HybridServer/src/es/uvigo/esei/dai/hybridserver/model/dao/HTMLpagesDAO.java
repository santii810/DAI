package es.uvigo.esei.dai.hybridserver.model.dao;

import java.util.Map;

public class HTMLpagesDAO implements PagesDAO {

	private Map<String, String> pages;

	public HTMLpagesDAO(Map<String, String> pages) {
		this.pages = pages;
	}

	@Override
	public String getHTMLPages() {
		StringBuilder toret = new StringBuilder("<ul>");
		for (String page : pages.keySet()) {
			toret.append("<li><a href=\"\">");
			toret.append(page);
			toret.append("</a></li>\r\n");
		}
		toret.append("</ul>\r\n");
		return toret.toString();
	}

	@Override
	public String getValue(String key) {
		return this.pages.get(key);
	}

	@Override
	public void addPage(String key, String value) {
		this.pages.put(key, value);
	}

}
