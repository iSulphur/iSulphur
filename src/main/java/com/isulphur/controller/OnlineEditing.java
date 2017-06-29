package com.isulphur.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.isulphur.connectmysql.ConnectMysql;

@Controller
public class OnlineEditing {

	private Connection conn;
	
	@RequestMapping("onlineEditing.do")
	public ModelAndView OnlineEditing(HttpSession httpSession, String teamName, String projectName, String teamLeader, String telephone, String email, String progress, String result, String plan){
		conn = new ConnectMysql().getConnection();
		PreparedStatement psql;
		try {
			psql = conn.prepareStatement("INSERT INTO 上传报告表 VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
			psql.setString(1, teamName);
			psql.setString(2, projectName);
			psql.setString(3, teamLeader);
			psql.setString(4, telephone);
			psql.setString(5, email);
			psql.setString(6, progress);
			psql.setString(7, plan);
			psql.executeUpdate();
			psql.close();
			return new ModelAndView("onlineEditing.jsp", "message", "1");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelAndView("onlineEditing.jsp", "message", "0");
		}
	}
}
