package com.isulphur.connectmysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = new ConnectMysql().getConnection();

		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(String.format("select * from admin"));
			//µÇÂ½³É¹¦
			if(rs.next()){
				System.out.println(rs.getString("username")+" "+rs.getString("password"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}