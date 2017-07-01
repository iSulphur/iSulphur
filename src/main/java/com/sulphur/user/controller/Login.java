package com.sulphur.user.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sulphur.connectmysql.ConnectMysql;

@Controller
@RequestMapping("/login.do")
public class Login {

	private Connection conn;
	
	@RequestMapping("/method")
	public @ResponseBody String Login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest req){
		
		conn = new ConnectMysql().getConnection();
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(String.format("select * from password where username=\"%s\" and password=\"%s\"", username, password));
			//µÇÂ½³É¹¦
			if(rs.next()){
				req.getSession().setAttribute("username", username);
				return "1";
			}
			//µÇÂ½Ê§°Ü
			else{
				System.out.println("failed");
				return "0";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "0";
		}
	}
}