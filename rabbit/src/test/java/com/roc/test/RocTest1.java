package com.roc.test;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class RocTest1 {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=false"; 
    private static final String USER = "root";
	private static final String PWD = "root";
 
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName(JDBC_DRIVER);
        
            System.out.println("连接数据库...");
            conn = (Connection) DriverManager.getConnection(DB_URL,USER,PWD);
        
            System.out.println(" 实例化Statement对象...");
            stmt = (Statement) conn.createStatement();
            String sql;
            sql = "select * from roc_rabbit";
            ResultSet rs = stmt.executeQuery(sql);
        
            while(rs.next()){
            	String name  = rs.getString("name");    
                System.out.print("name: " + name);
                int age  = rs.getInt("age");    
                System.out.print("age: " + age);
                String sex  = rs.getString("sex");    
                System.out.print("sex: " + sex);
                String interesting  = rs.getString("interesting");    
                System.out.print("interesting: " + interesting);
                System.out.println();
            }
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
 
    }
}

