package es.uvigo.esei.dai.hybridserver.webService;

import java.util.List;

import javax.jws.WebService;

import org.apache.derby.tools.sysinfo;

import es.uvigo.esei.dai.hybridserver.HybridServerService;
import es.uvigo.esei.dai.hybridserver.model.Page;
import es.uvigo.esei.dai.hybridserver.model.dao.PagesDAO;

@WebService(endpointInterface = "es.uvigo.esei.dai.hybridserver.HybridServerService", targetNamespace = "http://hybridserver.dai.esei.uvigo.es/", serviceName = "HybridServerService")
public class HybridServerImplementation implements HybridServerService {
	private PagesDAO dao;

	public HybridServerImplementation(PagesDAO dao) {
		this.dao = dao;
	}

	@Override
	public String[] getValue(String uuid, String dbTable) {
		Page page = this.dao.getValue(uuid, dbTable);

		String[] toret = new String[0];
		if (page != null) {
			if (page.getXsd() == null) {
				toret = new String[] { page.getUuid(), page.getContent(), null };
			} else {
				toret = new String[] { page.getUuid(), page.getContent(), page.getXsd() };
			}
		}
		System.out.println("prueba" + toret);
		return toret;
	}

	@Override
	public String[] listUuidFromTable(String dbTable) {
		List<String> list = this.dao.listUuidFromTable(dbTable);
		return list.toArray(new String[list.size()]);
	}

	@Override
	public boolean containsUuid(String uuid, String dbTable) {
		return this.dao.containsUuid(uuid, dbTable);

	}

}
