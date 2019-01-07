package es.uvigo.esei.dai.hybridserver.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.uvigo.esei.dai.hybridserver.model.Page;
import es.uvigo.esei.dai.hybridserver.model.dao.PagesDAO;
import es.uvigo.esei.dai.hybridserver.webService.HybridServerService;
import es.uvigo.esei.dai.hybridserver.webService.ServerConfiguration;

public class P2PManager {

	private PagesDAO pagesDAO;
	private Map<ServerConfiguration, HybridServerService> servers;
	private HybridServerService[] implementations;

	public P2PManager(PagesDAO pagesDAO, Map<ServerConfiguration, HybridServerService> servers) {
		this.pagesDAO = pagesDAO;
		this.servers = servers;
		if (this.servers.isEmpty()) {
			this.implementations = new HybridServerService[0];
		} else {
			this.implementations = new HybridServerService[servers.size()];
		}
	}

	public void addPage(Page page, String dbTable) {
		this.pagesDAO.addPage(page, dbTable);
	}

	public boolean containsUuid(String uuidRequested, String dbTable) {
		boolean toret = this.pagesDAO.containsUuid(uuidRequested, dbTable);
		if (toret == false && this.servers != null) {
			for (HybridServerService hybridServerService : this.getUpServers()) {
				toret = hybridServerService.containsUuid(uuidRequested, dbTable);
				if (toret)
					break;
			}
		}
		return toret;

	}

	public Page getValue(String uuidRequested, String dbTable) {
		Page toret = this.pagesDAO.getValue(uuidRequested, dbTable);
		if (toret == null && this.servers != null) {
			for (HybridServerService hybridServerService : this.getUpServers()) {
				toret = hybridServerService.getValue(uuidRequested, dbTable);
				if (toret != null) {
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
		if (this.servers != null) {
			for (HybridServerService hybridServerService : this.getUpServers()) {
				List<String> uuids = Arrays.asList(hybridServerService.listUuidFromTable(dbTable));
				// TODO cambiar string
				toret.put("Remote server", uuids);

			}
		}
		return toret;
	}

	public void delete(String uuid, String dbTable) {
		this.pagesDAO.delete(uuid, dbTable);
	}

	private HybridServerService[] getUpServers() {
		ArrayList<HybridServerService> list = new ArrayList<>(this.servers.values());
		for (int i = 0; i < list.size(); i++) {
			this.implementations[i] = list.get(i);
		}
		return this.implementations;
	}

}
