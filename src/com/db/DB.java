package com.db;
import com.db.*;
import com.entity.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
	//驱动//连接地址//用户名//密码
	private static String driver="com.mysql.cj.jdbc.Driver";
	private static String url="jdbc:mysql://localhost:3306/newsdb?serverTimezone=UTC";
	private static String user="root";
	private static String pwd="root";
	public static void main(String args[]) {
		getConnection();
	}
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
		
	}
	
}
