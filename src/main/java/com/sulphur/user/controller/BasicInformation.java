package com.sulphur.user.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sulphur.connectmysql.ConnectMysql;

@Controller
@RequestMapping("/basic")
public class BasicInformation {

	private Connection conn;
	
	@RequestMapping("/update")
	public @ResponseBody String BasicInformation(@RequestParam("team_name") String team_name,@RequestParam("project") String project, @RequestParam("team_leader") String team_leader,
			@RequestParam("leader_phone") String leader_phone,@RequestParam("leader_email") String leader_email,HttpServletRequest req){
		conn = new ConnectMysql().getConnection();
		PreparedStatement psql;
		try {
			psql = conn.prepareStatement("update team set team_name = ?, project = ?, team_leader = ?, leader_phone = ?, leader_email =? where ID = ?");
			psql.setString(1, team_name);
			psql.setString(2, project);
			psql.setString(3, team_leader);
			psql.setString(4, leader_phone);
			psql.setString(5, leader_email);
			psql.setString(6, (String)req.getSession().getAttribute("ID"));
			psql.executeUpdate();
			psql.close();
			conn.close();
			return "1";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "0";
		}
	}
}