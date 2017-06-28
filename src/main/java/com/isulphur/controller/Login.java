package com.isulphur.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.isulphur.connectmysql.ConnectMysql;

/**
 * @author User
 *登陆界面的逻辑设计
 */
@Controller
public class Login {

	private Connection conn;
	
	@RequestMapping("login.do")
	public String Login(HttpSession httpSession, String user, String password){
		conn = new ConnectMysql().getConnection();
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(String.format("select * from admin where username=\"%s\" and password=\"%s\"", user, password));
			//登陆成功
			if(rs.next()){
				httpSession.setAttribute("username", user);
				return "basicInformation";
			}
			//登陆失败
			else{
				return "Login";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Login";
		}
	}
}