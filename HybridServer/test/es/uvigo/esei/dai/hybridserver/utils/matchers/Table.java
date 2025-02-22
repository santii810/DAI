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
package es.uvigo.esei.dai.hybridserver.utils.matchers;

import java.util.LinkedList;
import java.util.List;

public class Table {
	private final List<Column> columns;
	private final String name;

	Table(String name) {
		this.name = name;
		this.columns = new LinkedList<>();
	}

	void addColumn(Column column) {
		this.columns.add(column);
	}

	List<Column> getColumns() {
		return columns;
	}

	String getName() {
		return name;
	}

	public Column withColumn(String name) {
		return new Column(this, name);
	}
}
