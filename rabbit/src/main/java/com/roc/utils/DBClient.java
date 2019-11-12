package com.roc.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBClient {
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	static {
		initProperties();
	}

	private static void initProperties() {
		try {
			Properties properties = new Properties();
			properties.load(DBClient.class.getClassLoader().getResourceAsStream("db.properties"));
			driver = properties.getProperty("driver");
			url = properties.getProperty("url");
			username = properties.getProperty("username");
			password = properties.getProperty("password");
			Class.forName(driver);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			connection.setAutoCommit(false);
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static void release(Connection connection, PreparedStatement statement) {
		release(connection, statement, null);
	}
	public static void release(Connection connection, Statement statement, ResultSet resultSet) {
		try {
			close(connection, statement, resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void close(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
		if (connection != null) {
			connection.commit();
			connection.close();
		}
		if (statement != null) {
			statement.close();
		}
		if (resultSet != null) {
			resultSet.close();
		}
	}
}
