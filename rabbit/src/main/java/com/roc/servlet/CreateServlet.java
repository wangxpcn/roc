package com.roc.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.roc.utils.MysqlClient;

public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID = -6775862788735743674L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String item = parse(req.getInputStream());
		JSONObject obj = JSON.parseObject(item);
		String name = obj.getString("name");
		int age = obj.getIntValue("age");
		String sex = obj.getString("sex");
		String interesting = obj.getString("interesting");
		String sql = "INSERT INTO roc_rabbit (name,age,sex,interesting) VALUES ('" + name + "', " + age + ",'" + sex + "','"
				+ interesting + "');";
		System.out.println(sql);
		try {
			MysqlClient.add(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		String msg = getContent();
//		JSONArray arr = JSON.parseArray(msg);
//
//		arr.add(obj);
//		String result = JSON.toJSONString(arr);
//		write(result);
		
		resp.setContentType("text/html");
		OutputStream out = resp.getOutputStream();
		out.write("success".getBytes("UTF-8"));
	}

	private String parse(InputStream inputStream) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		String line = null;
		StringBuilder result = new StringBuilder();
		while ((line = reader.readLine()) != null) {
			result.append(line);
		}
		reader.close();
		return result.toString();
	}

	
	
	private void write(String msg) throws IOException {
		String filePath = "D:\\wangxiaopeng\\page.txt";
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath)));
		bw.write(msg);
		bw.close();
	}

	private static String getContent() throws IOException {
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
}
