package com.sulphur.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sulphur.connectmysql.ConnectMysql;

@Controller
@RequestMapping("/user")
public class Agenda {

	private Connection conn;

	@RequestMapping("/agenda.do")
	public @ResponseBody ArrayList<String> getReport(HttpServletRequest req) throws SQLException{
		
		ArrayList<String> result = new ArrayList<String>();//���صĽ����
		
		/*��ѯ��û�ϴ��ı���*/
		conn = new ConnectMysql().getConnection();
		PreparedStatement psql;
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(String.format("select reportID from upload where token = 1"));
		
		while(rs.next()){
			result.add(rs.getString("reportID"));
		}
		
		return result;
	}
}
