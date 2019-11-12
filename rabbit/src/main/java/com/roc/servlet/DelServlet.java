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

public class DelServlet extends HttpServlet {
	   @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	    	  
//	    	String msg = getContent();
//	    	JSONArray arr = JSON.parseArray(msg);
	    	
	    	String id = parse(req.getInputStream());
	    	

//			arr.remove(obj);
//			String result=JSON.toJSONString(arr);   	
//	    	write(result);
			String sql = "DELETE FROM roc_rabbit WHERE id="+id+";";
			try {
				MysqlClient.delete(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        resp.setContentType("text/html");
	        OutputStream  out = resp.getOutputStream();
			out.write("success".getBytes("UTF-8"));
	    }
	   
	   private String parse(InputStream inputStream) throws IOException {
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;
			StringBuilder result = new StringBuilder();
			while((line = reader.readLine())!= null) {
				result.append(line);
			}
			reader.close();
			return result.toString();
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
	   
	   private void write(String msg) throws IOException {
			String filePath="D:\\wangxiaopeng\\page.txt";
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath)));
			bw.write(msg);
			bw.close();
		}
}
