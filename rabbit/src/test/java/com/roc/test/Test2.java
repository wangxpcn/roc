package com.roc.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

public class Test2 {
	public static void main(String[] args) throws IOException {
		String msg = getContent();
		System.out.println(msg + 1);
		JSONArray arr = JSON.parseArray(msg);
		System.out.println(arr);
		String s = "hello";
		System.out.println(s);
	}
	
	private static String getContent() throws IOException {
		InputStream inputStream = new FileInputStream("D:\\wangxiaopeng\\page2.txt");
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
