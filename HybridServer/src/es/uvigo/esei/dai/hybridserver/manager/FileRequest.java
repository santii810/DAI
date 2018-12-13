package es.uvigo.esei.dai.hybridserver.manager;

public interface FileRequest {
	void sendResponse();

	void manageHTMLRequest();

	void manageXSDRequest();

	void manageXSLTRequest();

	void manageXMLRequest();
}
