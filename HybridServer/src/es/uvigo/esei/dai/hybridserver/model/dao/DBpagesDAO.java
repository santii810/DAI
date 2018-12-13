package es.uvigo.esei.dai.hybridserver.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBpagesDAO implements PagesDAO {

	private String dbUrl;
	private String dbUser;
	private String dbPassword;

	public DBpagesDAO(String dbUrl, String dbUser, String dbPassword) {
		this.dbUrl = dbUrl;
		this.dbUser = dbUser;
		this.dbPassword = dbPassword;
	}

	@Override
	public List<String> getUuidFromTable(String dbTable) {
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
	public String getValue(String uuid, String dbTable) {
		try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
			try (PreparedStatement statement = connection
					.prepareStatement("SELECT content FROM " + dbTable + " WHERE uuid = ?")) {
				statement.setString(1, uuid);
				try (ResultSet result = statement.executeQuery()) {
					result.next();
					return result.getString("content");
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
	public void addPage(String uuid, String content) {
		try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
			try (PreparedStatement statement = connection
					.prepareStatement("INSERT INTO HTML (uuid, content) " + "VALUES (?, ?)")) {
				statement.setString(1, uuid);
				statement.setString(2, content);
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
	public void delete(String uuid) {
		try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
			try (PreparedStatement statement = connection.prepareStatement("DELETE FROM HTML WHERE uuid=?")) {
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

	}

	/*
	 * return String array with content and xsd uuid values
	 */
	@Override
	public String[] getXSLTParameters(String uuid) {
		try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
			try (PreparedStatement statement = connection
					.prepareStatement("SELECT content, xsd FROM XSLT WHERE uuid = ?")) {
				statement.setString(1, uuid);
				try (ResultSet result = statement.executeQuery()) {
					result.next();
					return new String[] { result.getString("content"), result.getString("xsd") };
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
