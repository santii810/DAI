/**
 *  HybridServer
 *  Copyright (C) 2018 Miguel Reboiro-Jato
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
package es.uvigo.esei.dai.hybridserver.utils;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;

import es.uvigo.esei.dai.hybridserver.HybridServer;

public abstract class HybridServerTestCase {
	@Rule
	public final TestRule globalTimeout = new Timeout(5, TimeUnit.SECONDS);
	protected HybridServer server;

	protected String url;

	protected HybridServer createHybridServer() {
		return new HybridServer();
	}

	@Before
	public void startServer() {
		this.server = createHybridServer();
		this.url = String.format("http://localhost:%d/", this.server.getPort());

		this.server.start();
	}

	@After
	public void stopServer() {
		this.server.stop();
	}
}
