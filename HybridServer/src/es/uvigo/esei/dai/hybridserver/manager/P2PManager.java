package es.uvigo.esei.dai.hybridserver.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.uvigo.esei.dai.hybridserver.HybridServerService;
import es.uvigo.esei.dai.hybridserver.model.Page;
import es.uvigo.esei.dai.hybridserver.model.dao.PagesDAO;
import es.uvigo.esei.dai.hybridserver.webService.HybridServerConnection;
import es.uvigo.esei.dai.hybridserver.webService.ServerConfiguration;

public class P2PManager {

	private PagesDAO pagesDAO;
	private HybridServerConnection hybridServerConnection;
	private Map<String, HybridServerService> implementations;

	public P2PManager(PagesDAO pagesDAO, HybridServerConnection hybridServerConnection) {
		this.pagesDAO = pagesDAO;
		this.hybridServerConnection = hybridServerConnection;
		if (hybridServerConnection.getServers().isEmpty()) {
			this.implementations = new HashMap<String, HybridServerService>();
		}
	}

	public void addPage(Page page, String dbTable) {
		this.pagesDAO.addPage(page, dbTable);
	}

	public boolean containsUuid(String uuidRequested, String dbTable) {
		boolean toret = this.pagesDAO.containsUuid(uuidRequested, dbTable);
		if (toret == false && hybridServerConnection.getServers() != null) {
			for (String serverName : this.getUpServers().keySet()) {
				toret = this.getUpServers().get(serverName).containsUuid(uuidRequested, dbTable);
				if (toret)
					break;
			}
		}
		return toret;

	}

	public Page getValue(String uuidRequested, String dbTable) {
		Page toret = this.pagesDAO.getValue(uuidRequested, dbTable);
		if (toret == null && hybridServerConnection.getServers() != null) {
			for (String serverName : this.getUpServers().keySet()) {
				String[] page = this.getUpServers().get(serverName).getValue(uuidRequested, dbTable);

				if (page.length != 0) {
					toret = new Page(page[0], page[1], page[2]);
					// Se inserta una copia en la base de datos local
					this.pagesDAO.addPage(toret, dbTable);
					break;
				}
			}
		}
		return toret;
	}

	public HashMap<String, List<String>> listUuidFromTable(String dbTable) {
		HashMap<String, List<String>> toret = new HashMap<String, List<String>>();
		toret.put("Local Server", this.pagesDAO.listUuidFromTable(dbTable));
		if (hybridServerConnection.getServers() != null) {
			for (String serverName : this.getUpServers().keySet()) {
				List<String> uuids = Arrays.asList(this.getUpServers().get(serverName).listUuidFromTable(dbTable));
				// TODO cambiar string
				toret.put(serverName, uuids);
			}
		}
		return toret;
	}

	public void delete(String uuid, String dbTable) {
		this.pagesDAO.delete(uuid, dbTable);
	}

	private Map<String, HybridServerService> getUpServers() {
		this.implementations = new HashMap<>();
		for (ServerConfiguration sc : hybridServerConnection.getServers().keySet()) {
			String serverName = sc.getName();
			HybridServerService hybridServerService = hybridServerConnection.getServers().get(sc);
			this.implementations.put(serverName, hybridServerService);
		}
		return this.implementations;
	}

}
