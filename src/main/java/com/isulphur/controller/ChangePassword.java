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
public class ChangePassword {

	private Connection conn;
	
	@RequestMapping("changePassword.do")
	public ModelAndView ChangePassword(HttpSession httpSession, String teamName, String projectName, String teamLeader, String telephone, String qq, String email){
		conn = new ConnectMysql().getConnection();
		PreparedStatement psql;
		try {
			psql = conn.prepareStatement("update team set teamName = ?, projectName = ?, teamLeader = ?, telephone = ?, qq = ? email =? where id = ?");
			psql.setString(1, teamName);
			psql.setString(2, projectName);
			psql.setString(3, teamLeader);
			psql.setString(4, telephone);
			psql.setString(5, qq);
			psql.setString(6, email);
			psql.setString(7, (String)httpSession.getAttribute("id"));
			psql.executeUpdate();
			psql.close();
			return new ModelAndView("basicInformation.jsp", "message", "1");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelAndView("basicInformation.jsp", "message", "0");
		}
	}
}
