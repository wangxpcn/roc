package com.roc.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Test1 {
	public static void main(String[] args) throws IOException {
		String msg = getContent();
		JSONArray arr = JSON.parseArray(msg);
		System.out.println(arr);
		String item = "{\"sex\":\"sf\",\"interesting\":\"sf\",\"name\":\"f\",\"age\":\"18\"}";
		JSONObject obj = JSON.parseObject(item);
		String name = obj.getString("name");
		System.out.println(name);
		modify(arr, obj);
		String result=JSON.toJSONString(arr);
		System.out.println(result);
	}

	private static void modify(JSONArray arr, JSONObject obj) {
		Iterator<Object> it = arr.iterator();
		int i = 0;
		while(it.hasNext()) {
			JSONObject item = (JSONObject) it.next();
			String name = item.getString("name");
			System.out.println(name);
			String objName = obj.getString("name");
			if(name.equals(objName)) {
				break;
			}
			i++;			
		}
		
		arr.remove(i);
		arr.add(i, obj);
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
