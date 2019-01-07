package es.uvigo.esei.dai.hybridserver.webService;

import java.util.List;

import javax.jws.WebService;

import es.uvigo.esei.dai.hybridserver.model.Page;
import es.uvigo.esei.dai.hybridserver.model.dao.PagesDAO;

@WebService(endpointInterface = "es.uvigo.esei.dai.hybridserver.webService.HybridServerService", 
	targetNamespace = "http://hybridserver.dai.esei.uvigo.es/", 
	serviceName = "HybridServerService")
//@WebService(endpointInterface = "es.uvigo.esei.dai.hybridServer.HybridServerService")
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
	public String[] listUuidFromTable(String dbTable) {
		List<String> list = this.dao.listUuidFromTable(dbTable);
		return list.toArray(new String[list.size()]);
	}

	@Override
	public boolean containsUuid(String uuid, String dbTable) {
		this.dao.containsUuid(uuid, dbTable);
		return false;
	}

}
