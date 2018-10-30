package es.uvigo.esei.dai.hybridserver.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBPagesDAO implements PagesDAO {

	private String dbUrl;
	private String dbUser;
	private String dbPassword;

	public DBPagesDAO(String dbUrl, String dbUser, String dbPassword) {
		this.dbUrl = dbUrl;
		this.dbUser = dbUser;
		this.dbPassword = dbPassword;
	}

	@Override
	public List<String> getHTMLPages() {
		List<String> toret = new ArrayList<>();
		String query = "SELECT uuid FROM HTML";
		try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
			try (Statement statement = connection.createStatement()) {
				ResultSet result = statement.executeQuery(query);
				while (result.next()) {
					toret.add(result.getString("uuid"));
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
	public String getValue(String uuid) {
		try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
			try (PreparedStatement statement = connection.prepareStatement("SELECT content FROM HTML WHERE uuid = ?")) {
				statement.setString(1, uuid);
				ResultSet result = statement.executeQuery();
				return result.getString(0);
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
	public boolean containsUuid(String uuid) {
		try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
			try (PreparedStatement statement = connection
					.prepareStatement("SELECT count(uuid) AS count FROM HTML WHERE uuid = ?")) {
				statement.setString(1, uuid);
				ResultSet result = statement.executeQuery();
				result.
				return result.getInt(1) == 1;
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

}
