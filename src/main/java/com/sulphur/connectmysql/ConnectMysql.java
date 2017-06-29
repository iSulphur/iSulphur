package com.sulphur.connectmysql;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author User
 *连接数据库,获取Connection
 */
public class ConnectMysql {

	public Connection conn;
	public ConnectMysql(){
		try{
    		Class.forName("com.mysql.jdbc.Driver");
    		String url = "jdbc:mysql://123.206.176.165:3306/sulphur";
    		conn = DriverManager.getConnection(url, "sulphur", "111111");
    	}catch(Exception e){
    		e.printStackTrace();
    	}
	}
	public Connection getConnection(){
		return conn;
	}
}