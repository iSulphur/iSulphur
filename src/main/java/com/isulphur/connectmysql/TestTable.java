package com.isulphur.connectmysql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestTable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection conn = new ConnectMysql().getConnection();

		try {
			DatabaseMetaData metaData = conn.getMetaData();
			ResultSet rs = metaData.getTables(conn.getCatalog(), "root", null, new String[]{"TABLE"});
			while(rs.next()) {
			 System.out.println(rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
