package com.sulphur.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
@RequestMapping("/user")
public class Login {

	private Connection conn;
	
	@RequestMapping("/login.do")
	public @ResponseBody String Login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest req){
		
		conn = new ConnectMysql().getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from password where username=? and password=?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
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