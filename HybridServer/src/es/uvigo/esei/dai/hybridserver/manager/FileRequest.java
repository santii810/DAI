package es.uvigo.esei.dai.hybridserver.manager;

public interface FileRequest {
	void manageHTMLRequest();

	void manageXMLRequest();

	void manageXSDRequest();

	void manageXSLTRequest();

	void sendResponse();
}
