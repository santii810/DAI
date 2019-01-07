package es.uvigo.esei.dai.hybridserver.webService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceException;

public class HybridServerConnection {
	private List<ServerConfiguration> serverConfigurations;
	private static Map<ServerConfiguration, HybridServerService> hybridServerService;

	public HybridServerConnection(List<ServerConfiguration> serverConfigurations) {
		this.serverConfigurations = serverConfigurations;
		hybridServerService = new HashMap<>();
	}

	public Map<ServerConfiguration, HybridServerService> connection() throws MalformedURLException {
		if (this.serverConfigurations != null) {
			for (ServerConfiguration server : serverConfigurations) {
				URL url = new URL(server.getWsdl());
				QName name = new QName(server.getNamespace(), "HybridServerService");

				try {
					Service service = Service.create(url, name);
					HybridServerService hs = service.getPort(HybridServerService.class);
					hybridServerService.put(server, hs);

				} catch (WebServiceException e) {
					System.err.println("Servidor '" + server.getName() + "' caído");
				}
			}
		}
		return hybridServerService;
	}
	
	public static Map<ServerConfiguration, HybridServerService> getServers(){
		return hybridServerService;
	}
}