package com.lxk.ChatRoom.utils;


import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.sql.Connection;

public class JDBCUtils {
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	
	public static DataSource getDataSource(){
		return dataSource;
	}
	
	public static Connection getConnection()
	{
		Connection conn =null;
		try {
			return dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
//	
//	public static void main(String[] args) {
//		Connection conn = JDBCUtils.getConnection();
//		System.out.println(conn);
//		System.out.println("1111111111111");
//	}
}
