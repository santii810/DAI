package es.uvigo.esei.dai.hybridserver.model;

public class Page {

	private String content;
	private String uuid;
	private String xsd;

	public Page(String uuid, String content, String xsd) {
		this.uuid = uuid;
		this.content = content;
		this.xsd = xsd;
	}

	public Page(String uuid, String content) {
		this.uuid = uuid;
		this.content = content;
	}

	public String getContent() {
		return this.content;
	}

	public String getUuid() {
		return this.uuid;
	}

	public String getXsd() {
		return xsd;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setXsd(String xsd) {
		this.xsd = xsd;
	}
}
