package com.roc.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.roc.callback.ResultCallback;

public class MysqlClient {
	public static void add(String sql) throws SQLException {
		Connection connection = DBClient.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.executeUpdate();
		DBClient.release(connection, statement);
	}

	public static void delete(String sql) throws SQLException {
		Connection connection = DBClient.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.executeUpdate();
		DBClient.release(connection, statement);
	}

	public static void update(String sql) throws SQLException {
		Connection connection = DBClient.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.executeUpdate();
		DBClient.release(connection, statement);
	}

	public static void query(String sql, ResultCallback callback) throws SQLException {
		Connection connection = DBClient.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		callback.call(resultSet);
		DBClient.release(connection, statement, resultSet);
	}

	public static void main(String[] args) throws SQLException {
		List<String> names = new ArrayList<>();
		ResultCallback callback = new ResultCallback() {
			@Override
			public void call(ResultSet resultSet) throws SQLException {
				while (resultSet.next()) {
				 names.add(resultSet.getString("name"));
				}
			}};
		query("select * from roc_rabbit", callback);
//		add("INSERT INTO roc_rabbit (name,age,sex,interesting) VALUES ('bb', 21,'nv','pingpang');");
		System.out.println(names);
	}
}
