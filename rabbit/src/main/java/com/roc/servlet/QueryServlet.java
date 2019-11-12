package com.roc.servlet;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.roc.callback.ResultCallback;
import com.roc.utils.MysqlClient;
public class QueryServlet extends HttpServlet {
	private static final long serialVersionUID = -6775862788735743674L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		OutputStream out = resp.getOutputStream();
		String content = getContent();
		out.write(content.getBytes("UTF-8"));
	}

	private String getContent() {
		JSONArray arr = new JSONArray();
		ResultCallback callback = new ResultCallback() {
			@Override
			public void call(ResultSet resultSet) throws SQLException {
				while (resultSet.next()) {
					JSONObject obj = new JSONObject();
					obj.put("id", resultSet.getInt("id"));
					obj.put("name", resultSet.getString("name"));
					obj.put("age", resultSet.getInt("age"));
					obj.put("sex", resultSet.getString("sex"));
					obj.put("interesting", resultSet.getString("interesting"));
					arr.add(obj);
				}
			}
		};
		try {
			MysqlClient.query("select * from roc_rabbit", callback);
			return arr.toJSONString();
		} catch (SQLException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	
	
	
	
	

	private String getContent2() throws IOException {
		InputStream inputStream = new FileInputStream("D:\\wangxiaopeng\\page.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		String line = null;
		StringBuilder result = new StringBuilder();
		while ((line = reader.readLine()) != null) {
			result.append(line);
		}
		reader.close();
		return result.toString();
	}

	public static void main(String[] args) {
		JSONArray arr = new JSONArray();
		ResultCallback callback = new ResultCallback() {
			@Override
			public void call(ResultSet resultSet) throws SQLException {
				while (resultSet.next()) {
					JSONObject obj = new JSONObject();
					obj.put("name", resultSet.getString("name"));
					obj.put("age", resultSet.getInt("age"));
					obj.put("sex", resultSet.getString("sex"));
					obj.put("interesting", resultSet.getString("interesting"));
					arr.add(obj);
				}
			}
		};
		try {
			MysqlClient.query("select * from roc_rabbit", callback);
			System.out.println(arr);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
