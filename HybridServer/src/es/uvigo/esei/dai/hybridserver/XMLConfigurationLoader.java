/**
 *  HybridServer
 *  Copyright (C) 2017 Miguel Reboiro-Jato
 *  
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package es.uvigo.esei.dai.hybridserver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import es.uvigo.esei.dai.hybridserver.xml.XMLValidator;

public class XMLConfigurationLoader {
	/**
	 * 
	 */
	private static final String CONFIGURATION_XSD_PATH = "configuration.xsd";

	public Configuration load(File xmlFile) throws Exception {
		File xsd = new File(CONFIGURATION_XSD_PATH);

		Document document = XMLValidator.validateXML(xmlFile, xsd);

		Configuration config = new Configuration();

		// Parseado de connections
		NodeList connections = document.getElementsByTagName("connections");
		Element connectionElement = (Element) connections.item(0);	
		Node HTTP = connectionElement.getElementsByTagName("http").item(0);
		config.setHttpPort(Integer.parseInt(HTTP.getTextContent()));
		Node webservice = connectionElement.getElementsByTagName("webservice").item(0);
		config.setWebServiceURL(webservice.getTextContent());
		Node numClients = connectionElement.getElementsByTagName("numClients").item(0);
		config.setNumClients(Integer.parseInt(numClients.getTextContent()));

		// parseado de database
		NodeList database = document.getElementsByTagName("database");
		Element databaseElement = (Element) database.item(0);
		Node user = databaseElement.getElementsByTagName("user").item(0);
		config.setDbUser(user.getTextContent());
		Node pass = databaseElement.getElementsByTagName("password").item(0);
		config.setDbPassword(pass.getTextContent());
		Node URL = databaseElement.getElementsByTagName("url").item(0);
		config.setDbURL(URL.getTextContent());

		// parseado de servers
		ServerConfiguration serverConf;
		List<ServerConfiguration> serverList = new ArrayList<>();

		NodeList servers = document.getElementsByTagName("servers");
		Element serversElement = (Element) servers.item(0);
		NodeList serversList = serversElement.getElementsByTagName("server");
		for (int i = 0; i < serversList.getLength(); i++) {
			if (serversList.item(i) instanceof Element) {
				Element server = (Element) serversList.item(i);

				serverConf = new ServerConfiguration(server.getAttribute("name"), server.getAttribute("wsdl"),
						server.getAttribute("namespace"), server.getAttribute("service"),
						server.getAttribute("httpAddress"));
				serverList.add(serverConf);
			}
		}

		config.setServers(serverList);

		return config;
	}
}
