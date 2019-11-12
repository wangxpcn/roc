package com.roc.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {
	private static String readMsg() throws IOException {
		String filePath="D:\\wangxiaopeng\\abc.html";
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
		
		String line = null;
		StringBuilder result = new StringBuilder();
		while((line = br.readLine())!= null) {
			result.append(line);
		}
		return result.toString();
	}
	public static void main(String[] args) throws IOException {
		System.out.println(readMsg());
	}
}
