package com.etc.vmall101.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtil {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	
	//数据库连接信息
	private static String dbDriver = "";
	private static String dbUrl = "";
	private static String dbUsername = "";
	private static String dbPassword = "";
	
	static{
		
		try {
			
			//加载文件
			Properties prop = new Properties();
			
			prop.load(JDBCUtil.class.getResourceAsStream("dbconfig.properties"));
			
			//读取属性
			dbDriver = prop.getProperty("dbDriver");
			dbUrl = prop.getProperty("dbUrl");
			dbUsername = prop.getProperty("dbUsername");
			dbPassword = prop.getProperty("dbPassword");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//构造方法---加载驱动
	//单例步骤1---构造方法私有化
	private JDBCUtil(){
		
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//单例步骤2---类内部实例化一个单一的对象
	private static JDBCUtil instance = new JDBCUtil();
	
	//单例步骤3---提供公有的方法返回的单一的对象、
	public static JDBCUtil getInstance(){
		return instance;
	}
	
	
	//打开数据库连接
	private void openConnection(){
		try {
			this.conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//更新方法
	public int execUpdate(String sql, Object... params){   

		//打开连接
		openConnection();
		
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			
			//参数赋值
			for (int i = 0; i < params.length; i++) {
				this.pstmt.setObject(i + 1, params[i]);
			}
			
			return this.pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			return -1;
		} finally{
			this.closeConnection();
		}
		
	}

	//查询方法
	public ResultSet execQuery(String sql, Object... params){
	
		//打开连接
		openConnection();
		
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			
			//参数赋值
			for (int i = 0; i < params.length; i++) {
				this.pstmt.setObject(i + 1, params[i]);
			}
			
			return this.pstmt.executeQuery();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			return null;
		} 
	}
	
	//关闭数据库连接
	public void closeConnection(){
		
		if(this.pstmt!=null){
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				this.pstmt = null;
			}
		}
		
		if(this.conn!=null){
			try {
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				this.conn = null;
			}
		}
		
	}
	

	
	
}
