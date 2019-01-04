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

import java.util.ArrayList;
import java.util.List;

import es.uvigo.esei.dai.hybridserver.webService.ServerConfiguration;

public class Configuration {
	private String dbPassword;
	private String dbURL;
	private String dbUser;

	private int httpPort;
	private int numClients;
	private List<ServerConfiguration> servers;

	private String webServiceURL;

	public Configuration() {
		this(8888, 50, null, "hsdb", "hsdbpass", "jdbc:mysql://localhost:3306/hstestdb",
				new ArrayList<ServerConfiguration>());
	}

	public Configuration(int httpPort, int numClients, String webServiceURL, String dbUser, String dbPassword,
			String dbURL, List<ServerConfiguration> servers) {
		this.httpPort = httpPort;
		this.numClients = numClients;
		this.webServiceURL = webServiceURL;
		this.dbUser = dbUser;
		this.dbPassword = dbPassword;
		this.dbURL = dbURL;
		this.servers = servers;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public String getDbURL() {
		return dbURL;
	}

	public String getDbUser() {
		return dbUser;
	}

	public int getHttpPort() {
		return httpPort;
	}

	public int getNumClients() {
		return numClients;
	}

	public List<ServerConfiguration> getServers() {
		return servers;
	}

	public String getWebServiceURL() {
		return webServiceURL;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public void setDbURL(String dbURL) {
		this.dbURL = dbURL;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public void setHttpPort(int httpPort) {
		this.httpPort = httpPort;
	}

	public void setNumClients(int numClients) {
		this.numClients = numClients;
	}

	public void setServers(List<ServerConfiguration> servers) {
		this.servers = servers;
	}

	public void setWebServiceURL(String webServiceURL) {
		this.webServiceURL = webServiceURL;
	}
}
