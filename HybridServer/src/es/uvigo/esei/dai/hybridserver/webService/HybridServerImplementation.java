package es.uvigo.esei.dai.hybridserver.webService;

import java.util.List;

import javax.jws.WebService;

import es.uvigo.esei.dai.hybridserver.model.Page;
import es.uvigo.esei.dai.hybridserver.model.dao.PagesDAO;

//@WebService(endpointInterface = "es.uvigo.esei.dai.hybridServer.HybridServerService", 
//	targetNamespace = "http://hybridserver.dai.esei.uvigo.es/", 
//	serviceName = "HybridServerService")
@WebService(endpointInterface = "es.uvigo.esei.dai.hybridServer.HybridServerService")
public class HybridServerImplementation implements HybridServerService {
	private PagesDAO dao;

	public HybridServerImplementation(PagesDAO dao) {
		this.dao = dao;
	}

	@Override
	public Page getValue(String uuid, String dbTable) {
		return this.dao.getValue(uuid, dbTable);

	}

	@Override
	public List<String> listUuidFromTable(String dbTable) {
		return this.dao.listUuidFromTable(dbTable);
	}

}
