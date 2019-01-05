package es.uvigo.esei.dai.hybridserver.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.uvigo.esei.dai.hybridserver.model.Page;

public class DBpagesDAO implements PagesDAO {

	private String dbPassword;
	private String dbUrl;
	private String dbUser;

	public DBpagesDAO(String dbUrl, String dbUser, String dbPassword) {
		this.dbUrl = dbUrl;
		this.dbUser = dbUser;
		this.dbPassword = dbPassword;
	}

	@Override
	public void addPage(Page page, String table) {
		if (table.equals(XSLT_TABLE_NAME)) {
			this.addXSLTPage(page);
		} else {
			try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
				try (PreparedStatement statement = connection
						.prepareStatement("INSERT INTO " + table + " (uuid, content) " + "VALUES (?, ?)")) {
					statement.setString(1, page.getUuid());
					statement.setString(2, page.getContent());
					int result = statement.executeUpdate();
					if (result != 1) {
						throw new SQLException("Insertion error");
					}
				} catch (SQLException queryException) {
					System.out.println("Query error");
					throw new RuntimeException(queryException);
				}
			} catch (SQLException connectionException) {
				System.out.println("Connection error");
				throw new RuntimeException(connectionException);
			}
		}
	}

	private void addXSLTPage(Page page) {
		try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
			try (PreparedStatement statement = connection
					.prepareStatement("INSERT INTO XSLT (uuid, content, xsd) " + "VALUES (?, ?,?)")) {
				statement.setString(1, page.getUuid());
				statement.setString(2, page.getContent());
				statement.setString(3, page.getXsd());
				int result = statement.executeUpdate();
				if (result != 1) {
					throw new SQLException("Insertion error");
				}
			} catch (SQLException queryException) {
				System.out.println("Query error");
				throw new RuntimeException(queryException);
			}
		} catch (SQLException connectionException) {
			System.out.println("Connection error");
			throw new RuntimeException(connectionException);
		}

	}

	@Override
	public boolean containsUuid(String uuid, String dbTable) {
		try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
			try (PreparedStatement statement = connection
					.prepareStatement("SELECT uuid AS count FROM " + dbTable + " WHERE uuid = ?")) {
				statement.setString(1, uuid);
				try (ResultSet result = statement.executeQuery()) {
					return result.next();
				}
			} catch (SQLException queryException) {
				System.out.println("Query error");
				throw new RuntimeException(queryException);
			}
		} catch (SQLException connectionException) {
			System.out.println("Connection error");
			throw new RuntimeException(connectionException);
		}
	}

	@Override
	public void delete(String uuid, String table) {
		try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
			try (PreparedStatement statement = connection.prepareStatement("DELETE FROM " + table + " WHERE uuid=?")) {
				statement.setString(1, uuid);
				int result = statement.executeUpdate();
				if (result != 1) {
					throw new SQLException("Delete error");
				}
			} catch (SQLException queryException) {
				System.out.println("Query error");
				throw new RuntimeException(queryException);
			}
		} catch (SQLException connectionException) {
			System.out.println("Connection error");
			throw new RuntimeException(connectionException);
		}

		if (table.equals(PagesDAO.XSLT_TABLE_NAME)) {
			this.deleteXSLTAssociated(uuid);
		}

	}

	private void deleteXSLTAssociated(String uuid) {
		try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
			try (PreparedStatement statement = connection.prepareStatement("DELETE FROM XSLT WHERE xsd=?")) {
				statement.setString(1, uuid);
				statement.executeUpdate();
			} catch (SQLException queryException) {
				System.out.println("Query error");
				throw new RuntimeException(queryException);
			}
		} catch (SQLException connectionException) {
			System.out.println("Connection error");
			throw new RuntimeException(connectionException);
		}

	}

	@Override
	public List<String> listUuidFromTable(String dbTable) {
		List<String> toret = new ArrayList<>();
		String query = "SELECT uuid FROM " + dbTable;
		try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
			try (Statement statement = connection.createStatement()) {
				try (ResultSet result = statement.executeQuery(query)) {
					while (result.next()) {
						toret.add(result.getString("uuid"));
					}
				}
			} catch (SQLException queryException) {
				System.out.println("Query error");
				throw new RuntimeException(queryException);
			}
		} catch (SQLException connectionException) {
			System.out.println("Connection error");
			throw new RuntimeException(connectionException);
		}
		return toret;
	}

	@Override
	public Page getValue(String uuid, String dbTable) {
		if (dbTable.equals(XSLT_TABLE_NAME)) {
			return this.getXSLTParameters(uuid);
		} else {

			try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
				try (PreparedStatement statement = connection
						.prepareStatement("SELECT content FROM " + dbTable + " WHERE uuid = ?")) {
					statement.setString(1, uuid);
					try (ResultSet result = statement.executeQuery()) {
						result.next();
						return new Page(uuid, result.getString("content"));
					}
				} catch (SQLException queryException) {
					System.out.println("Query error");
					throw new RuntimeException(queryException);
				}
			} catch (SQLException connectionException) {
				System.out.println("Connection error");
				throw new RuntimeException(connectionException);
			}
		}
	}

	/*
	 * return String array with content and xsd uuid values
	 */
	private Page getXSLTParameters(String uuid) {
		try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
			try (PreparedStatement statement = connection
					.prepareStatement("SELECT content, xsd FROM XSLT WHERE uuid = ?")) {
				statement.setString(1, uuid);
				try (ResultSet result = statement.executeQuery()) {
					result.next();
					return new Page(uuid, result.getString("content"), result.getString("xsd"));
				}
			} catch (SQLException queryException) {
				System.out.println("Query error");
				throw new RuntimeException(queryException);
			}
		} catch (SQLException connectionException) {
			System.out.println("Connection error");
			throw new RuntimeException(connectionException);
		}

	}

}
